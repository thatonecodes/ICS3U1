package Factory;

import utils.CircleTerminal;

interface Shape {
    void draw();
}

class Circle implements Shape {

    public void draw() {
        System.out.println("Drawing a Circle");
        CircleTerminal.draw();
    }
}

class ShapeFactory {

    public static Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }
        return null;
    }
}

public class FactoryClass {

    public static void main(String[] args) {
        //testing the factory class
        Shape shape = ShapeFactory.getShape("CIRCLE");
        shape.draw();
    }

    public void create(String type) {
        Shape shape = ShapeFactory.getShape(type);
        shape.draw();
    }
}
