package services.organizador;

import modelos.Localidade;
import modelos.Organizador;
import repositories.PessoaRepository;
import util.ConsoleResources;
import util.DataResources;
import util.InscricaoResources;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizadorService {
    private static ConsoleResources consoleResources = new ConsoleResources();
    private static InscricaoResources inscricaoResources = new InscricaoResources();
    public static ArrayList<Organizador> organizadores = new ArrayList<Organizador>();

    public static boolean imprimirOrganizadoresPorNome(String nome) {
        boolean encontrouResultados = false;
        for (Organizador organizador : organizadores) {
            if (!organizador.getNome().toLowerCase().contains(nome.toLowerCase()))
                continue;
            System.out.println(organizador.getCodigoOrganizador() + " - " + organizador.getNome());
            encontrouResultados = true;
        }

        return encontrouResultados;
    }

    public static boolean imprimirInformacoesOrganizador(String codigoOrganizador) {
        for (Organizador organizador : organizadores) {
            if (!organizador.getCodigoOrganizador().equals(codigoOrganizador))
                continue;
            System.out.println("========= ORGANIZADOR " + organizador.getCodigoOrganizador() + " ==========");

            System.out.println("Nome: " + organizador.getNome());
            System.out.println("CPF: " + organizador.getCpfCnpj());
            System.out.println("Data de nascimento: " + organizador.getDataNascimento());
            ConsoleResources.pausarConsole();
        }

        return true;
    }
}
