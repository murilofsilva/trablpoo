package processos;

public class Menu {
    private static final String DEFAULT_FINISH_MESSAGE = "Obrigado, volte sempre!";
    private static final String DEFAULT_ERROR_MESSAGE = "Favor digitar uma opção válida!";

    public Menu() {
        String menu = """
                BEM VINDO AO MENU!
                Escolha a funcionalidade que deseja:
                01 - Visualizar
                02 - Cadastrar
                03 - Editar
                04 - Remover
                00 - Sair""";
        System.out.println(menu);
    }

    public void switchOpcao(int op) {
        switch (op) {
            case 1 -> loadMenuVisualizacao();
            case 2 -> loadMenuCadastro();
            case 3 -> loadMenuEdicao();
            case 4 -> loadMenuRemocao();
            case 0 -> System.out.println(DEFAULT_FINISH_MESSAGE);
            default -> System.out.println(DEFAULT_ERROR_MESSAGE);
        }
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
