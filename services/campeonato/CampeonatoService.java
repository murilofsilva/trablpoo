package services.campeonato;

import modelos.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import interfaces.ItemMenu;
import modelos.Campeonato;
import modelos.Jogo;
import modelos.Localidade;
import repositories.CampeonatoRepository;
import repositories.JogoRepository;
import repositories.TimeRepository;
import util.ConsoleResources;
import util.DataResources;

public class CampeonatoService implements ItemMenu {
    private static final ConsoleResources consoleResources = new ConsoleResources();
    private static final CampeonatoRepository campeonatoRepository = new CampeonatoRepository();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização dos campeonatos");
        List<Campeonato> campeonatos;

        while (true) {
            String nomePesquisado = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
            campeonatos = campeonatoRepository.obter(nomePesquisado);
    
            if (campeonatos.size() > 0)
                break;

            System.out.println("Nenhum campeonato encontrado com esse nome!");
        }

        imprimirListagemCampeonatos(campeonatos);
        int id = consoleResources.getNumberFromConsole("Informe o ID do campeonato para visualizar informações detalhadas: ");

        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getId() == id) {
                ConsoleResources.pularVariasLinhas();
                ConsoleResources.exibirTitulo("informações do campeonato");
                System.out.println(campeonato.obterInformacoesDetalhadas());
                ConsoleResources.pausarConsole();
            }
        }
    }

    private static void imprimirListagemCampeonatos(List<Campeonato> campeonatos) {
        for (Campeonato campeonato : campeonatos) {
            System.out.println(campeonato.obterInformacoes());
        }
    }

    public void criar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro de campeonato");

        String nome = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
        LocalDate dataInicio = DataResources.getAndValidateDate("Informe a data de início do campeonato: ");
        LocalDate dataFim = DataResources.getAndValidateDate("Informe a data de fim do campeonato: ");
        String pais = consoleResources.getStringFromConsole("Informe o país em que ocorrerá o campeonato: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado em que ocorrerá o campeonato: ");
        String municipio = consoleResources.getStringFromConsole("Informe o município em que ocorrerá o campeonato: ");
        Jogo jogo;

        while (true) {
            String nomeJogo = consoleResources.getStringFromConsole("Informe o nome do jogo do campeonato: ");
            jogo = JogoRepository.obterPorNome(nomeJogo).get(0);
            
            if (jogo != null)
                break;

            System.out.println("Nenhum jogo encontrado com esse nome!");
        }

        Localidade localizacao = new Localidade(pais, estado, municipio);
        Campeonato campeonato = new Campeonato(nome, dataInicio, dataFim, localizacao, new ArrayList<Time>(), jogo);
        campeonatoRepository.salvar(campeonato);
        System.out.println("Campeonato cadastrado com sucesso!");
        ConsoleResources.pausarConsole();
    }

    public void editar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição do campeonato");

        Campeonato campeonato;
        while (true) {
            String nome = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
            campeonato = campeonatoRepository.obter(nome).get(0);
            if (campeonato != null)
                break;
            System.out.println("Nenhum campeonato encontrado com esse nome!");
        }

        System.out.println("\nNome do campeonato: " + campeonato.getNome());

        while (true) {
            System.out.println("Opções de edição:");
            System.out.println("01 - Nome");
            System.out.println("02 - Data de fim");
            System.out.println("03 - Adicionar time");
            System.out.println("04 - Remover time");
            int opcao = consoleResources.getNumberFromConsole("O que deseja editar? ");
            if (opcao == 0) break;

            switch (opcao) {
                case 1: editarNome(campeonato); break;
                case 2: editarDataFim(campeonato); break;
                case 3: adicionarTime(campeonato); break;
                case 4: removerTime(campeonato); break;
                default: break;
            }

            System.out.println("Edição realizada com sucesso!");
            ConsoleResources.pausarConsole();
        }
    }

    private static void editarNome(Campeonato campeonato) {
        String nome = consoleResources.getStringFromConsole("Informe o novo nome: ");
        boolean jaExiste = !campeonatoRepository.obter(nome).isEmpty();
    
        if (jaExiste) {
            System.out.println("Já existe um campeonato com esse nome!");
            editarNome(campeonato);
        }
    
        campeonato.setNome(nome);        
    }
    
    private static void editarDataFim(Campeonato campeonato) {
        LocalDate dataFim = DataResources.getAndValidateDate("Informe a nova data de fim: ");
        campeonato.setDataFim(dataFim);
    }
    
    private static void adicionarTime(Campeonato campeonato) {
        Time time;
        while (true) {
            String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
            time = TimeRepository.obterExatamente(nome);
            if (time != null)
                break;
            System.out.println("Nenhum time encontrado com esse nome!");
        }
        campeonato.adicionarTime(time);
    }
    
    private static void removerTime(Campeonato campeonato) {
        Time time;
        while (true) {
            String nome = consoleResources.getStringFromConsole("Informe o nome do time: ");
            time = TimeRepository.obterExatamente(nome);
            if (time != null)
                break;
            System.out.println("Nenhum time encontrado com esse nome!");
        }
        campeonato.removerTime(time);
    }

    public void remover() {
        int op = consoleResources.getNumberFromConsole("Por qual meio gostaria de remover um campeonato?\n01 - id\n02 - nome\n");
        switch (op) {
            case 1:
                removerPorId();
                break;
            case 2:
                removerPorNome();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                remover();
        }

        System.out.println("Remoção realizada com sucesso!");
        ConsoleResources.pausarConsole();
    }

    private void removerPorId() {
        int id = consoleResources.getNumberFromConsole("Informe o id do campeonato: ");
        Campeonato campeonato = campeonatoRepository.obterTodos().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (Objects.isNull(campeonato)) {
            System.out.println("Id não existe no sistema! Tente novamente.");
            removerPorId();
        }
        campeonatoRepository.remover(campeonato);
    }

    private void removerPorNome() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do campeonato: ");
        Campeonato campeonato = campeonatoRepository.obterTodos().stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
        if (Objects.isNull(campeonato)) {
            System.out.println("Id não existe no sistema! Tente novamente.");
            removerPorNome();
        }
        campeonatoRepository.remover(campeonato);
    }
}