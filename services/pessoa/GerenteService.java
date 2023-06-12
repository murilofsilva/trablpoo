package services.pessoa;

import modelos.Auxiliar;
import modelos.Funcionario;
import modelos.Gerente;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenteService extends FuncionarioService {
    private static final InscricaoResources inscricaoResources = new InscricaoResources();

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
        pessoaRepository.salvar(gerente);
    }

    public void editar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição do gerente");

        Pessoa pessoa;
        while (true) {
            pessoa = buscarPessoaPorCPF();
            if (!(pessoa instanceof Gerente)) {
                System.out.println("Nenhum gerente encontrado com esse CPF!");
                continue;
            }
            break;
        }

        Funcionario funcionario = (Funcionario)pessoa;
        System.out.println("\nNome do gerente: " + funcionario.getNome());

        while(true) {
            System.out.println("Opções de edição:");
            System.out.println("01 - Salário base");
            System.out.println("02 - Setor gerenciado");
            System.out.println("03 - Funcionários gerenciados");
            int opcao = consoleResources.getNumberFromConsole("O que deseja editar? ");
            if (opcao == 0) break;

            switch (opcao) {
                case 1: editarSalarioBase(funcionario); break;
                case 2: editarSetorGerenciado((Gerente)funcionario); break;
                case 3: editarFuncionariosGerenciados((Gerente)funcionario); break;
                default: break;
            }

            System.out.println("Edição realizada com sucesso!");
            ConsoleResources.pausarConsole();
        }
    }

    private void editarSetorGerenciado(Gerente gerente) {
        ConsoleResources.pularVariasLinhas();
        String setor = consoleResources.getStringFromConsole("Informe o novo setor do gerente: ");
        gerente.setSetorGerenciado(setor);
    }

    private void editarFuncionariosGerenciados(Gerente gerente) {
        ConsoleResources.pularVariasLinhas();
        Pessoa pessoa;

        while (true) {
            String cpf = consoleResources.getStringFromConsole("Informe o CPF do funcionário auxiliar para ser adicionado: ");
            pessoa = pessoaRepository.obter(cpf);
            if (pessoa instanceof Auxiliar)
                break;
            System.out.println("Nenhum auxiliar encontrado com esse CPF!");
        }

        System.out.println(pessoa.getNome() + " foi adicionado a lista de funcionários gerenciados!");
        gerente.adicionarFuncionarioGerenciado(gerente);
    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> gerentes = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Gerente) gerentes.add(pessoa);
        });
        return gerentes;
    }

    public void remover() {
        pessoaRemocaoService.remover("gerente");
    }
}
