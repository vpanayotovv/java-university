package src.aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private List<Fish> fishInPool;
    private String name;
    private int capacity;
    private int size;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.size;
    }
    public int getFishInPool(){

        return this.fishInPool.size();
    }
    public void add(Fish fish){
        boolean isSafeToAdd = true;
        if(this.fishInPool.size() < this.capacity){
            for (Fish fishFromPool : this.fishInPool) {
                if(fishFromPool.getName().equals(fish.getName())){
                    isSafeToAdd = false;
                    break;
                }
            }
        }else {
            isSafeToAdd =false;
        }
        if (isSafeToAdd){
            this.fishInPool.add(fish);
        }
    }
    public boolean remove(String name){
        for (Fish fish : this.fishInPool) {
            if(fish.getName().equals(name)){
                return this.fishInPool.remove(fish);
            }
        }
        return false;
        
    }
    public Fish findFish(String name){
        for (Fish fish : this.fishInPool) {
            if (fish.getName().equals(name)){
                return fish;
            }
        }
        return null;
    }
    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Aquarium: %s ^ Size: %d%n",this.getName(),this.getSize()));
        for (Fish fish : this.fishInPool) {

           sb.append(fish.toString()).append(System.lineSeparator());

        }
        return sb.toString();
    }
}
