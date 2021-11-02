package shapes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static Color generateRandomColor(){
        return new Color(
                (float) Math.random(),
                (float) Math.random(),
                (float) Math.random()
        );
    }

    static Circle createRandomCircle(){
        Circle c = new Circle();
        c.setColor(generateRandomColor());
        c.setR(Math.random() * 100);
        return c;
    }

    static Rectangle createRandomRectangle(){
        Rectangle r = new Rectangle();
        r.setColor(generateRandomColor());
        r.setWidth(Math.random() * 100);
        r.setHeight(Math.random() * 100);
        r.setPosition(Math.random() * 100, Math.random() * 100);

        return r;
    }

    static Triangle createRandomTriangle(){
        Triangle t = new Triangle();
        t.setColor(generateRandomColor());
        if(!t.setVertexes(  new Point2D.Double(Math.random() * 10, Math.random() * 10),
                    new Point2D.Double(Math.random() * 10, Math.random() * 10),
                    new Point2D.Double(Math.random() * 10, Math.random() * 10))
        ) {
            t.setVertexes(new Point2D.Double(0,0),
                    new Point2D.Double(1,1),
                    new Point2D.Double(0, 1));
        }
        return t;
    }

    interface ICreatorShape{
        Shape create();
    }

    static Shape createRandomShape(){
        ICreatorShape[] creators = new ICreatorShape[]{
                () -> createRandomCircle(),
                () -> createRandomRectangle(),
                () -> createRandomTriangle()
        };
        return creators[(int) ((Math.random()-0.01) * (creators.length))].create();
    }

    static void print(ArrayList<Shape> shapes){
        for (Shape shape : shapes) {
            System.out.printf("%s\n", shape);
        }
    }

    public static void main(String[] args) {
        int size = (int) (Math.random() * 20) + 10;
        ArrayList<Shape> shapes = new ArrayList<Shape>(size);
        double sum = 0;
        for(int i = 0; i < size; ++i){
            Shape s = createRandomShape();
            shapes.add(s);
            s.draw();
            double area = s.calcArea();
            System.out.printf("area: %f\n", area);
            sum += area;
        }
        System.out.printf("sum: %f", sum);

        System.out.printf("\nSORT BY AREA\n");
        Collections.sort(shapes, Comparator.comparingDouble(o -> o.calcArea()));
        print(shapes);

        System.out.printf("\nSORT BY COLOR\n");
        Collections.sort(shapes, Comparator.comparingInt(o -> o.getColor().getRGB()));
        print(shapes);

    }
}
