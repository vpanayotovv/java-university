package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerImportDto;
import softuni.exam.models.dto.SellerRootImportDto;
import softuni.exam.models.entity.Rating;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(SELLER_PATH)));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        SellerRootImportDto sellerRootImportDto = this.xmlParser.importXMl(SellerRootImportDto.class, SELLER_PATH);


        for (SellerImportDto seller : sellerRootImportDto.getSellerImportDtos()) {
            Rating rating;

            try {
                rating = Rating.valueOf(seller.getRating());
            }catch (Exception ex){
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            Seller mappedSeller = this.modelMapper.map(seller, Seller.class);
            if (this.validationUtil.isValid(seller)) {
                mappedSeller.setRating(rating);
                this.sellerRepository.saveAndFlush(mappedSeller);
                sb.append(String.format("Successfully import seller %s - %s\n",
                        mappedSeller.getLastName(),
                        mappedSeller.getEmail()));
            } else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }

        }

        return sb.toString();
    }
}
