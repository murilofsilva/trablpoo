package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.pessoa.AuxiliarService;
import services.pessoa.CoachService;
import services.pessoa.GerenteService;
import services.pessoa.JogadorService;
import services.pessoa.OrganizadorService;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuCadastroService {
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
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro");
        System.out.print(ConsoleResources.modelos);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().criar();
                break;
            case CAMPEONATO: break;
            case COACH:
                new CoachService().criar();
                break;
            case GERENTE:
                new GerenteService().criar();
                break;
            case JOGADOR:
                new JogadorService().criar();
                break;
            case JOGO: break;
            case ORGANIZADOR:
                new OrganizadorService().criar();
                break;
            case TIME:
                new TimeService().criar();
                break;
            default: 
                System.out.println("Opção desconhecida!");
                break;
        }
    }
}
