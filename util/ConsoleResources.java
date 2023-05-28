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

    public String getStringFromConsole(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine();
    }

    public static void pularVariasLinhas() {
        System.out.print("\n\n\n\n\n");
    }
}
