package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation{
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
//        Double getEnergy = discoverers.stream().map(Discoverer::getEnergy).findFirst().get();
//
//        if(getEnergy != null){
//            for (Discoverer discoverer: discoverers){
//                if(discoverer.getMuseum().getExhibits()!=null){
//                    double energy = discoverer.getEnergy();
//
//                    energy--;
//                }
//            }
//        }
        Collection<String> spotExhibits = spot.getExhibits();

        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && spotExhibits.iterator().hasNext()) {
                discoverer.dig();

                String currentExhibit = spotExhibits.iterator().next();
                discoverer.getMuseum().getExhibits().add(currentExhibit);
                spotExhibits.remove(currentExhibit);
            }
        }
    }
}
