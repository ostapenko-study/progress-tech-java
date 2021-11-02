package shapes;

import java.awt.Color;

public abstract class Shape implements Drawable {

    public abstract double calcArea();

    @Override
    public void draw() {
        System.out.printf("\nNow, you see %s\n", this.toString());
    }

    @Override
    public String toString() {
        return String.format("%s (area: %s) (color : %s)", this.getClass(), calcArea(), color);
    }

    void setColor(Color color){
        this.color = color;
    }
    Color getColor(){return color;}

    Color color = Color.white;

}
