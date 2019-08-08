package main;

import com.mysql.cj.core.io.BigDecimalValueFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class testmain {
    public static void main(String[] args) {
        String valor = "1,332,231.42";

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        try {
            Number number = numberFormat.parse(valor);
            BigDecimal valorBigDecimal = new BigDecimal(number.toString());
            System.out.println(valorBigDecimal);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}