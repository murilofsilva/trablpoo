package services.pessoa;

import modelos.Auxiliar;
import modelos.Funcionario;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static services.pessoa.JogadorService.inscricaoResources;

public class AuxiliarService extends FuncionarioService {
    private static final PessoaRepository pessoaRepository = new PessoaRepository();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização do auxiliar");

        while (true) {
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
        pessoaRepository.salvar(auxiliar);
    }

    public void editar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição do auxiliar");

        Pessoa pessoa;
        while (true) {
            pessoa = buscarPessoaPorCPF();
            if (!(pessoa instanceof Auxiliar)) {
                System.out.println("Nenhum auxiliar encontrado com esse CPF!");
                continue;
            }
            break;
        }

        Funcionario funcionario = (Funcionario) pessoa;
        System.out.println("\nNome do auxiliar: " + funcionario.getNome());

        while (true) {
            System.out.println("Opções de edição:");
            System.out.println("01 - Salário base");
            int opcao = consoleResources.getNumberFromConsole("O que deseja editar? ");
            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    editarSalarioBase(funcionario);
                    break;
                default:
                    break;
            }

            System.out.println("Edição realizada com sucesso!");
            ConsoleResources.pausarConsole();
        }
    }

    public List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> auxiliares = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Auxiliar) auxiliares.add(pessoa);
        });
        return auxiliares;
    }

    public void remover() {
        int op = consoleResources.getNumberFromConsole("Por qual meio gostaria de remover um auxiliar?\n01 - cpf/cnpj\n02 - nome\n");
        switch (op) {
            case 1:
                removerPorCpfCnpj();
                break;
            case 2:
                removerPorNome();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                remover();
        }

        System.out.println("Remoção realizada com sucesso!");
        ConsoleResources.pausarConsole();
    }

    private void removerPorCpfCnpj() {
        String cpfCnpj = consoleResources.getStringFromConsole("Informe o cpf/cnpj do auxiliar: ");
        List<Pessoa> auxiliares = filtrar(pessoaRepository.obterTodos());
        Auxiliar auxiliar = (Auxiliar) auxiliares.stream().filter(a -> a.getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
        if (Objects.isNull(auxiliar)) {
            System.out.println("Cpf/cnpj não existe no sistema! Tente novamente.");
            removerPorCpfCnpj();
        }
        pessoaRepository.remover(auxiliar);
    }

    private void removerPorNome() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do auxiliar: ");
        List<Pessoa> auxiliares = filtrar(pessoaRepository.obterTodos());
        Auxiliar auxiliar = (Auxiliar) auxiliares.stream().filter(p -> p.getNome().toLowerCase()
                .trim().replace(" ", "").equals(nome.toLowerCase().trim()
                        .replace(" ", ""))).findFirst().orElse(null);
        if (Objects.isNull(auxiliar)) {
            System.out.println("Nome não existe no sistema! Tente novamente.");
            removerPorNome();
        }
        pessoaRepository.remover(auxiliar);
    }
}
