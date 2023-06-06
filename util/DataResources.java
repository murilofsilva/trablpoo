package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DataResources {
    static Scanner sc = new Scanner(System.in);
    public static LocalDate getAndValidateDate(String mensagem) {
        final String DEFAULT_ERROR_MESSAGE = "A data informada é inválida! Favor tentar novamente.";

        System.out.println("Para a data solicitada abaixo, utilizar um dos formatos: dd/mm/yyyy; dd-mm-yyyy; yyyy/mm/dd; yyyy-mm-aa");
        System.out.print(mensagem);
        String data = sc.nextLine().replace(" ", "");

        String pattern;
        String pattern2;

        if (data.contains("-")) {
            pattern = "yyyy-MM-dd";
            pattern2 = "dd-MM-yyyy";
        } else {
            pattern = "yyyy/MM/dd";
            pattern2 = "dd/MM/yyyy";
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(pattern2);

        try {
            return LocalDate.parse(data, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(data, dateTimeFormatter2);
            } catch (DateTimeParseException ex) {
                System.out.println(DEFAULT_ERROR_MESSAGE);
                getAndValidateDate(mensagem);
            }
        }

        return null;
    }
}
