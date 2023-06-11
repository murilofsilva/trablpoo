package services.jogo;

import interfaces.ItemMenu;
import modelos.Jogo;
import modelos.enumerators.ModalidadeEnum;
import repositories.JogoRepository;
import util.ConsoleResources;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JogoService implements ItemMenu {
    static Scanner sc = new Scanner(System.in);
    private final ConsoleResources consoleResources = new ConsoleResources();

    public void criar() {
        consoleResources.exibirTitulo("Cadasatro de jogo");
        String nome = consoleResources.getStringFromConsole("Informe o nome do jogo: ");
        ModalidadeEnum modalidadeEscolhida = getModalidade();
        Jogo jogo = new Jogo(nome, modalidadeEscolhida);
        JogoRepository.jogos.add(jogo);
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
            jogos = JogoRepository.obterPorNome(nomePesquisado);

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
    }
}
