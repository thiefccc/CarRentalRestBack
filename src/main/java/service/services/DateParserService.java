package service.services;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Service
final public class DateParserService{
    private static String pattern = "dd-MM-yyyy HH:mm";

    static Date toDate(String stringDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(stringDate);
    }

    static Date toDate(String stringDate, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(stringDate);
    }

    static Date nowDateTime() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String now = dateFormat.format(new Date());
        return dateFormat.parse(now);
    }
}
