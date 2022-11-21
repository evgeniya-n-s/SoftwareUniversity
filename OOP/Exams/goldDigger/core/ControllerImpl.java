package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int spotCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        if ("Archaeologist".equals(kind)) {
            discoverer = new Archaeologist(discovererName);
        } else if("Anthropologist".equals(kind)){
            discoverer = new Anthropologist(discovererName);
        } else if("Geologist".equals(kind)){
           discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED,kind,discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED,spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
//        String name = discovererRepository.byName(discovererName).getName();
//        if(!name.equals(discovererName)){
//            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST,discovererName));
//        }
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST,discovererName));
        }
    //    discovererRepository.remove(discovererRepository.byName(discovererName));

        discovererRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE,discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
//        List<Driver> winners = drivers.stream().sorted((d1,d2)->(int)(d2.getCar().calculateRacePoints(numberOfLaps) - d1.getCar().calculateRacePoints(numberOfLaps))).limit(3).collect(Collectors.toList());
       // spotRepository.byName(spotName).getExhibits();

       // return String.format(ConstantMessages.INSPECT_SPOT,spotName,0);
        List<Discoverer> discoverers = this.discovererRepository.getCollection().stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());
        if (discoverers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = this.spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverers);
        long excluded = discoverers.stream().filter(d -> d.getEnergy() == 0).count();
        this.spotCount++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excluded);

    }

    @Override
    public String getStatistics() {
   //     "{inspectedSpotCount}
//        spotRepository.getCollection();
//        for (Discoverer discoverer:discovererRepository.getCollection()){
//
//        }
//        return null;
        StringBuilder out = new StringBuilder();
        out.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, this.spotCount));
        out.append(System.lineSeparator());
        out.append(ConstantMessages.FINAL_DISCOVERER_INFO);

        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for(Discoverer discoverer : discoverers){
            out.append(System.lineSeparator());
            out.append(String.format(ConstantMessages.FINAL_DISCOVERER_NAME, discoverer.getName()));
            out.append(System.lineSeparator());
            out.append(String.format(ConstantMessages.FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()));
            out.append(System.lineSeparator());
            if(discoverer.getMuseum().getExhibits().isEmpty()){
                out.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));

            }else{
                out.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                        String.join(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discoverer.getMuseum().getExhibits())));
            }
        }
        return out.toString();
    }
}
