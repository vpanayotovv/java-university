package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;


public class ChampionshipControllerImpl implements ChampionshipController {
    private Repository<Rider> riderRepo;
    private Repository<Motorcycle> motorcycleRepo;
    private Repository<Race> raceRepo;

    public ChampionshipControllerImpl(Repository<Rider> riderRepo,
                                      Repository<Motorcycle> motorcycleRepo,
                                      Repository<Race> raceRepo) {
        this.riderRepo = riderRepo;
        this.motorcycleRepo = motorcycleRepo;
        this.raceRepo = raceRepo;
    }

    @Override
    public String createRider(String riderName) {
        if (this.riderRepo.getByName(riderName) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, riderName));
        }
        Rider rider = new RiderImpl(riderName);
        this.riderRepo.add(rider);
        return String.format(OutputMessages.RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        if (this.motorcycleRepo.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model));
        }
        Motorcycle motorcycle;
        if (type.equals("Power")) {
            motorcycle = new PowerMotorcycle(model, horsePower);
        } else {
            motorcycle = new SpeedMotorcycle(model,horsePower);
        }
        this.motorcycleRepo.add(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_CREATED,type,model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        if (this.riderRepo.getByName(riderName) == null){
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND,riderName));
        }
        if (this.motorcycleRepo.getByName(motorcycleModel) == null){
            throw new NullPointerException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND,motorcycleModel));
        }
        this.riderRepo.getByName(riderName).addMotorcycle(this.motorcycleRepo.getByName(motorcycleModel));
        return String.format(OutputMessages.MOTORCYCLE_ADDED,riderName,motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        if (this.raceRepo.getByName(raceName) == null){
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        if (this.riderRepo.getByName(riderName) == null){
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND,riderName));
        }
        this.raceRepo.getByName(raceName).addRider(this.riderRepo.getByName(riderName));
        return String.format(OutputMessages.RIDER_ADDED,riderName,raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepo.getByName(name) != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));
        }

        Race race = new RaceImpl(name,laps);
        raceRepo.add(race);
        return String.format(OutputMessages.RACE_CREATED,name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepo.getByName(raceName);
        if (race == null){
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }

        if (race.getRiders().size() < 3 ){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));
        }

        List<Rider> winners = race.getRiders().stream().sorted((f, s) -> Double.compare(s
                .getMotorcycle()
                .calculateRacePoints(race.getLaps()), f
                .getMotorcycle()
                .calculateRacePoints(race.getLaps())))
                .limit(3)
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        winners.get(0).winRace();
        sb.append(String.format(OutputMessages.RIDER_FIRST_POSITION,winners.get(0).getName(),race.getName()))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.RIDER_SECOND_POSITION,winners.get(1).getName(),race.getName()))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.RIDER_THIRD_POSITION,winners.get(2).getName(),race.getName()));

        this.raceRepo.remove(race);

        return sb.toString().trim();
    }
}
