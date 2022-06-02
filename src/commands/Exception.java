package commands;

public class Exception extends Command{
    protected String nameOfCommand = "exception";

    public Exception(){
        super("exception");
    }


    @Override
    public void getResult() {
        System.out.println("На сервере возникла ошибка");
    }
}
