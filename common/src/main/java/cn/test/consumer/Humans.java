package cn.test.consumer;

import java.util.List;
import java.util.function.Consumer;

public class Humans {
    private List<Human> humans;

    public Humans(List<Human> humans) {
        this.humans = humans;
    }

    public void say(Consumer<Human> human) {
        this.humans.forEach(human);
    }

}
