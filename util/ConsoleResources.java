package util;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleResources {
    private static final Scanner sc = new Scanner(System.in);
    public static final String modelos = "01 - AUXILIAR" +
                                        "\n02 - CAMPEONATO" +
                                        "\n03 - COACH" +
                                        "\n04 - GERENTE" +
                                        "\n05 - JOGADOR" +
                                        "\n06 - JOGO" +
                                        "\n07 - ORGANIZADOR" +
                                        "\n08 - TIME\n";

    public static void pausarConsole() {
        System.out.print("Tecle para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.print("");
        }

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

    public double getDoubleFromConsole(String mensagem) {
        double valor;
        try {
            System.out.print(mensagem);
            valor = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("O valor inserido deve ser um número!");
            return getDoubleFromConsole(mensagem);
        }
        return valor;
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

    public static void exibirTitulo(String titulo) {
        int tamanhoLinhaDivisoria = 50;
        String linhaDivisoria = new String(new char[tamanhoLinhaDivisoria]).replace("\0", "*");
        int tamanhoMetadeLinhaDivisoria = (tamanhoLinhaDivisoria - 2 - titulo.length()) / 2;
        String metadeLinhaDivisoria = new String(new char[tamanhoMetadeLinhaDivisoria]).replace("\0", "*");

        System.out.println(linhaDivisoria);
        System.out.println(metadeLinhaDivisoria + " " + titulo.toUpperCase() + " " + metadeLinhaDivisoria);
        System.out.println(linhaDivisoria);
        System.out.println();
    }
}