package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        if ("ShortHouse".equals(type)) {
            House house = new ShortHouse(name);
            houses.add(house);
        }else if("LongHouse".equals(type)){
            House house = new LongHouse(name);
            houses.add(house);
        }else {
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        if("Ball".equals(type)){
            Toy toy = new Ball();
            toys.buyToy(toy);
        } else if ("Mouse".equals(type)) {
            Toy toy = new Mouse();
            toys.buyToy(toy);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);
        if(toy == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
        }
        House house = this.houses.stream().filter(f->f.getName().equals(houseName)).findFirst().get();
        house.buyToy(toy);
        toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        if ("ShorthairCat".equals(catType)) {
            cat = new ShorthairCat(catName,catBreed,price);
        }else if("LonghairCat".equals(catType)){
            cat = new LonghairCat(catName,catBreed,price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        House house = this.houses.stream().filter(f->f.getName().equals(houseName)).findFirst().get();
        if(catType.startsWith("Long") && house.getClass().getSimpleName().startsWith("Long")){
            house.addCat(cat);
        } else if(catType.startsWith("Short") && house.getClass().getSimpleName().startsWith("Short")){
            house.addCat(cat);
        } else {
            String.format(ConstantMessages.UNSUITABLE_HOUSE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = this.houses.stream().filter(f->f.getName().equals(houseName)).findFirst().get();
        house.feeding();
        return String.format(ConstantMessages.FEEDING_CAT,house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = this.houses.stream().filter(f->f.getName().equals(houseName)).findFirst().get();
        double catSize = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toySize = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double total = catSize+toySize;

        return String.format(ConstantMessages.VALUE_HOUSE,houseName,total);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house: this.houses){
            sb.append(house.getStatistics());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
