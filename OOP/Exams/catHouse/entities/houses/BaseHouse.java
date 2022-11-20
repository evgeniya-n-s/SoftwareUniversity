package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseHouse implements House{
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int sumSoftness() {
        int sum = 0;
        for (Toy toy:this.toys){
            sum+= toy.getSoftness();
        }
        return sum;
//        return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
  //      if(cats.size() > capacity){
        if(this.getCats().size() >= capacity){
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
    //    cats.add(cat);
        this.getCats().add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
       // cats.remove(cat);
        this.getCats().remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
       // toys.add(toy);
        this.getToys().add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat:this.getCats()){
            cat.eating();
        }
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        //this.getName,
        sb.append(String.format("%s %s:%n",this.getName(), this.getClass().getSimpleName()));
        sb.append("Cats: ");
        //this.getClass.isEmpty
        if(this.getCats().isEmpty()){
            sb.append("none");
        } else {
            sb.append(this.getCats().stream().map(Cat::getName).collect(Collectors.joining(" ")).trim());
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Toys: %d Softness: %d",this.getToys().size(),this.sumSoftness()));
       // sb.append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name==null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name=name;
    }

    @Override
    public Collection<Cat> getCats() {
        return cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }

    public int getCapacity() {
        return this.cats.size();
    }
}
