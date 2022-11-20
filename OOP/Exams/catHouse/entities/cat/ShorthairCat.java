package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int DEFAULT_KILOGRAMS = 7;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, DEFAULT_KILOGRAMS,price);

    }

    @Override
    public void eating() {
        setKilograms(getKilograms()+1);
    }

}
