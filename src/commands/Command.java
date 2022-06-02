package commands;

import collection.PersonCollection;
import data.Person;

import java.io.Serializable;

public abstract class Command implements Serializable {
    protected String nameOfCommand;
    protected HistoryCommand history;
    protected PersonCollection collection;

    public Command(String name){
        nameOfCommand = name;
    }

    public String getNameOfCommand(){
        return nameOfCommand;
    }

    public abstract void getResult();

    public PersonCollection getCollection(){
        return collection;
    }

}
