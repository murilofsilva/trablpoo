package services.pessoa;

import modelos.Gerente;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static services.pessoa.JogadorService.inscricaoResources;

public class GerenteService extends PessoaService {
    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização do gerente");

        while(true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> gerentes = filtrar(pessoas);
            if (gerentes.size() > 0) {
                imprimirPessoasEncontradas(gerentes);
                break;
            }
            System.out.println("Nenhum gerente encontrado com esse nome!");
        }

        while (true) {
            Pessoa gerente = buscarPessoaPorCPF();
            if (!(gerente instanceof Gerente)) {
                System.out.println("Nenhum gerente encontrado com esse CPF!");
                continue;
            }
            ConsoleResources.pularVariasLinhas();
            ConsoleResources.exibirTitulo("informações do gerente");
            imprimirInformacoesPessoa(gerente);
            System.out.println("Nenhum gerente encontrado com esse CPF!");
            break;
        }
    }

    public void criar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("cadastro de gerente");
        String nome = consoleResources.getStringFromConsole("Informe o nome do gerente: ");
        String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("gerente");
        LocalDate dataNascimento = DataResources.getAndValidateDate("Informe a data de nascimento do gerente: ");
        double salario = consoleResources.getDoubleFromConsole("Informe o salário base do gerente: ");
        String setor = consoleResources.getStringFromConsole("Informe o setor do gerente: ");
        Gerente gerente = new Gerente(nome, cpfCnpj, dataNascimento, salario, LocalDate.now(), setor);
        PessoaRepository.salvar(gerente);
    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> gerentes = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Gerente) gerentes.add(pessoa);
        });
        return gerentes;
    }
}
