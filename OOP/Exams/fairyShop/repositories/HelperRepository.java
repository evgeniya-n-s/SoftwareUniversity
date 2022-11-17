package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HelperRepository<T> implements Repository<Helper> {
    private List<Helper> helpers;

    public HelperRepository() {
        this.helpers= new ArrayList<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableList(this.helpers);
    }

    @Override
    public void add(Helper model) {
        this.helpers.add(model);
    }

    @Override
    public boolean remove(Helper model) {
        return this.helpers.remove(model);
    }

    @Override
    public Helper findByName(String name) {
        return this.helpers.stream().filter(f->f.getName().equals(name)).findFirst().orElse(null);
    }
}
