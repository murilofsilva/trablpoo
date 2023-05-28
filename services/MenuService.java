package services;

import services.menu.MenuCadastroService;
import services.menu.MenuVisualizacaoService;
import util.ConsoleResources;

public class MenuService {
    private static ConsoleResources consoleResources = new ConsoleResources();
    private static final String QUEBRA_DE_LINHA = "\n";

    private static void exibeOpcoesMenu() {
        String menu = QUEBRA_DE_LINHA + """
                    BEM VINDO AO MENU!
                    Escolha a funcionalidade que deseja:
                    01 - Visualizar
                    02 - Cadastrar
                    03 - Editar
                    04 - Remover
                    00 - Sair
                """;
        System.out.println(menu);
    }

    public static void processaMenu() {
        int op;
        do {
            exibeOpcoesMenu();
            op = consoleResources.getNumberFromConsole();

            switch (op) {
                case 1 -> MenuVisualizacaoService.processaMenuVisualizacao();
                case 2 -> MenuCadastroService.processaMenuCadastro();
                case 3 -> System.out.println("TODO");
                case 4 -> System.out.println("TODO");
                case 0 -> System.out.println(QUEBRA_DE_LINHA + "Obrigado, volte sempre!");
                default -> {
                    System.out.println(QUEBRA_DE_LINHA + "Favor digitar uma opção válida!");
                }
            }
        } while (op != 0);
    }
}
