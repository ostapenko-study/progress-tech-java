package shapes;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {


    interface Command{
        String getName();
        void run();
    }

    private boolean isExit = false;
    private ArrayList<Command> commands = new ArrayList<>();


    final void setCommands(ArrayList<Command> commands){
        this.commands = commands;
        this.commands.add(new Command() {
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

    final void run(){
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
