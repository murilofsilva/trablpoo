package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DataResources {
    static Scanner sc = new Scanner(System.in);
    public static LocalDate getAndValidateDate(String context) {
        final String DEFAULT_ERROR_MESSAGE = "A data informada é inválida! Favor tentar novamente.";

        System.out.println("Para a data solicitada abaixo, utilizar um dos formatos: dd/mm/yyyy; dd-mm-yyyy; yyyy/mm/dd; yyyy-mm-aa");
        System.out.print("Informe a data de nascimento do " + context + ": ");
        String data = sc.nextLine().replace(" ", "");

        if (data.contains("-")) {
            String pattern = "yyyy-MM-dd";
            String pattern2 = "dd-MM-yyyy";

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(pattern2);

            try {
                return LocalDate.parse(data, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(data, dateTimeFormatter2);
                } catch (DateTimeParseException ex) {
                    System.out.println(DEFAULT_ERROR_MESSAGE);
                    getAndValidateDate(context);
                }
            }
        } else {
            String pattern = "yyyy/MM/dd";
            String pattern2 = "dd/MM/yyyy";

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(pattern2);

            try {
                return LocalDate.parse(data, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(data, dateTimeFormatter2);
                } catch (DateTimeParseException ex) {
                    System.out.println(DEFAULT_ERROR_MESSAGE);
                    getAndValidateDate(context);
                }
            }
        }
        return null;
    }
}
