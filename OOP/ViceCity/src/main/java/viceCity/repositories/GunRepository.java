package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GunRepository implements Repository<Gun>{
    private final List<Gun> guns;

    public GunRepository() {
        this.guns = new LinkedList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableList(this.guns);
    }

    @Override
    public void add(Gun model) {
        if (!this.guns.contains(model)){
            guns.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return this.guns.remove(model);
    }

    @Override
    public Gun find(String name) {
        for (Gun gun : this.guns) {
            if (gun.getName().equals(name)){
                return gun;
            }
        }
        return null;
    }
}
