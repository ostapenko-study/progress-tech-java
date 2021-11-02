package books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    static void print(Object[] objects){
        if(objects.length == 0){
            System.out.println("There are no objects");
        }else{
            for (Object o : objects) {
                System.out.println(o);
            }
        }
    }

    interface Command{
        String getName();
        void run();
    }

    boolean isExit = false;
    final BookManager bm = new BookManager();
    final ArrayList<Command> commands = new ArrayList<>();
    Controller(){
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "Generated books";
                         }

                         @Override
                         public void run() {
                             bm.generateBooks();
                             print(bm.getBooks().toArray());
                         }
                     }
        );
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "get by author";
                         }

                         @Override
                         public void run() {
                             print(bm.getAuthor(View.getString("author")).toArray());
                         }
                     }
        );
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "get by vendor";
                         }

                         @Override
                         public void run() {
                             print(bm.getVendor(View.getString("vendor")).toArray());
                         }
                     }
        );
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "get after year";
                         }

                         @Override
                         public void run() {
                             print(bm.getAfterYear(View.getInt("year")).toArray());
                         }
                     }
        );
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "sort by vendor";
                         }

                         @Override
                         public void run() {
                             print(bm.sortedBy("vendor").toArray());
                         }
                     }
        );
        commands.add(new Command() {
                         @Override
                         public String getName() {
                             return "exit";
                         }

                         @Override
                         public void run() {
                             System.out.println("thank you!");
                             isExit = true;
                         }
                     }
        );
    }

    void run(){
        String[] command_names = new String[commands.size()];
        for(int i = 0; i < command_names.length; ++i){
            command_names[i] = commands.get(i).getName();
        }
        while(!isExit){
            int commandId = View.choice(command_names);
            commands.get(commandId).run();
        }
    }
}
