package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscovererRepository implements Repository<Discoverer> {
    private Map<String,Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers.values());
    }

    @Override
    public void add(Discoverer entity) {
        discoverers.putIfAbsent(entity.getName(),entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return discoverers.remove(entity.getName()) !=null;
    }

    @Override
    public Discoverer byName(String name) {
        return discoverers.get(name);
    }
}
