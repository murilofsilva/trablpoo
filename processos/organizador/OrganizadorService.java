package processos.organizador;

import modelos.Localidade;
import modelos.Organizador;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrganizadorService {
    private static ConsoleResources consoleResources = new ConsoleResources();
    private static InscricaoResources inscricaoResources = new InscricaoResources();
    public static ArrayList<Organizador> organizadores = new ArrayList<Organizador>() {
        {
            add(new Organizador("Emerson",
                    "06369196177",
                    LocalDate.now(),
                    new Localidade("brazil", "cg", "ms"))
            );
        }
    };

    public static Organizador obterOrganizadorPorCodigo(String codigo) {
        Optional<Organizador> organizador = organizadores.stream()
                .filter(x -> x.getCodigoOrganizador().equals(codigo)).findFirst();
        if (organizador.isEmpty()) {
            System.out.println("Organizador não encontrado");
            return null;
        }

        return organizador.get();
    }

    public static boolean imprimirOrganizadoresPorNome(String nome) {
        boolean encontrouResultados = false;
        List<Organizador> organizadoresEncontrados = organizadores.stream()
                .filter(x -> x.getNome().toLowerCase().contains(nome.toLowerCase())).toList();

        for (Organizador organizador : organizadoresEncontrados) {
            System.out.println(organizador.getCodigoOrganizador() + " - " + organizador.getNome());
            encontrouResultados = true;
        }

        return encontrouResultados;
    }

    //CADASTRO

    public static void cadastra() {
        try {
            System.out.println("========= CADASTRO DE ORGANIZADOR ==========");
            String nome = consoleResources.getStringFromConsole("Informe o nome do organizador: ");
            String cpfCnpj = inscricaoResources.getAndValidateCpfCnpj("organizador");
            LocalDate dataNascimento = DataResources.getAndValidateDate("organizador");
            String pais = consoleResources.getStringFromConsole("Informe o país do organizador: ");
            String estado = consoleResources.getStringFromConsole("Informe o estado do organizador: ");
            String municipio = consoleResources.getStringFromConsole("Informe o município do organizador");

            Localidade localidade = new Localidade(pais, municipio, estado);
            Organizador organizador = new Organizador(nome, cpfCnpj, dataNascimento,localidade);

            organizadores.add(organizador);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um organizador. Entre em contato com o suporte.");
        }
    }
}
