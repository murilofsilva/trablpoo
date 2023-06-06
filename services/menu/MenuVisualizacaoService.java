package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.pessoa.*;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuVisualizacaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();
    private static final String QUEBRA_DE_LINHA = "\n";

    public static void processaMenuVisualizacao() {
        while (true) {
            exibeMenuVisualizacao();
            int opcaoVisualizacao = consoleResources.getNumberFromConsole();
            if (opcaoVisualizacao == 0) return;
            processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuVisualizacao() {
        StringBuilder menu = new StringBuilder();
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização");
        menu.append(QUEBRA_DE_LINHA + "01 - Organizador");
        menu.append(QUEBRA_DE_LINHA + "02 - Gerente");
        menu.append(QUEBRA_DE_LINHA + "03 - Evento");
        menu.append(QUEBRA_DE_LINHA + "04 - Auxiliar");
        menu.append(QUEBRA_DE_LINHA + "05 - Jogador");
        menu.append(QUEBRA_DE_LINHA + "06 - Coach/Técnico");
        menu.append(QUEBRA_DE_LINHA + "07 - Time");
        menu.append(QUEBRA_DE_LINHA + "08 - Fornecedor");
        menu.append(QUEBRA_DE_LINHA + "09 - Visualização completa");
        menu.append(QUEBRA_DE_LINHA + "00 - Voltar\n");

        System.out.print(menu);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().visualizar();
                break;
            case JOGADOR:
                new JogadorService().visualizar();
                break;
            case COACH:
                new CoachService().visualizar();
                break;
            case ORGANIZADOR:
                new OrganizadorService().visualizar();
                break;
            case GERENTE:
                new GerenteService().visualizar();
                break;
            case EVENTO:
                System.out.print("organizador");
            case FORNECEDOR:
                System.out.print("organizador");
            case TIME:
                new TimeService().visualizar();
                break;
            default:
                System.out.print("nada");
        }
    }
}