package processos;

import java.util.Scanner;

public class MenuService {
    private static final String DEFAULT_FINISH_MESSAGE = "Obrigado, volte sempre!";
    private static final String DEFAULT_ERROR_MESSAGE = "Favor digitar uma opção válida!";
    private static final String QUEBRA_DE_LINHA = "\n";

    public void showMenu() {
        String menu = QUEBRA_DE_LINHA +
                """
                        BEM VINDO AO MENU!
                        Escolha a funcionalidade que deseja:
                        01 - Visualizar
                        02 - Cadastrar
                        03 - Editar
                        04 - Remover
                        00 - Sair""";
        System.out.println(menu);
    }

    public void processaMenu() {
        int op = this.readOpcao();
        switch (op) {
            case 1 -> loadMenuVisualizacao();
            case 2 -> loadMenuCadastro();
            case 3 -> loadMenuEdicao();
            case 4 -> loadMenuRemocao();
            case 0 -> System.out.println(QUEBRA_DE_LINHA + DEFAULT_FINISH_MESSAGE);
            default -> {
                System.out.println(QUEBRA_DE_LINHA + DEFAULT_ERROR_MESSAGE);
                processaMenu();
            }
        }
    }

    private int readOpcao() {
        Scanner sc = new Scanner(System.in);
        MenuService menu = new MenuService();
        int opcao = 0;
        try {
            menu.showMenu();
            opcao = sc.nextInt();
        } catch (Exception e) {
            System.out.println(DEFAULT_ERROR_MESSAGE);
            readOpcao();
        }
        return opcao;
    }

    private void loadMenuVisualizacao() {
        String menu = """
                --- VISUALIZACAO ---
                Escolha o registro que deseja visualizar:
                01 - Organizador
                02 - Gerente
                03 - Evento
                04 - Auxiliar
                05 - Jogador
                06 - Coach/Tecnico
                07 - Fornecedor
                08 - Visualização completa
                00 - Voltar""";
        System.out.println(menu);
    }

    private void loadMenuCadastro() {
        String menu = """
                --- CADASTROS ---
                Escolha o registro que deseja cadastrar:
                01 - Organizador
                02 - Gerente
                03 - Evento
                04 - Auxiliar
                05 - Jogador
                06 - Coach/Tecnico
                07 - Time
                08 - Fornecedor
                00 - Voltar""";
        System.out.println(menu);
    }

    private void loadMenuEdicao() {
        String menu = """
                --- ATUALIZACAO ---
                Escolha o registro que deseja atualizar:
                01 - Organizador
                02 - Gerente
                03 - Evento
                04 - Auxiliar
                05 - Jogador
                06 - Coach/Tecnico
                07 - Fornecedor
                00 - Voltar""";
        System.out.println(menu);
    }

    private void loadMenuRemocao() {
        String menu = """
                --- REMOÇÃO ---
                Escolha o registro que deseja remover:
                01 - Organizador
                02 - Gerente
                03 - Evento
                04 - Auxiliar
                05 - Jogador
                06 - Coach/Tecnico
                07 - Fornecedor
                00 - Voltar""";
        System.out.println(menu);
    }
}
