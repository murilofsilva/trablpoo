package services;

import services.menu.MenuCadastroService;
import services.menu.MenuVisualizacaoService;
import util.ConsoleResources;

public class MenuService {
    private static final ConsoleResources consoleResources = new ConsoleResources();
    private static final String QUEBRA_DE_LINHA = "\n";

    private static void exibeOpcoesMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append(QUEBRA_DE_LINHA + " BEM VINDO AO MENU!");
        menu.append(QUEBRA_DE_LINHA + "Escolha a funcionalidade que deseja:");
        menu.append(QUEBRA_DE_LINHA + "01 - Visualizar");
        menu.append(QUEBRA_DE_LINHA + "02 - Cadastrar");
        menu.append(QUEBRA_DE_LINHA + "03 - Editar");
        menu.append(QUEBRA_DE_LINHA + "04 - Remover");
        menu.append(QUEBRA_DE_LINHA + "00 - Sair");

        System.out.println(menu);
    }

    public static void processaMenu() {
        int op;
        do {
            exibeOpcoesMenu();
            op = consoleResources.getNumberFromConsole();

            switch (op) {
                case 1:
                    MenuVisualizacaoService.processaMenuVisualizacao();
                    break;
                case 2:
                    MenuCadastroService.processaMenuCadastro();
                    break;
                case 3:
                    System.out.println("TODO");
                    break;
                case 4:
                    System.out.println("TODO");
                    break;
                case 0:
                    System.out.println(QUEBRA_DE_LINHA + "Obrigado, volte sempre!");
                    break;
                default:
                    System.out.println(QUEBRA_DE_LINHA + "Favor digitar uma opção válida!");
            }
        } while (op != 0);
    }
}
