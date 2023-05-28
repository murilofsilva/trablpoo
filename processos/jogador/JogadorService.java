package processos.jogador;

import modelos.Jogador;
import modelos.Localidade;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class JogadorService {
    static Scanner sc = new Scanner(System.in);
    final static ConsoleResources consoleResources = new ConsoleResources();
    final static InscricaoResources inscricaoResources = new InscricaoResources();
    public static List<Jogador> jogadores = new ArrayList<>();

    public static void cadastra() {
        try {
            System.out.println("========= CADASTRO DE JOGADOR ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do Jogador: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("jogador");
            LocalDate dataNascimento = DataResources.getAndValidateDate("jogador");
            String pais = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            String estado = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            String municipio = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            System.out.print("Informe o nome ou id do time que o jogador pertence: ");
            Long idTime = getTime(sc.nextLine());
            String jogo = getJogo();

            Localidade localidade = new Localidade(pais, municipio, estado);
            Jogador jogador = new Jogador(nome, cpfCnpj, dataNascimento, localidade, idTime, jogo);

            jogadores.add(jogador);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um organizador. Entre em contato com o suporte.");
        }
    }

    private static Long getTime(String nameOrId) {
        final Pattern pattern = Pattern.compile("[0-9]+");

        if (pattern.matcher(nameOrId).find()) {
            Long id = Long.valueOf(nameOrId);
            //TODO: busca por id na lista de Jogadores da classe JogadoresService
        } else {
            //TODO: busca por nome na lista de Jogadores da classe JogadoresService
        }

        return 1L;
    }

    private static String getJogo() {
        List<String> jogos = new ArrayList<>();

        {
            jogos.add("Counter Strike");
            jogos.add("Counter Strike GO");
            jogos.add("Valorant");
            jogos.add("Fortnite");
            jogos.add("League of Legends");
            jogos.add("Free Fire");
            jogos.add("FIFA");
        }

        System.out.println("Informe o número correspondente ao jogo relacionado ao jogador;");

        jogos.forEach(j -> {
            int index = jogos.indexOf(j) + 1;
            System.out.println(j + " -> " + index);
        });

        try {
            int escolha = sc.nextInt();
            return jogos.get(escolha - 1);
        } catch (Exception e) {
            System.out.println("Escolha inválida! Tente novamente.");
            getJogo();
        }

        return null;
    }
}
