package services.time;

import interfaces.ItemMenu;
import modelos.*;
import repositories.PessoaRepository;
import repositories.TimeRepository;
import services.menu.MenuCadastroService;
import services.pessoa.CoachService;
import services.pessoa.JogadorService;
import util.ConsoleResources;

import java.util.*;
import java.util.stream.Collectors;

public class TimeService implements ItemMenu {
    private static final String SWITCH_ITEM_DEFAULT_MESSAGE = "\nDigite a posição do item que deseja (considere que o primeiro elemento está na primeira posição): ";
    static Scanner sc = new Scanner(System.in);
    public static List<Time> times = new ArrayList<>();
    private static final ConsoleResources consoleResources = new ConsoleResources();
    final CoachService coachService = new CoachService();
    final JogadorService jogadorService = new JogadorService();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("VISUALIZAÇÃO DOS TIMES");
        buscarTimesPorNome();
    }

    private void buscarTimesPorNome() {
        String nomeASerBuscado = consoleResources.getStringFromConsole("Digite um nome para pesquisa do time: ");
        List<Time> timesEncontrados = TimeRepository.obter(nomeASerBuscado);

        if (timesEncontrados.size() == 0) {
            System.out.println("Nenhum time foi encontrado com esse nome! Por favor, tente novamente!");
            buscarTimesPorNome();
        }

        ConsoleResources.pularVariasLinhas();
        exibirListaTimes(timesEncontrados);

        while (true) {
            int idTime = consoleResources.getNumberFromConsole("Digite o ID do time para informações detalhadas: ");
            Time time = TimeRepository.obter(idTime);
            if (time != null) {
                exibirInformacoesDetalhadas(time);
                break;
            }
            System.out.println("Nenhum time foi encontrado com esse ID! Por favor, tente novamente!");
        }
    }

    private void exibirListaTimes(List<Time> times) {
        for (Time time : times) {
            System.out.println(time.obterInformacoes());
        }
    }

    private void exibirInformacoesDetalhadas(Time time) {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("INFORMAÇÕES DETALHADAS DO TIME");
        System.out.println(time.obterInformacoesDetalhadas());
        ConsoleResources.pausarConsole();
    }

    public void criar() {
        List<Coach> coaches = coachService.filtrar(PessoaRepository.pessoas).stream().map(c -> (Coach) c).collect(Collectors.toList());
        if (coaches.isEmpty()) {
            System.out.println("É necessário ter um coach cadastrado para atribui-lo ao time!");
            MenuCadastroService.processaMenuCadastro();
        }

        consoleResources.exibirTitulo("Cadastro de time");
        String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
        int maxJogadores = consoleResources.getNumberFromConsole("Informe o número máximo de jogadores no time: ");
        String pais = consoleResources.getStringFromConsole("Informe o país do time: ");
        String municipio = consoleResources.getStringFromConsole("Informe o município do time: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado do time: ");
        Coach coach = getCoach();
        List<Jogador> jogadores = getJogadores(maxJogadores);
        Localidade localidade = new Localidade(pais, municipio, estado);

        Time time = new Time(2, nome, jogadores, coach, localidade);
        TimeRepository.inserir(time);
    }

    private List<Jogador> getJogadores(int maxJogadores) {
        List<Jogador> jogadoresParaAdicionar = null;

        System.out.print("Gostaria de já adicionar jogadores ao time? ");
        boolean adicionarJogadores = consoleResources.getSimNao(sc.nextLine());

        if (!adicionarJogadores) {
            return Collections.EMPTY_LIST;
        }

        List<Jogador> jogadores = jogadorService.filtrar(PessoaRepository.pessoas).stream().map(pessoa -> (Jogador) pessoa).collect(Collectors.toList());
        if (jogadores.isEmpty()) {
            System.out.println("Impossível adicionar jogadores no momento pois não há nenhum disponível no sistema.");
            return Collections.EMPTY_LIST;
        }

        for (int i = 0; i < maxJogadores; i++) {
            Jogador jogador = getJogador();

            if (Objects.isNull(jogador)) {
                return jogadoresParaAdicionar;
            }

            jogadoresParaAdicionar.add(jogador);
            System.out.println("Jogador " + jogador.getNome() + " adicionado com sucesso!");
        }

        return jogadoresParaAdicionar;
    }

    private Jogador getJogador() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do jogador que deseja adicionar ao time: ");
        Jogador jogador = PessoaRepository.pessoas.stream().map(pessoa -> (Jogador) pessoa)
                .filter(j -> j.getNome().toLowerCase().trim().replace(" ", "")
                        .equals(nome.toLowerCase().trim().replace(" ", ""))).findFirst().orElse(null);

        if (Objects.isNull(jogador)) {
            System.out.println("Jogador não encontrado. Gostaria de tentar novamente? ");
            boolean sim = consoleResources.getSimNao(sc.nextLine());
            if (sim) {
                getJogador();
            }
            return null;
        }

        return jogador;
    }

    private Coach getCoach() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do coach do time, as opções são: " + coachService.filtrar(
                PessoaRepository.pessoas).stream().map(Pessoa::getNome).collect(Collectors.joining(", ")) + ": ");
        List<Pessoa> pessoas = PessoaRepository.obterPorNome(nome);
        pessoas = coachService.filtrar(pessoas);

        List<Pessoa> coachesEncontrados = pessoas.stream().filter(c -> c.getNome().toLowerCase().trim().replace(" ", "")
                .equals(nome.toLowerCase().trim().replace(" ", ""))).collect(Collectors.toList());
        if (coachesEncontrados.isEmpty()) {
            System.out.println("Coach não encontrado! Tente novamente.");
            getCoach();
        }

        /*
            O cenário abaixo jamais deve ocorrer, pois o cadastro de coaches não deve permitir que exista coaches duplicados no sistema.
            Mas caso ocorra alguma falha e isso venha acontecendo, existe essa validação que possibilita um segundo caminho para o usuário.
         */
        if (coachesEncontrados.size() > 1) {
            System.out.print("Foi encontrado mais de um coach para este nome. São eles: " +
                    coachesEncontrados.stream().map(Pessoa::getNome).collect(Collectors.joining(", ")));
            int item = consoleResources.getNumberFromConsole(SWITCH_ITEM_DEFAULT_MESSAGE);

            if (item > coachesEncontrados.size()) {
                System.out.println("Posição inválida! Tente novamente.");
                getCoach();
            }

            return (Coach) coachesEncontrados.get(item - 1);
        }

        return (Coach) coachesEncontrados.get(0);
    }

    public void editar() {

    }
}