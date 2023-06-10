package services.pessoa;

import modelos.Coach;
import modelos.Localidade;
import modelos.Pessoa;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoachService extends PessoaService {
    private final static ConsoleResources consoleResources = new ConsoleResources();
    private final static InscricaoResources inscricaoResources = new InscricaoResources();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        ConsoleResources.exibirTitulo("visualização do coach");

        while (true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> coaches = filtrar(pessoas);
            if (coaches.size() > 0) {
                imprimirPessoasEncontradas(coaches);
                break;
            }
            System.out.println("Nenhum coach encontrado com esse nome!");
        }

        while (true) {
            Pessoa coach = buscarPessoaPorCPF();
            if (coach != null) {
                ConsoleResources.pularVariasLinhas();
                ConsoleResources.exibirTitulo("informações do coach");
                imprimirInformacoesPessoa(coach);
                break;
            }
            System.out.println("Nenhum coach encontrado com esse CPF!");
        }
    }

    public void criar() {
        try {
            ConsoleResources.exibirTitulo("cadastro de coach/técnico");
            String nome = consoleResources.getStringFromConsole("Informe o nome do coach: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("coach");
            LocalDate dataNascimento = DataResources.getAndValidateDate("Informe a data de nascimento do coach: ");
            String pais = consoleResources.getStringFromConsole("Informe o país do coach: ");
            String municipio = consoleResources.getStringFromConsole("Informe o município do coach: ");
            String estado = consoleResources.getStringFromConsole("Informe o estado do coach: ");

            Localidade localidade = new Localidade(pais, municipio, estado);
            Coach coach = new Coach(nome, cpfCnpj, dataNascimento, localidade);

            PessoaRepository.salvar(coach);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um coach. Entre em contato com o suporte.");
        }
    }

    public void editar() {

    }

    public List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> coaches = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Coach) coaches.add(pessoa);
        });
        return coaches;
    }
}
