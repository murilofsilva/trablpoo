package util;

import java.util.Scanner;

public class ConsoleResources {
    private static final Scanner sc = new Scanner(System.in);

    public static void pausarConsole() {
        System.out.println("Tecle para continuar...");
        String discard = sc.nextLine();
        pularVariasLinhas();
    }

    public int getNumberFromConsole() {
        int opcao;
        try {
            System.out.print("Informe a opção: ");
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("A opcão escolhida deve ser um número!");
            return getNumberFromConsole();
        }

        return opcao;
    }

    public int getNumberFromConsole(String mensagem) {
        int opcao;
        try {
            System.out.print(mensagem);
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("O valor inserido deve ser um número!");
            return getNumberFromConsole(mensagem);
        }

        return opcao;
    }

    public String getStringFromConsole(String str) {
        System.out.print(str);
        return sc.nextLine();
    }

    public boolean getSimNao(String str) {
        if (str.contains("s")) {
            return true;
        }
        return false;
    }

    public static void pularVariasLinhas() {
        System.out.println("\n\n\n\n");
    }
}
