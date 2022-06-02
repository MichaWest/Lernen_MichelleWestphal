package commands;

import collection.PersonCollection;
import data.Person;
import exceptions.*;

public class ShowCommand extends Command{
    protected String nameOfCommand = "show";
    PersonCollection collection;

    public ShowCommand(){
        super("show");
    }

    @Override
    public void getResult() {
        if (collection.getCollection()==null) throw new EmptyCollectionException();
        if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
        System.out.println(collection.serializeCollection());
    }
}
