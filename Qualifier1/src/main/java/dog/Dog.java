package dog;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    public Dog() {
    }

    public void eatingFood() {
        System.out.println("Dog Food");
    }
}
