package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscricaoResources {
    static Scanner sc = new Scanner(System.in);

    public String getAndValidateCpfCnpj(String context) {
        System.out.print("Informe o CPF/CNPJ do " + context + ": ");
        String str = sc.nextLine();

        str = str.replace(".", "").replace("-","");
        Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find() || str.length() != 11 && str.length() != 14) {
            System.out.println("Cpf ou cnpj inválido! Tente novamente.");
            getAndValidateCpfCnpj(context);
        }

        return str;
    }
}
