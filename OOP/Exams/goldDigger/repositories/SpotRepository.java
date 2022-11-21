package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpotRepository implements Repository<Spot> {
    private Map<String,Spot> spots;

    public SpotRepository() {
        this.spots = new LinkedHashMap<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(spots.values());
    }

    @Override
    public void add(Spot entity) {
        spots.putIfAbsent(entity.getName(),entity);
    }

    @Override
    public boolean remove(Spot entity) {
        return spots.remove(entity.getName()) !=null;
    }

    @Override
    public Spot byName(String name) {
        return spots.get(name);
    }
}
