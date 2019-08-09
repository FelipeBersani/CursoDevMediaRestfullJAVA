package main;

import main.rest.MesesEnum;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class testmain {
    public static void main(String[] args) {
        String data = "21/Feb";
        data = data.replaceAll("\\s+", "");
        data = data.replace("-", "/");
        String[] identificaMes = data.split("/");
        Locale locale;

        LocalDate localDate;
        locale = MesesEnum.mesLocale(identificaMes[1].toLowerCase());

        localDate = LocalDate.parse(data.concat("/2019"), new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("dd/MMM/yyyy"))
                .toFormatter().withLocale(locale));
        System.out.println(localDate.toString());

    }
}