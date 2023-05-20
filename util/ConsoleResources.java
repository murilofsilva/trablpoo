package util;

import java.util.Scanner;

public class ConsoleResources {
    private static final Scanner sc = new Scanner(System.in);

    public static void pausarConsole() {
        System.out.println("Tecle para continuar...");
        String discard = sc.nextLine();
    }
}
