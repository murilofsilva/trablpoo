import processos.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        new Menu().switchOpcao(sc.nextInt());
    }
}
