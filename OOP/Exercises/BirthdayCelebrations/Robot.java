package BirthdayCelebrations;

public class Robot implements Machine,Identifiable{
    private String id;
    private String model;

    public Robot(String id, String model) {
        this.id = id;
        this.model = model;
    }
@Override
    public String getId() {
        return id;
    }
@Override
    public String getModel() {
        return model;
    }

}
