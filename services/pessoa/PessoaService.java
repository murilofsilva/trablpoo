package services.pessoa;

import interfaces.ItemMenu;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;

import java.util.List;

public abstract class PessoaService implements ItemMenu {
    protected static final ConsoleResources consoleResources = new ConsoleResources();

    protected abstract List<Pessoa> filtrar(List<Pessoa> pessoas);

    protected static List<Pessoa> buscarPessoasPorNome() {
        String nome = consoleResources.getStringFromConsole("Informe o nome para pesquisa: ");
        return PessoaRepository.obterPorNome(nome);
    }

    protected static void imprimirPessoasEncontradas(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.obterInformacoes());
        }
    }

    protected static Pessoa buscarPessoaPorCPF() {
        String cpf = consoleResources.getStringFromConsole("Informe o CPF: ");
        return PessoaRepository.obter(cpf);
    }

    protected static void imprimirInformacoesPessoa(Pessoa pessoa) {
        System.out.println(pessoa.obterInformacoesDetalhadas());
        ConsoleResources.pausarConsole();
    }
}
