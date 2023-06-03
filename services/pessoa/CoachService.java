package services.pessoa;

import modelos.*;
import services.pessoa.PessoaService;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static services.time.TimeService.times;

public class CoachService extends PessoaService {
    public static List<Coach> coaches = new ArrayList<>();
    private final static ConsoleResources consoleResources = new ConsoleResources();
    private final static InscricaoResources inscricaoResources = new InscricaoResources();

    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("************** VISUALIZAÇÃO DO COACH **************");

        while(true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> coaches = filtrar(pessoas);
            if (coaches.size() > 0) {
                imprimirPessoasEncontradas(coaches);
                break;
            }
            System.out.println("Nenhum coach encontrado com esse nome!");
        }

        while (true) {
            Pessoa gerente = buscarPessoaPorCPF();
            if (gerente != null) {
                imprimirInformacoesPessoa(gerente);
                break;
            }
            System.out.println("Nenhum coach encontrado com esse CPF!");
        }
    }

    public void criar() {
        try {
            if (times.isEmpty() || times.stream().filter(t -> Objects.isNull(t.getCoach())).toList().isEmpty()) {
                System.out.println("Não há times disponíveis para cadastrar o coach.");
            }
            System.out.println("========= CADASTRO DE COACH ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do coach: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("coach");
            LocalDate dataNascimento = DataResources.getAndValidateDate("coach");
            String pais = consoleResources.getStringFromConsole("Informe o país do coach: ");
            String municipio = consoleResources.getStringFromConsole("Informe o município do coach: ");
            String estado = consoleResources.getStringFromConsole("Informe o estado do coach: ");
            Time time = getTime();

            Localidade localidade = new Localidade(pais, municipio, estado);
            Coach coach = new Coach(nome, cpfCnpj, dataNascimento, localidade);

            coaches.add(coach);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um coach. Entre em contato com o suporte.");
        }
    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> coaches = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Coach) coaches.add(pessoa);
        });
        return coaches;
    }

    private Time getTime() {
        String nome = consoleResources.getStringFromConsole("Informe o nome do time que deseja atribuir o coach: ");
        Time time = times.stream().filter(t -> t.getNome().equals(nome)).findFirst().orElse(null);

        if (Objects.isNull(time)) {
            System.out.println("Time não encontrado! Tente novamente.");
            getTime();
        }

        return time;
    }
}
