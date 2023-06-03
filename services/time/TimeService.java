package services.time;

import interfaces.ItemMenu;
import modelos.Coach;
import modelos.Jogador;
import modelos.Localidade;
import modelos.Time;
import repositories.TimeRepository;
import services.menu.MenuCadastroService;
import util.ConsoleResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static services.pessoa.CoachService.coaches;
import static services.pessoa.JogadorService.jogadores;

public class TimeService implements ItemMenu {
    static Scanner sc = new Scanner(System.in);
    public static List<Time> times = new ArrayList<>();
    private static final ConsoleResources consoleResources = new ConsoleResources();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("****************************************");
        System.out.println("******** VISUALIZAÇÃO DOS TIMES ********");
        System.out.println("****************************************");

        int opcaoEscolhida = consoleResources
                .getNumberFromConsole("Deseja buscar o time por (1) código ou por (2) nome? (1 ou 2) ");

        if (opcaoEscolhida == 1) {
            int codigo = consoleResources.getNumberFromConsole("Informe o código do time: ");
            Time time = TimeRepository.obter(codigo);
            ConsoleResources.pularVariasLinhas();
            ConsoleResources.exibirTitulo("informações do time");
            System.out.print(time.obterInformacoesDetalhadas() + "\n");
            ConsoleResources.pausarConsole();
        }

        String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
        TimeRepository.
    }

    public void criar() {
        if (coaches.size() == 0) {
            System.out.println("É necessário ter um coach cadastrado para atribui-lo ao time!");
            MenuCadastroService.processaMenuCadastro();
        }

        System.out.println("========= CADASTRO DE TIME ==========");
        String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
        int maxJogadores = consoleResources.getNumberFromConsole("Informe o número máximo de jogadores no time");
        String pais = consoleResources.getStringFromConsole("Informe o país do time: ");
        String municipio = consoleResources.getStringFromConsole("Informe o município do time: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado do time: ");
        Coach coach = getCoach();
        List<Jogador> jogadores = getJogadores(true, maxJogadores);
        Localidade localidade = new Localidade(pais, municipio, estado);

        Time time = new Time(2, nome, jogadores, coach);
        times.add(time);
    }

    private List<Jogador> getJogadores(boolean firstExec, int maxJogadores) {
        if (firstExec) {
            System.out.print("Gostaria de já adicionar jogadores ao time? ");
            boolean adicionarJogadores = consoleResources.getSimNao(sc.nextLine());

            if (!adicionarJogadores) {
                return null;
            }
        }

        List<Jogador> jogadoresParaAdicionar = new ArrayList<>();
        if (jogadores.size() == 0 || jogadores.stream().filter(f -> Objects.nonNull(f.getTime())).toList().isEmpty()) {
            System.out.println("Não há jogadores disponíveis no momento.");
            return null;
        }

        String nome = consoleResources.getStringFromConsole("Informe o nome do jogador que deseja adicionar ao time: ");
        Jogador jogador = jogadores.stream().filter(j -> j.getNome().equals(nome)).findFirst().orElse(null);

        if (Objects.isNull(jogador)) {
            System.out.print("Jogador não encontrado. Gostaria de tentar novamente? ");
            boolean sim = consoleResources.getSimNao(sc.nextLine());
            if (sim) {
                getJogadores(false, maxJogadores);
            }
            return jogadoresParaAdicionar;
        }

        jogadoresParaAdicionar.add(jogador);
        System.out.println("Jogador adicionado com sucesso!");

        if (jogadoresParaAdicionar.size() < maxJogadores) {
            System.out.print("Gostaria de adicionar outro? ");
            boolean sim = consoleResources.getSimNao(sc.nextLine());
            if (sim) {
                getJogadores(false, maxJogadores);
            }
        }

        return jogadoresParaAdicionar;
    }

    private Coach getCoach() {
        System.out.print("Informe o nome do coach do time: ");
        String nome = sc.nextLine();

        List<Coach> coach = coaches.stream().filter(c -> c.getNome().equals(nome)).toList();
        if (coach.size() == 0) {
            System.out.println("Coach não encontrado! Tente novamente.");
            getCoach();
        }

        return coach.get(0);
    }
}