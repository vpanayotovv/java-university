package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureImportDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {

    private final Gson gson;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final String PICTURES_PATH = "src/main/resources/files/json/pictures.json";

    public PictureServiceImpl(Gson gson, PictureRepository pictureRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gson = gson;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURES_PATH)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureImportDto[] pictureImportDtos = this.gson.fromJson(readPicturesFromFile(), PictureImportDto[].class);
        for (PictureImportDto pictureImportDto : pictureImportDtos) {
            if (this.validationUtil.isValid(pictureImportDto)){
                Picture mappedPicture = this.modelMapper.map(pictureImportDto, Picture.class);
                this.pictureRepository.saveAndFlush(mappedPicture);
                sb.append(String.format("Successfully import picture - %s\n",pictureImportDto.getName()));
            }
            else {
                sb.append("Invalid picture\n");
            }
        }

        return sb.toString();
    }
}
