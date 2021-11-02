package shapes;


import javax.management.BadAttributeValueExpException;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Collections;

public class Triangle extends Shape {
    Point2D a,b,c;
    double d1,d2,d3;

    public boolean setVertexes(Point2D a, Point2D b, Point2D c){
        d1 = a.distance(b);
        d2 = a.distance(c);
        d3 = b.distance(c);

        double max = Collections.max(Arrays.asList(d1,d2,d3));

        if(max >= d1+d2+d3-max){
            return false;
        }

        this.a = a;
        this.b = b;
        this.c = c;

        return true;
    }

    @Override
    public double calcArea() {
        double p = (d1+d2+d3) / 2;
        return Math.sqrt(p*(p-d1)*(p-d2)*(p-d3));
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" with parameters: position of vertexes %s %s %s", a, b, c);
    }
}
