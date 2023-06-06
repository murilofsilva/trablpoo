package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.pessoa.AuxiliarService;
import services.pessoa.JogadorService;
import services.pessoa.OrganizadorService;
import util.ConsoleResources;

public class MenuCadastroService {
    private static final String QUEBRA_DE_LINHA = "\n";
    private static ConsoleResources consoleResources = new ConsoleResources();

    public static void processaMenuCadastro() {
        while (true) {
            exibeMenuCadastro();
            int opcaoVisualizacao = consoleResources.getNumberFromConsole();

            if (opcaoVisualizacao == 0) return;

            processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuCadastro() {
        StringBuilder menu = new StringBuilder();
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro");
        menu.append(QUEBRA_DE_LINHA + "Escolha a entidade que deseja cadastrar:");
        menu.append(QUEBRA_DE_LINHA + "01 - Organizador");
        menu.append(QUEBRA_DE_LINHA + "02 - Gerente");
        menu.append(QUEBRA_DE_LINHA + "03 - Jogador");
        menu.append(QUEBRA_DE_LINHA + "04 - Auxiliar");
        menu.append(QUEBRA_DE_LINHA + "05 - Time");
        menu.append(QUEBRA_DE_LINHA + "00 - Voltar");
        menu.append(QUEBRA_DE_LINHA + "");
        menu.append(QUEBRA_DE_LINHA + "");

        System.out.print(menu);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().criar();
                break;
            case ORGANIZADOR:
                new OrganizadorService().criar();
                break;
            case GERENTE:
                System.out.print("organizador");
                break;
            case JOGADOR:
                new JogadorService().criar();
                break;
            case COACH:
                System.out.print("organizador");
                break;
            case TIME:
                System.out.print("organizador");
                break;
            default:
                System.out.print("nada");
        }
    }
}
