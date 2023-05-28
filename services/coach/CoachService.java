package services.coach;

import modelos.Coach;
import modelos.Localidade;
import modelos.Time;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static services.time.TimeService.times;

public class CoachService {
    public static List<Coach> coaches = new ArrayList<>();
    private static ConsoleResources consoleResources = new ConsoleResources();
    private static InscricaoResources inscricaoResources = new InscricaoResources();
    private static DataResources dataResources = new DataResources();

    public void cadastra() {
        try {
            if (times.isEmpty() || times.stream().filter(t -> Objects.isNull(t.getCoach())).toList().isEmpty()) {
                System.out.println("Não há times disponíveis para cadastrar o coach.");
            }
            System.out.println("========= CADASTRO DE COACH ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do coach: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("coach");
            LocalDate dataNascimento = dataResources.getAndValidateDate("coach");
            String pais = consoleResources.getStringFromConsole("Informe o país do coach: ");
            String municipio = consoleResources.getStringFromConsole("Informe o município do coach: ");
            String estado = consoleResources.getStringFromConsole("Informe o estado do coach: ");
            Time time = getTime();

            Localidade localidade = new Localidade(pais, municipio, estado);
            Coach coach = new Coach(nome, cpfCnpj, dataNascimento, localidade, time);

            coaches.add(coach);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um coach. Entre em contato com o suporte.");
        }
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
