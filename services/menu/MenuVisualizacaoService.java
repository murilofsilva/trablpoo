package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.pessoa.*;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuVisualizacaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();

    public static void processaMenuVisualizacao() {
        while (true) {
            exibeMenuVisualizacao();
            int opcaoVisualizacao = consoleResources.getNumberFromConsole();
            if (opcaoVisualizacao == 0) return;
            processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuVisualizacao() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização");
        System.out.print(ConsoleResources.modelos);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().visualizar();
                break;
            case CAMPEONATO: break;
            case COACH:
                new CoachService().visualizar();
                break;
            case GERENTE:
                new GerenteService().visualizar();
                break;
            case JOGADOR:
                new JogadorService().visualizar();
                break;
            case JOGO: break;
            case ORGANIZADOR:
                new OrganizadorService().visualizar();
                break;
            case TIME:
                new TimeService().visualizar();
                break;
            default: 
                System.out.println("Opção desconhecida!");
                break;
        }
    }
}