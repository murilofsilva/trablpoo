package services.pessoa;

import modelos.*;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static services.time.TimeService.times;

public class JogadorService extends PessoaService {
    private static final Scanner sc = new Scanner(System.in);
    private final static ConsoleResources consoleResources = new ConsoleResources();
    final static InscricaoResources inscricaoResources = new InscricaoResources();
    public static List<Jogador> jogadores = new ArrayList<>();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("************** VISUALIZAÇÃO DO JOGADOR ***************");

        while(true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> jogadores = filtrar(pessoas);
            if (jogadores.size() > 0) {
                imprimirPessoasEncontradas(jogadores);
                break;
            }
            System.out.println("Nenhum jogador encontrado com esse nome!");
        }

        while (true) {
            Pessoa jogador = buscarPessoaPorCPF();
            if (jogador != null) {
                imprimirInformacoesPessoa(jogador);
                break;
            }
            System.out.println("Nenhum jogador encontrado com esse CPF!");
        }
    }

    public void criar() {
        try {
            System.out.println("========= CADASTRO DE JOGADOR ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do Jogador: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("jogador");
            LocalDate dataNascimento = DataResources.getAndValidateDate("jogador");
            String pais = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            String estado = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            String municipio = consoleResources.getStringFromConsole("Informe o país do jogador: ");
            System.out.print("Informe o nome ou id do time que o jogador pertence: ");
            Time time = getTime();
            String jogo = getJogo();

            Localidade localidade = new Localidade(pais, municipio, estado);
            Jogador jogador = new Jogador(nome, cpfCnpj, dataNascimento, localidade, jogo);

            jogadores.add(jogador);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um jogador. Entre em contato com o suporte.");
        }
    }

    public void editar() {

    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> jogadores = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Jogador) jogadores.add(pessoa);
        });
        return jogadores;
    }

    private static Time getTime() {
        System.out.print("Informe o nome do time do jogador (caso não possua, apenas avance): ");
        String time = sc.nextLine();

        if (Objects.isNull(time) || time.isEmpty()) {
            return null;
        }

        List<Time> timesEncontrados = times.stream().filter(t -> t.getNome().equals(time)).collect(Collectors.toList());
        if (timesEncontrados.size() == 0) {
            System.out.println("Time não encontrado! Tente novamente.");
            getTime();
        }

        return timesEncontrados.get(0);
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
