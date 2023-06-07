package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.MenuService;
import services.pessoa.*;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuCadastroService {
    private static final String QUEBRA_DE_LINHA = "\n";
    private static final ConsoleResources consoleResources = new ConsoleResources();

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
        menu.append("01 - Auxiliar");
        menu.append(QUEBRA_DE_LINHA + "02 - Organizador");
        menu.append(QUEBRA_DE_LINHA + "03 - Gerente");
        menu.append(QUEBRA_DE_LINHA + "04 - Jogador");
        menu.append(QUEBRA_DE_LINHA + "05 - Coach");
        menu.append(QUEBRA_DE_LINHA + "06 - Evento");
        menu.append(QUEBRA_DE_LINHA + "07 - Time");
        menu.append(QUEBRA_DE_LINHA + "08 - Fornecedor");
        menu.append(QUEBRA_DE_LINHA + "00 - Voltar");

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
                new GerenteService().criar();
                break;
            case JOGADOR:
                new JogadorService().criar();
                break;
            case COACH:
                new CoachService().criar();
                break;
            case TIME:
                new TimeService().criar();
                break;
            default:
                System.out.print("Opção inválida!");
                MenuService.processaMenu();
        }
    }
}
