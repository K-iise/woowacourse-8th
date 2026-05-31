package racingcar.domain;

public class Car {
    private String name;
    private int weight;

    public Car(String name) {
        this.name = name;
    }

    public void addWeight(int number) {
        if (number >= 4) {
            weight++;
        }
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
