package services.menu;

import modelos.enumerators.EntidadeMenuEnum;
import services.pessoa.CoachService;
import services.pessoa.GerenteService;
import services.pessoa.JogadorService;
import services.pessoa.OrganizadorService;
import util.ConsoleResources;

public class MenuVisualizacaoService {
    private static final ConsoleResources consoleResources = new ConsoleResources();

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
            int opcaoVisualizacao = consoleResources.getNumberFromConsole();
            if (opcaoVisualizacao == 0) return;
            processaOpcaoEscolhida(opcaoVisualizacao);
        }
    }

    private static void exibeMenuVisualizacao() {
        String menu = """
                ========= VISUALIZAÇÃO ==========
                """
                + EXIBICAO_ENTIDADES_MENU +
                """
                09 - Visualização completa
                00 - Voltar
                =================================
                """;
        System.out.print(menu);
    }

    private static void processaOpcaoEscolhida(int opcaoEscolhida) {
        EntidadeMenuEnum opcaoEscolhidaEnum = EntidadeMenuEnum.obterEntidadePorValor(opcaoEscolhida);

        switch (opcaoEscolhidaEnum) {
            case AUXILIAR:
            case JOGADOR:
                new JogadorService().visualizar();
                break;
            case COACH:
                new CoachService().visualizar();
                break;
            case ORGANIZADOR:
                new OrganizadorService().visualizar();
                break;
            case GERENTE:
                new GerenteService().visualizar();
                break;
            case EVENTO: System.out.print("organizador");
            case FORNECEDOR: System.out.print("organizador");
            case TIME: System.out.print("organizador");
            default: System.out.print("nada");
        }
    }
}