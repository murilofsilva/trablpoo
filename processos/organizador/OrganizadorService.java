package processos.organizador;

import modelos.Organizador;
import util.ConsoleResources;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrganizadorService {
    private static ArrayList<Organizador> organizadores = new ArrayList<Organizador>() {
        {
            add(new Organizador("Emerson", "06369196177", LocalDate.now()));
        }
    };

    public static boolean imprimirOrganizadoresPorNome(String nome) {
        boolean encontrouResultados = false;
        for (Organizador organizador: organizadores) {
            if (!organizador.getNome().contains(nome))
                continue;
            System.out.println(organizador.getCodigoOrganizador() + " - " + organizador.getNome());
            encontrouResultados = true;
        }

        return encontrouResultados;
    }

    public static boolean imprimirInformacoesOrganizador(String codigoOrganizador) {
        for (Organizador organizador: organizadores) {
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
