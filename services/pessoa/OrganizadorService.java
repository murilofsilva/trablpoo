package services.pessoa;

import modelos.Localidade;
import modelos.Organizador;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static services.pessoa.JogadorService.inscricaoResources;

public class OrganizadorService extends PessoaService {
    InscricaoResources inscricaoResources = new InscricaoResources();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização do organizador");

        while (true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> organizadores = filtrar(pessoas);
            if (organizadores.size() > 0) {
                imprimirPessoasEncontradas(organizadores);
                break;
            }
            System.out.println("Nenhum organizador encontrado com esse nome!");
        }

        while (true) {
            Pessoa organizador = buscarPessoaPorCPF();
            if (!(organizador instanceof Organizador)) {
                System.out.println("Nenhum organizador encontrado com esse CPF!");
                continue;
            }
            ConsoleResources.pularVariasLinhas();
            ConsoleResources.exibirTitulo("informações do organizador");
            imprimirInformacoesPessoa(organizador);
            break;
        }
    }

    public void criar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro de organizador");
        String nome = consoleResources.getStringFromConsole("Informe o nome do organizador: ");
        String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("organizador");
        LocalDate dataNascimento = DataResources.getAndValidateDate("Informe a data de nascimento do organizador: ");
        double salario = consoleResources.getDoubleFromConsole("Informe o salário base do organizador: ");
        Organizador organizador = new Organizador(nome, cpfCnpj, dataNascimento, salario, LocalDate.now());
        PessoaRepository.salvar(organizador);
    }

    public void editar() {

    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> organizadores = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Organizador) organizadores.add(pessoa);
        });
        return organizadores;
    }
}