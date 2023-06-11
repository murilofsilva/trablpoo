package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.campeonato.CampeonatoService;
import services.jogo.JogoService;
import services.pessoa.*;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuRemocaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();

    public static void processaMenuRemocao() {
        while (true) {
            exibeMenuEdicao();
            int opcao = consoleResources.getNumberFromConsole();
            if (opcao == 0) return;
            processaOpcaoEscolhida(opcao);
        }
    }

    private static void exibeMenuEdicao() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("remoção");
        System.out.print(ConsoleResources.modelos);
    }

    public static void main(String[] args) {
        processaMenuRemocao();
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().remover();
                break;
            case CAMPEONATO:
                new CampeonatoService().remover();
                break;
            case COACH:
                new CoachService().remover();
                break;
            case GERENTE:
                new GerenteService().remover();
                break;
            case JOGADOR:
                new JogadorService().remover();
                break;
            case JOGO:
                new JogoService().remover();
                break;
            case ORGANIZADOR:
                new OrganizadorService().remover();
                break;
            case TIME:
                new TimeService().remover();
                break;
            default:
                System.out.println("Opção desconhecida!");
                break;
        }
    }
}
