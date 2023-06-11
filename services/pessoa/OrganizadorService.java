package services.pessoa;

import modelos.Localidade;
import modelos.Organizador;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizadorService extends PessoaService {
    InscricaoResources inscricaoResources = new InscricaoResources();
    PessoaRepository pessoaRepository = new PessoaRepository();

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
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("edição de organizador");
        Organizador organizador = null;
        int op;

        op = consoleResources.getNumberFromConsole("Gostaria de buscar o organizador por:\n01 - nome\n 02 - cpf/cnpj");
        switch (op) {
            case 1:
                organizador = (Organizador) PessoaService.buscarPessoasPorNome();
                break;
            case 2:
                organizador = (Organizador) PessoaService.buscarPessoaPorCPF();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                editar();
        }

        if (Objects.isNull(organizador)) {
            System.out.println("Organizador não encontrado! Tente novamente.");
            editar();
        }

        op = consoleResources.getNumberFromConsole("O que gostaria de editar?\n01 - Quantidade de campeonatos organizados\n02: Localidade");
        switch (op) {
            case 1:
                editarQuantidadeCampeonatosOrganizados(organizador);
                break;
            case 2:
                editarLocalidade(organizador);
                break;
            case 3:
                System.out.println("Opção inválida! Tente novamente!");
                editar();
        }
    }

    private void editarQuantidadeCampeonatosOrganizados(Organizador organizador) {
        int num = consoleResources.getNumberFromConsole("Informe o novo número de campeonatos que estão sob responsabilidade do organizador");
        organizador.setNumeroCampeonatosOrganizados(num);

    }

    private void editarLocalidade(Organizador organizador) {
        String pais = consoleResources.getStringFromConsole("Informe o país do jogador: ");
        String estado = consoleResources.getStringFromConsole("Informe o estado do jogador: ");
        String municipio = consoleResources.getStringFromConsole("Informe o municipio do jogador: ");
        organizador.setLocalidade(new Localidade(pais, municipio, estado));
    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> organizadores = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Organizador) organizadores.add(pessoa);
        });
        return organizadores;
    }
}