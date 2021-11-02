package shapes;

import javax.management.BadAttributeValueExpException;

public class Rectangle extends Shape {

    double width, height;
    double x, y;

    public void setWidth(double width){
        this.width = width > 0 ? width : -width;
    }

    public void setHeight(double height) {
        this.height = height > 0 ? height : -height;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double calcArea() {
        return height * width;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" with parameters: position (%f %f) size (%f %f)", x, y, width, height);
    }
}
