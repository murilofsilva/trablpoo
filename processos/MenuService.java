package processos;

import processos.menu.MenuVisualizacaoService;

import java.util.Scanner;

public class MenuService {
    private static final Scanner sc = new Scanner(System.in);
    private static final String QUEBRA_DE_LINHA = "\n";
    private static final String EXIBICAO_ENTIDADES_MENU = """
                                                          01 - Organizador
                                                          02 - Gerente
                                                          03 - Evento
                                                          04 - Auxiliar
                                                          05 - Jogador
                                                          06 - Coach/Técnico
                                                          07 - Time
                                                          08 - Fornecedor
                                                          """;

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
            op = readOpcao();

            switch (op) {
                case 1 -> MenuVisualizacaoService.processaMenuVisualizacao();
                case 2 -> loadMenuCadastro();
                case 3 -> loadMenuEdicao();
                case 4 -> loadMenuRemocao();
                case 0 -> System.out.println(QUEBRA_DE_LINHA + "Obrigado, volte sempre!");
                default -> {
                    System.out.println(QUEBRA_DE_LINHA + "Favor digitar uma opção válida!");
                }
            }
        } while(op != 0);
    }

    private static int readOpcao() {
        int opcao;
        try {
            System.out.print("Informe a opção: ");
            opcao = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("A opcão escolhida deve ser um número!");
            return readOpcao();
        }
        
        return opcao;
    }

    private static void loadMenuCadastro() {
        String menu = """
            --- CADASTROS ---
            Escolha o registro que deseja cadastrar:
            """ + EXIBICAO_ENTIDADES_MENU + """
            00 - Voltar
        """;
        System.out.println(menu);
    }

    private static void loadMenuEdicao() {
        String menu = """
            --- ATUALIZACAO ---
            Escolha o registro que deseja atualizar:
            """ + EXIBICAO_ENTIDADES_MENU + """
            00 - Voltar
        """;
        System.out.println(menu);
    }

    private static void loadMenuRemocao() {
        String menu = """
            --- REMOÇÃO ---
            Escolha o registro que deseja remover:
            """ + EXIBICAO_ENTIDADES_MENU + """
            00 - Voltar
        """;
        System.out.println(menu);
    }
}
