package services.jogo;

import interfaces.ItemMenu;
import modelos.Jogo;
import modelos.enumerators.ModalidadeEnum;
import repositories.JogoRepository;
import services.MenuService;
import util.ConsoleResources;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JogoService implements ItemMenu {
    static Scanner sc = new Scanner(System.in);
    private final ConsoleResources consoleResources = new ConsoleResources();
    private final JogoRepository jogoRepository = new JogoRepository();

    public void criar() {
        consoleResources.exibirTitulo("Cadasatro de jogo");
        String nome = consoleResources.getStringFromConsole("Informe o nome do jogo: ");
        ModalidadeEnum modalidadeEscolhida = getModalidade();
        Jogo jogo = new Jogo(nome, modalidadeEscolhida);
        jogoRepository.salvar(jogo);
    }

    private ModalidadeEnum getModalidade() {
        int contador = ModalidadeEnum.values().length;
        System.out.print("Escolha a modalidade que mais se adequa ao jogo: ");
        for (ModalidadeEnum modalidade : ModalidadeEnum.values()) {
            contador--;
            System.out.print(modalidade.obterValor());
            if (contador != 0) System.out.print(", ");
            else System.out.print(": ");
        }
        ModalidadeEnum modalidadeEscolhida = ModalidadeEnum.obterModalidadePorValor(sc.nextLine());
        if (Objects.isNull(modalidadeEscolhida)) {
            System.out.println("Opção inválida! Tente novamente.");
            getModalidade();
        }
        return modalidadeEscolhida;
    }

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização dos jogos");
        List<Jogo> jogos;

        while (true) {
            String nomePesquisado = consoleResources.getStringFromConsole("Informe o nome do jogo: ");
            jogos = jogoRepository.obterPorNome(nomePesquisado);

            if (jogos.size() > 0)
                break;

            System.out.println("Nenhum jogo encontrado com esse nome!");
        }

        imprimirListagemJogos(jogos);
    }

    private static void imprimirListagemJogos(List<Jogo> jogos) {
        for (Jogo jogo : jogos) {
            System.out.println(jogo.obterInformacoes());
        }
    }

    public void editar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição de jogo");
        Jogo jogo;

        while (true) {
            jogo = buscarJogoPorNome();
            if (Objects.isNull(jogo)) {
                System.out.println("Nenhum jogo encontrado com esse CPF!");
                continue;
            }
            break;
        }

        System.out.println("\nNome do jogo: " + jogo.getNome());
        while (true) {
            System.out.println("Opções de edição:");
            System.out.println("01 - nome");
            System.out.println("02 - modalidade");
            System.out.println("00 - Sair");
            int opcao = consoleResources.getNumberFromConsole("O que deseja editar? ");
            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    editarNome(jogo);
                    break;
                case 2:
                    editarModalidade(jogo);
                    break;
            }

            System.out.println("Edição realizada com sucesso!");
            MenuService.processaMenu();
        }
    }

    private Jogo buscarJogoPorNome() {
        String nome = consoleResources.getStringFromConsole("Informe o nome: ");
        return jogoRepository.obterPorNome(nome).get(0);
    }

    private void editarNome(Jogo jogo) {
        ConsoleResources.pularVariasLinhas();
        String novoNome = consoleResources.getStringFromConsole("Informe o novo nome para o jogo: ");
        jogo.setNome(novoNome);
    }

    private void editarModalidade(Jogo jogo) {
        int contador = ModalidadeEnum.values().length;
        System.out.print("Escolha a nova modalidade dentre as opções listadas: ");
        for (ModalidadeEnum modalidade : ModalidadeEnum.values()) {
            contador--;
            System.out.print(modalidade.obterValor());
            if (contador != 0) System.out.print(", ");
            else System.out.print(": ");
        }
        ModalidadeEnum modalidadeEscolhida = ModalidadeEnum.obterModalidadePorValor(sc.nextLine());
        jogo.setModalidade(modalidadeEscolhida);
    }

    public void remover() {
        int op = consoleResources.getNumberFromConsole("Por qual meio gostaria de remover um jogo?\n01 - id\n02 - nome\n");
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
        MenuService.processaMenu();
    }

    private void removerPorId() {
        int id = consoleResources.getNumberFromConsole("Informe o id do jogo: ");
        Jogo jogo = jogoRepository.obterPorId(id);
        if (Objects.isNull(jogo)) {
            System.out.println("Id não existe no sistema! Tente novamente.");
            removerPorId();
        }
        jogoRepository.remover(jogo);
    }

    private void removerPorNome() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do jogo: ");
        List<Jogo> jogos = jogoRepository.obterPorNome(nome);
        if (Objects.isNull(jogos) || jogos.isEmpty()) {
            System.out.println("Nome não existe no sistema! Tente novamente.");
            removerPorNome();
        }
        jogoRepository.remover(jogos.get(0));
        System.out.println("Remoção realizada com sucesso!");
        MenuService.processaMenu();
    }
}
