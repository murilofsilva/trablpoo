package processos.menu;

import processos.menu.enumerators.EntidadeMenuEnum;
import processos.organizador.OrganizadorService;
import util.ConsoleResources;

import java.util.Scanner;

public class MenuVisualizacaoService {
    private static final Scanner sc = new Scanner(System.in);
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

    public static void processaMenuVisualizacao() {
        while (true) {
            exibeMenuVisualizacao();
            int opcaoVisualizacao = readOpcao();

            if (opcaoVisualizacao == 0) return;

            MenuVisualizacaoService.processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuVisualizacao() {
        String menu = """
                      ========= VISUALIZAÇÃO ==========
                      """
                      +EXIBICAO_ENTIDADES_MENU+
                      """
                      09 - Visualização completa
                      00 - Voltar
                      =================================
                      """;
        System.out.print(menu);
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


    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case ORGANIZADOR -> exibirBuscaOrganizador();
            case GERENTE -> System.out.print("organizador");
            case EVENTO -> System.out.print("organizador");
            case AUXILIAR -> System.out.print("organizador");
            case JOGADOR -> System.out.print("organizador");
            case TECNICO -> System.out.print("organizador");
            case TIME -> System.out.print("organizador");
            case FORNECEDOR -> System.out.print("organizador");
            default -> System.out.print("nada");
        }
    }

    private static void exibirBuscaOrganizador() {
        System.out.println("========= VISUALIZAÇÃO DO ORGANIZADOR ==========");
        System.out.print("Informe o nome do organizador para busca: ");

        String nome = sc.nextLine();
        boolean encontrouResultados = OrganizadorService.imprimirOrganizadoresPorNome(nome);

        if (!encontrouResultados) {
            System.out.println("Organizador não encontrado!");
            ConsoleResources.pausarConsole();
            return;
        }

        System.out.print("Informe o código do organizador para visualização completa: ");
        String codigoOrganizador = sc.nextLine();
        OrganizadorService.imprimirInformacoesOrganizador(codigoOrganizador);
    }
}