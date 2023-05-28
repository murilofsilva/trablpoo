package processos.menu;

import modelos.Gerente;
import modelos.Organizador;
import modelos.Pessoa;
import modelos.enumerators.EntidadeMenuEnum;
import processos.organizador.OrganizadorService;
import repositories.PessoaRepository;
import util.ConsoleResources;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class MenuVisualizacaoService {
    private static final Scanner sc = new Scanner(System.in);
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
            case TECNICO:
            case ORGANIZADOR:
            case GERENTE:
                List<Pessoa> pessoas = buscarPessoasPorNome();
                List<Pessoa> gerentes = Gerente.filtrarGerentes(pessoas);
                imprimirPessoasEncontradas(gerentes);
                imprimirInformacoesPessoa();
                break;
            case EVENTO: System.out.print("organizador");
            case FORNECEDOR: System.out.print("organizador");
            case TIME: System.out.print("organizador");
            default: System.out.print("nada");
        }
    }

    private static List<Pessoa> buscarPessoasPorNome() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("************** VISUALIZAÇÃO DA PESSOA **************");
        String nome = consoleResources.getStringFromConsole("Informe o nome para pesquisa: ");
        return PessoaRepository.obterPorNome(nome);
    }

    private static void imprimirPessoasEncontradas(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.obterInformacoes());
        }
    }

    private static void imprimirInformacoesPessoa() {
        String cpf = consoleResources.getStringFromConsole("Informe o CPF para exibir informações detalhadas: ");
        Pessoa pessoa = PessoaRepository.obter(cpf);
        imprimirInformacoesPessoa(pessoa);
    }

    private static void imprimirInformacoesPessoa(Pessoa pessoa) {
        ConsoleResources.pularVariasLinhas();
        System.out.println(pessoa.obterInformacoesDetalhadas());
        ConsoleResources.pausarConsole();
    }
}