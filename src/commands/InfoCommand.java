package commands;

import collection.PersonCollection;
import data.Person;
import exceptions.EmptyCollectionException;

public class InfoCommand extends Command{
    protected String nameOfCommand = "info";
    PersonCollection collection;

    public InfoCommand(){
        super("info");
    }

    @Override
    public void getResult() {
        if (collection.getCollection()==null) throw new EmptyCollectionException();
        if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
        System.out.println(collection.getInfo());
    }
}
