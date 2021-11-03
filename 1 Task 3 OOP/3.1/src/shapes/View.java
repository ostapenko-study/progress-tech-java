package shapes;

import java.util.Scanner;

public class View {
    static Scanner scanner = new Scanner(System.in);

    static int choice(String[] commands){
        for(;;){
            for(int i = 0; i < commands.length; ++i){
                System.out.printf("[%d] %s\n", i, commands[i]);
            }

            System.out.println("Enter command:");
            int n = scanner.nextInt();

            if(n >=0 && n < commands.length){
                return n;
            }

            System.out.println("incorrect command, try again");

        }
    }

    static int getInt(String name){
        System.out.printf("Enter %s: ",name);
        return scanner.nextInt();
    }

    static String getString(String name){
        System.out.printf("Enter %s: ",name);
        return scanner.next();
    }
}
