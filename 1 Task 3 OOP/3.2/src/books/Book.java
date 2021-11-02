package books;

public class Book {

    public String name;
    public String author;
    public String vendor;
    public int year;
    public int pages;
    public double coast;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", vendor='" + vendor + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", coast=" + coast +
                '}';
    }
}
