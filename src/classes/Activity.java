package classes;

import interfaces.ActivityInterface;

public class Activity implements ActivityInterface {
    private String name;

    public Activity(String setName) {
        this.name = setName;
    }

    @Override
    public String GetName() {
        return name;
    }
}
