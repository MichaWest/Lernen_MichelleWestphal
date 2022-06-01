package commands;

import java.io.Serializable;
import java.util.List;

public abstract class Command implements Serializable {
    protected String nameOfCommand = "";
    protected History history;

    public abstract void execute();

    public String getNameOfCommand(){
        return nameOfCommand;
    }

}
