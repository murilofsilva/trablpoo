package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.jogo.JogoService;
import services.pessoa.AuxiliarService;
import services.pessoa.CoachService;
import services.pessoa.GerenteService;
import services.pessoa.JogadorService;
import services.pessoa.OrganizadorService;
import services.time.TimeService;
import util.ConsoleResources;

public class MenuEdicaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();

    public static void processaMenuEdicao() {
        while (true) {
            exibeMenuEdicao();
            int opcao = consoleResources.getNumberFromConsole();
            if (opcao == 0) return;
            processaOpcaoEscolhida(opcao);
        }
    }

    private static void exibeMenuEdicao() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição");
        System.out.print(ConsoleResources.modelos);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().editar();
                break;
            case CAMPEONATO: break;
            case COACH:
                new CoachService().editar();
                break;
            case GERENTE:
                new GerenteService().editar();
                break;
            case JOGADOR:
                new JogadorService().editar();
                break;
            case JOGO:
                new JogoService().editar();
                break;
            case ORGANIZADOR:
                new OrganizadorService().editar();
                break;
            case TIME:
                new TimeService().editar();
                break;
            default: 
                System.out.println("Opção desconhecida!");
                break;
        }
    }
}
