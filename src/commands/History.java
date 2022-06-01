package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {
    static private ArrayList<String> history;

    public History(){
        history = new ArrayList<>();
    }

    public void addCommand(String command){
        if(history.size()<7){
            history.add(command);
        }
        if(history.size()==7){
            history.remove(0);
            history.add(command);
        }
    }

    public void printHistory(){
        for(String command: history){
            System.out.println(command);
        }
    }

}
