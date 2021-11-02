package books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookManager {

    static final String[] names = {"HP1", "HP2", "HP4", "HP6", "Good book", "Bad book", "My book"};
    static final String[] authors = {"a0", "a1", "a2","a3"};
    static final String[] vendors = {"v0", "v1", "v2"};

    public static Book generateBook(){
        Book b = new Book();
        b.name = names[Generator.getInt(names.length)];
        b.author = authors[Generator.getInt(authors.length)];
        b.vendor = vendors[Generator.getInt(vendors.length)];
        b.year = 1990 + Generator.getInt(30);
        b.pages = 20 + Generator.getInt(300);
        b.coast = Math.random()*200;
        return b;
    }

    protected List<Book> books = new ArrayList<Book>();

    List<Book> getBooks(){
        return books;
    }

    void generateBooks(){
        int size = Generator.getInt(10) + 10;
        books.clear();

        for(int i = 0; i < size; ++i){
            books.add(generateBook());
        }
    }

    List<Book> getAuthor(String author){
        return filter(book -> book.author.equals(author));
    }

    List<Book> getVendor(String vendor){
        return filter(book -> book.vendor.equals(vendor));
    }

    List<Book> getAfterYear(int year){
        return filter(book -> book.year > year);
    }

    List<Book> sortedBy(String field){
        if(field.equals("vendor")){
            List<Book> copy = new ArrayList<Book>(books);
            copy.sort((o1, o2) -> o1.vendor.compareTo(o2.vendor));
            return copy;
        }
        return null;
    }

    private List<Book> filter(Predicate<Book> predicate){
        return books.stream().filter(predicate).collect(Collectors.toList());
    }

}
