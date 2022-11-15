package TrafficLights;

public class TrafficLight {
    private Lights lights;

    public TrafficLight(Lights lights) {
        this.lights = lights;
    }

    public Lights getLights(){
        return this.lights;
    }

    public void update() {
        switch (lights) {
            case RED:
                this.lights = Lights.GREEN;
                break;
            case GREEN:
                this.lights = Lights.YELLOW;
                break;
            case YELLOW:
                this.lights = Lights.RED;
                break;
        }
    }
}
