package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PlayerImportDto;
import softuni.exam.domain.entity.Picture;
import softuni.exam.domain.entity.Player;
import softuni.exam.domain.entity.Position;
import softuni.exam.domain.entity.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;
    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final String PLAYER_PATH = "src/main/resources/files/json/players.json";

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson, TeamRepository teamRepository, PictureRepository pictureRepository) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerImportDto[] playerImportDtos = this.gson.fromJson(new FileReader(PLAYER_PATH), PlayerImportDto[].class);

        for (PlayerImportDto playerImportDto : playerImportDtos) {
            if (this.validatorUtil.isValid(playerImportDto)) {
                Position position;
                try {
                    position = Position.valueOf(playerImportDto.getPosition());

                }catch (Exception ex){
                    sb.append("Invalid player").append(System.lineSeparator());
                    continue;
                }

                Player mappedPlayer = this.modelMapper.map(playerImportDto, Player.class);
                Picture picture = this.pictureRepository.findByUrl(playerImportDto.getPicture().getUrl());
                Team team = this.teamRepository.findByName(playerImportDto.getTeam().getName());
                mappedPlayer.setPosition(position);
                mappedPlayer.setPicture(picture);
                mappedPlayer.setTeam(team);

                this.playerRepository.saveAndFlush(mappedPlayer);
                sb.append(String.format("Successfully imported player: %s %s",
                        mappedPlayer.getFirstName() ,
                        mappedPlayer.getLastName()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid player").append(System.lineSeparator());
            }
        }


        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(PLAYER_PATH));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        return "";
    }

    @Override
    public String exportPlayersInATeam() {
        return "";
    }


}
