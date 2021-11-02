package books;

import java.util.Random;

public class Generator {
    protected static final Random generator = new Random();

    static int getInt(int max){
        return generator.nextInt(max);
    }

}
