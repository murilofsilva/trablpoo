package processos.menu;

import modelos.enumerators.EntidadeMenuEnum;
import processos.jogador.JogadorService;
import processos.organizador.OrganizadorService;
import util.ConsoleResources;

import java.util.Scanner;

public class MenuCadastroService {
    private static final Scanner sc = new Scanner(System.in);
    private static ConsoleResources consoleResources = new ConsoleResources();

    private static final String EXIBICAO_ENTIDADES_MENU = """
            01 - Organizador
            02 - Gerente
            03 - Evento
            04 - Auxiliar
            05 - Jogador
            06 - Coach/Técnico
            07 - Time
            08 - Fornecedor
            00 - Voltar
            """;

    public static void processaMenuCadastro() {
        while (true) {
            exibeMenuCadastro();
            int opcaoVisualizacao = consoleResources.getNumberFromConsole();

            if (opcaoVisualizacao == 0) return;

            processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuCadastro() {
        String menu = """
                ========= CADASTRO ==========
                """
                + EXIBICAO_ENTIDADES_MENU;
        System.out.print(menu);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case ORGANIZADOR -> OrganizadorService.cadastra();
            case GERENTE -> System.out.print("organizador");
            case EVENTO -> System.out.print("organizador");
            case AUXILIAR -> System.out.print("organizador");
            case JOGADOR -> JogadorService.cadastra();
            case TECNICO -> System.out.print("organizador");
            case TIME -> System.out.print("organizador");
            case FORNECEDOR -> System.out.print("organizador");
            default -> System.out.print("nada");
        }
    }
}
