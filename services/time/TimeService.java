package services.time;

import interfaces.ItemMenu;
import modelos.Coach;
import modelos.Jogador;
import modelos.Pessoa;
import modelos.Time;
import repositories.PessoaRepository;
import repositories.TimeRepository;
import services.MenuService;
import services.menu.MenuCadastroService;
import services.pessoa.CoachService;
import services.pessoa.JogadorService;
import util.ConsoleResources;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TimeService implements ItemMenu {
    private static final String SWITCH_ITEM_DEFAULT_MESSAGE = "\nDigite a posição do item que deseja (considere que o primeiro elemento está na primeira posição): ";
    private static final Scanner sc = new Scanner(System.in);
    private static final ConsoleResources consoleResources = new ConsoleResources();
    private static final CoachService coachService = new CoachService();
    private static final JogadorService jogadorService = new JogadorService();
    private static final PessoaRepository pessoaRepository = new PessoaRepository();
    private static final TimeRepository timeRepository = new TimeRepository();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização dos times");
        buscarTimesPorNome();
    }

    private List<Time> buscarTimesPorNome() {
        String nomeASerBuscado = consoleResources.getStringFromConsole("Digite um nome para pesquisa do time: ");
        List<Time> timesEncontrados = timeRepository.obter(nomeASerBuscado);

        if (timesEncontrados.size() == 0) {
            System.out.println("Nenhum time foi encontrado com esse nome! Por favor, tente novamente!");
            buscarTimesPorNome();
        }

        ConsoleResources.pularVariasLinhas();
        exibirListaTimes(timesEncontrados);

        while (true) {
            int idTime = consoleResources.getNumberFromConsole("Digite o ID do time para informações detalhadas: ");
            Time time = timeRepository.obter(idTime);
            if (time != null) {
                exibirInformacoesDetalhadas(time);
                break;
            }
            System.out.println("Nenhum time foi encontrado com esse ID! Por favor, tente novamente!");
        }

        return timesEncontrados;
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
        List<Coach> coaches = coachService.filtrar(pessoaRepository.obterTodos()).stream().map(c -> (Coach) c).collect(Collectors.toList());
        if (coaches.isEmpty()) {
            System.out.println("É necessário ter um coach cadastrado para atribui-lo ao time!");
            MenuCadastroService.processaMenuCadastro();
        }

        ConsoleResources.exibirTitulo("Cadastro de time");
        String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
        int maxJogadores = consoleResources.getNumberFromConsole("Informe o número máximo de jogadores no time: ");
        Coach coach = getCoach();
        List<Jogador> jogadores = getJogadores(maxJogadores);

        Time time = new Time(2, nome, jogadores, coach);
        timeRepository.salvar(time);
    }

    private List<Jogador> getJogadores(int maxJogadores) {
        List<Jogador> jogadoresParaAdicionar = null;

        System.out.print("Gostaria de já adicionar jogadores ao time? ");
        boolean adicionarJogadores = consoleResources.getSimNao(sc.nextLine());

        if (!adicionarJogadores) {
            return Collections.EMPTY_LIST;
        }

        List<Jogador> jogadores = jogadorService.filtrar(pessoaRepository.obterTodos()).stream().map(pessoa -> (Jogador) pessoa).collect(Collectors.toList());
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
        Jogador jogador = pessoaRepository.obterTodos().stream().map(pessoa -> (Jogador) pessoa)
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
                pessoaRepository.obterTodos()).stream().map(Pessoa::getNome).collect(Collectors.joining(", ")) + ": ");
        List<Pessoa> pessoas = pessoaRepository.obterPorNome(nome);
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
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição de time");

        Time time;
        while (true) {
            time = buscarTimesPorNome().get(0);
            if (Objects.isNull(time)) {
                System.out.println("Nenhum time encontrado com esse nome!");
                continue;
            }
            break;
        }

        System.out.println("\nNome do time: " + time.getNome());
        while (true) {
            System.out.println("Opções de edição:");
            System.out.println("01 - Salário base");
            System.out.println("02 - Setor gerenciado");
            System.out.println("03 - Funcionários gerenciados");
            int opcao = consoleResources.getNumberFromConsole("O que deseja editar? ");
            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    editarNome(time);
                    break;
                case 2:
                    editarListaDeJogadores(time);
                    break;
                case 3:
                    editarCoach(time);
                    break;
                default:
                    break;
            }

            System.out.println("Edição realizada com sucesso!");
            ConsoleResources.pausarConsole();
        }
    }

    private void editarNome(Time time) {
        String nome = consoleResources.getStringFromConsole("Informe o novo nome do time: ");
        if (!timeRepository.obterTodos().stream().map(Time::getNome).filter(n -> n.equals(nome)).collect(Collectors.toList()).isEmpty()) {
            System.out.println("Nome já cadastrado! Tente novamente.");
            editarNome(time);
        }
        time.setNome(nome);
    }

    private void editarListaDeJogadores(Time time) {
        int acao = consoleResources.getNumberFromConsole("Qual ação gostaria de realizar?\n01 - Adicionar jogador\n02 - Remover jogador");
        switch (acao) {
            case 1:
                adicionarJogador(time);
                break;
            case 2:
                removerJogador(time);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                editarListaDeJogadores(time);
        }
    }

    private void adicionarJogador(Time time) {
        if (time.getJogadores().size() == time.getNumeroIntegrantes() - 1) {
            System.out.println("Time cheio, impossível adicionar jogadores!");
            MenuService.processaMenu();
        }
        jogadorService.criar();
        List<Pessoa> pessoas = pessoaRepository.obterTodos();
        Jogador jogador = (Jogador) jogadorService.filtrar(pessoas).get(pessoas.size() - 1);
        time.addJogador(jogador);
    }

    private void removerJogador(Time time) {
        String username = consoleResources.getStringFromConsole("Informe o nome de usuário do jogador que deseja remover: ");
        Jogador jogador = time.getJogadores().stream().filter(u -> u.getNomeUsuario().equals(username)).findFirst().orElse(null);
        time.getJogadores().remove(jogador);
    }

    private void editarCoach(Time time) {
        String nome = consoleResources.getStringFromConsole("Informe o nome do novo coach: ");
        List<Coach> coaches = coachService.filtrar(pessoaRepository.obterTodos()).stream().map(c -> (Coach) c).collect(Collectors.toList());
        Coach coach = coaches.stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
        if (Objects.isNull(coach)) {
            System.out.println("Coach não encontrado! Tente novamente.");
            editarCoach(time);
        }
        time.setCoach(coach);
    }

    public void remover() {
        String nome = consoleResources.getStringFromConsole("Qual o nome do time que deseja remover? ");
        Time time = timeRepository.obterTodos().stream().filter(t -> t.getNome().equals(nome)).findFirst().orElse(null);
        if (Objects.isNull(time)) {
            System.out.println("Time não encontrado! Tente novamente.");
            remover();
        }
        timeRepository.remover(time);
        System.out.println("Remoção realizada com sucesso!");
        ConsoleResources.pausarConsole();
    }
}