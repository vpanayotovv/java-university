package rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit){
        if(this.capacity > this.data.size()){
            this.data.add(rabbit);
        }
    }
    public boolean removeRabbit(String name){
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(name)){
                this.data.remove(i);
                return true;
            }
        }
        return false;
    }
    public void removeSpecies(String species){
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            if(this.data.get(i).getSpecies().equals(species)){
                index.add(i);
            }
        }
        for (int i = 0; i < index.size(); i++) {
            this.data.remove(index.get(i) - i);
        }
    }
    public Rabbit sellRabbit(String name){
        Rabbit rabbit = null;
        for (Rabbit aData : this.data) {
            if (aData.getName().equals(name)) {
                rabbit = aData;
                break;
            }
        }
        return rabbit;
    }
    public List<Rabbit> sellRabbitBySpecies(String species){
        List<Rabbit> rabbits = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            if(this.data.get(i).equals(species)){
                this.data.get(i).setAvailable(false);
                rabbits.add(this.data.get(i));
            }
        }
        return rabbits;
    }
    public int count(){
        return this.data.size();
    }
    public String report(){
        StringBuilder sb = new StringBuilder(String.format("Rabbits available at %s:%n",this.name));
        for (Rabbit rabbit : this.data) {
            if (rabbit.isAvailable()){
                sb.append(rabbit.toString()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
