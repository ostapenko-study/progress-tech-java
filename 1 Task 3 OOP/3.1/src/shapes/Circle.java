package shapes;

public class Circle extends Shape {

    double R;

    public void setR(double R){
        this.R = R;
    }

    @Override
    public double calcArea() {
        return Math.PI*R*R;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" with R = %f", R);
    }
}
