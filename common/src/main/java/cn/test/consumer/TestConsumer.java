package cn.test.consumer;

import java.util.Arrays;

public class TestConsumer {
    public static void main(String[] args) {
        Human human1 = new Human("小燕子",15);
        Human human2 = new Human("紫薇",16);

        Humans humans = new Humans(Arrays.asList(human1,human2));
        humans.say(human -> human.sayHello());
        humans.say(human -> human.sayYear());
    }
}
