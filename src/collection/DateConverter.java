package collection;

import exceptions.InvalidDateFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    /**
     * convert LocalDate to String
     */
    public static String dateToString(LocalTime date){
        return date.format(localDateFormatter);
    }

    /**
     * convert LocalDate to String
     * @param s
     * @return
     * @throws InvalidDateFormatException
     */
    public static LocalTime parseLocalDate(String s) throws InvalidDateFormatException {
        try{
            return LocalTime.parse(s, localDateFormatter);
        }
        catch(java.time.format.DateTimeParseException e){
            throw new InvalidDateFormatException();
        }
    }
}
