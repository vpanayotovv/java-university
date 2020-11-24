package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PictureImportDto;
import softuni.exam.domain.dto.PictureImportRootDto;
import softuni.exam.domain.entity.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final String PICTURE_PATH = "src/main/resources/files/xml/pictures.xml";

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public String importPictures() throws JAXBException {
        StringBuilder builder = new StringBuilder();

        PictureImportRootDto pictureImportRootDto = this.xmlParser.importXMl(PictureImportRootDto.class, PICTURE_PATH);

        for (PictureImportDto picture : pictureImportRootDto.getPictures()) {
            if (this.validatorUtil.isValid(picture)) {
                Picture mappedPic = this.modelMapper.map(picture, Picture.class);

                this.pictureRepository.saveAndFlush(mappedPic);
                builder.append(String.format("Successfully imported picture - %s", mappedPic.getUrl())).append(System.lineSeparator());
            } else {
                builder.append("Invalid picture").append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files.readString(Path.of(PICTURE_PATH));
    }


}
