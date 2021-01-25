package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository implements Repository<Gun> {
    private Collection<Gun> models;

    public GunRepository(){
        this.models=new ArrayList<>();
    }
    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Gun model) {
        boolean flag =false;
        for (Gun gun : models) {
            if(gun.getName().equals(model.getName())){
                flag=true;
            }
        }
        if(!flag){
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        if(this.models.contains(model)){
            this.models.remove(model);
            return true;
        }
        return false;
    }

    @Override
    public Gun find(String name) {
        Gun gun=null;
        for (Gun model : models) {
            if(model.getName().equals(name)){
                gun=model;
                break;
            }
        }
        return gun;
    }
}
