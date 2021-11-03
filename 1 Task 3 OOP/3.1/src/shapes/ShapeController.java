package shapes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class ShapeController extends Controller{

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
            System.out.println(shape);
        }
    }

    ArrayList<Shape> shapes = new ArrayList<Shape>();

    ShapeController(){
        ArrayList<Controller.Command> commands = new ArrayList<>();

        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "generate shapes";
                         }

                         @Override
                         public void run() {
                             int size = (int) (Math.random() * 20) + 10;
                             shapes = new ArrayList<Shape>(size);
                             for(int i = 0; i < size; ++i){
                                 Shape s = createRandomShape();
                                 shapes.add(s);
                             }
                         }
                     }
        );
        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "draw";
                         }

                         @Override
                         public void run() {
                             for (Shape shape : shapes) {
                                 shape.draw();
                             }
                         }
                     }
        );
        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "calc sum";
                         }

                         @Override
                         public void run() {
                             double sum = 0;
                             for (Shape shape : shapes) {
                                 sum += shape.calcArea();
                             }
                             System.out.printf("sum: %f\n",sum);
                         }
                     }
        );
        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "calc sum for type";
                         }

                         @Override
                         public void run() {
                             Map<String, Class<?>> map = new HashMap<String, Class<?>>();
                             map.put("c", Circle.class);
                             map.put("t", Triangle.class);
                             map.put("r", Rectangle.class);

                             String id = View.getString("Enter \'c\' - circle; \'t\'-triangle; \'r\' - rectangle");
                             if(map.containsKey(id)){
                                 Class<?> class_object = map.get(id);
                                 double sum = 0;
                                 for (Shape shape : shapes) {
                                     if(shape.getClass() == class_object){
                                         sum += shape.calcArea();
                                     }
                                 }
                                 System.out.printf("sum: %f\n",sum);
                             }else{
                                 System.out.printf("There is no shapes with tag %s\n", id);
                             }
                         }
                     }
        );
        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "sort by area";
                         }

                         @Override
                         public void run() {
                             shapes.sort(Comparator.comparingDouble(o -> o.calcArea()));
                             print(shapes);
                         }
                     }
        );
        commands.add(new Controller.Command() {
                         @Override
                         public String getName() {
                             return "sort by color";
                         }

                         @Override
                         public void run() {
                             shapes.sort(Comparator.comparingInt(o -> o.getColor().getRGB()));
                             print(shapes);
                         }
                     }
        );
        this.setCommands(commands);
    }
}
