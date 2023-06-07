package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.MenuService;
import services.pessoa.AuxiliarService;
import services.pessoa.GerenteService;
import services.pessoa.JogadorService;
import services.pessoa.OrganizadorService;
import util.ConsoleResources;

public class MenuEdicaoService {
    private static final String QUEBRA_DE_LINHA = "\n";
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
        String menu = MenuService.obterEntidadesMenu();
        System.out.print(menu);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
                new AuxiliarService().editar();
                break;
            case ORGANIZADOR:
                new OrganizadorService().criar();
                break;
            case GERENTE:
                new GerenteService().editar();
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
