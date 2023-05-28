package services.jogo;

import modelos.Jogo;
import modelos.enumerators.ModalidadeEnum;
import util.ConsoleResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JogoService {
    static Scanner sc = new Scanner(System.in);
    public static List<Jogo> jogos = new ArrayList<>();
    private static ConsoleResources consoleResources = new ConsoleResources();

    public static void cadastra() {
        try {
            System.out.println("========= CADASTRO DE JOGO ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do Jogador: ");

            List<ModalidadeEnum> modalidades = Arrays.asList(ModalidadeEnum.values());
            System.out.println("Escolha a modalidade que mais se adequa ao jogo: " + modalidades.toString()
                    .replace("[", "").replace("]", ""));
            String escolha = sc.nextLine();
//            ModalidadeEnum modalidadeEscolhida = ModalidadeEnum.

        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um jogo. Entre em contato com o suporte.");
        }
    }
}
