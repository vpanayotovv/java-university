package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferImportDto;
import softuni.exam.models.dto.OfferRootImportDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final String OFFER_PATH = "src/main/resources/files/xml/offers.xml";

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFER_PATH)));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        OfferRootImportDto offerRootImportDto = this.xmlParser.importXMl(OfferRootImportDto.class, OFFER_PATH);
        for (OfferImportDto offerImportDto : offerRootImportDto.getOfferImportDtos()) {
            if (this.validationUtil.isValid(offerImportDto)){

                Offer mappedOffer = this.modelMapper.map(offerImportDto, Offer.class);
                Car car = this.carRepository.findById(mappedOffer.getCar().getId()).get();
                Seller seller = this.sellerRepository.findById(mappedOffer.getSeller().getId()).get();

                mappedOffer.setPictures(car.getPictures());
                mappedOffer.setCar(car);
                mappedOffer.setSeller(seller);

                this.offerRepository.save(mappedOffer);
                sb.append(String.format("Successfully import offer %s - %s\n",
                        mappedOffer.getAddedOn(),
                        mappedOffer.isHasGoldStatus()));

            }else {
                sb.append("Invalid offer").append(System.lineSeparator());
            }

        }


        return sb.toString();
    }
}
