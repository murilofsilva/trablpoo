package services.pessoa;

import modelos.*;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static services.pessoa.JogadorService.inscricaoResources;

public class AuxiliarService extends PessoaService {
    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização do auxiliar");

        while(true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> auxiliares = filtrar(pessoas);
            if (auxiliares.size() > 0) {
                imprimirPessoasEncontradas(auxiliares);
                break;
            }
            System.out.println("Nenhum auxiliar encontrado com esse nome!");
        }

        while (true) {
            Pessoa auxiliar = buscarPessoaPorCPF();
            if (auxiliar != null) {
                ConsoleResources.pularVariasLinhas();
                ConsoleResources.exibirTitulo("informações do auxiliar");
                imprimirInformacoesPessoa(auxiliar);
                break;
            }
            System.out.println("Nenhum auxiliar encontrado com esse CPF!");
        }
    }

    public void criar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro de auxiliar");
        String nome = consoleResources.getStringFromConsole("Informe o nome do auxiliar: ");
        String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("auxiliar");
        LocalDate dataNascimento = DataResources.getAndValidateDate("Informe a data de nascimento do auxiliar: ");
        double salario = consoleResources.getDoubleFromConsole("Informe o salário base do auxiliar: ");
        LocalDate dataEntrada = DataResources.getAndValidateDate("Informe a data de entrada do auxiliar: ");
        Auxiliar auxiliar = new Auxiliar(nome, cpfCnpj, dataNascimento, salario, dataEntrada);
        PessoaRepository.salvar(auxiliar);
    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> auxiliares = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Auxiliar) auxiliares.add(pessoa);
        });
        return auxiliares;
    }
}
