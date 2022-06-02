package commands;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryCommand extends Command implements Serializable{
    private ArrayList<String> history;
    protected String nameOfCommand = "history";

    public HistoryCommand(){
        super("history");
        history = new ArrayList<>();
    }

    @Override
    public void getResult() {

    }

}
