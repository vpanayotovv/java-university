package softuni.exam.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.TeamImportDto;
import softuni.exam.domain.dto.TeamImportRootDto;
import softuni.exam.domain.entity.Picture;
import softuni.exam.domain.entity.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;


import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final PictureRepository pictureRepository;
    private final String PICTURE_PATH = "src/main/resources/files/xml/teams.xml";

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.pictureRepository = pictureRepository;
    }


    @Override
    @Transactional
    public String importTeams() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TeamImportRootDto teamImportRootDto = this.xmlParser.importXMl(TeamImportRootDto.class, PICTURE_PATH);

        for (TeamImportDto teamImportDto : teamImportRootDto.getTeamImportDtos()) {
            if (this.teamRepository.findByName(teamImportDto.getName()) == null) {
                if (this.validatorUtil.isValid(teamImportDto)) {

                    Team mappedTeam = this.modelMapper.map(teamImportDto, Team.class);

                    Picture picture = this.pictureRepository.findByUrl(teamImportDto.getPicture().getUrl());
                    if (this.pictureRepository.findByUrl(teamImportDto.getPicture().getUrl()) != null) {
                        mappedTeam.setPicture(picture);
                        this.teamRepository.saveAndFlush(mappedTeam);

                        sb.append(String.format("Successfully imported - %s", mappedTeam.getName())).append(System.lineSeparator());
                    }
                } else {
                    sb.append("Invalid team").append(System.lineSeparator());
                }

            } else {
                sb.append("Invalid team").append(System.lineSeparator());
            }

        }


        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return Files.readString(Path.of(PICTURE_PATH));
    }

}
