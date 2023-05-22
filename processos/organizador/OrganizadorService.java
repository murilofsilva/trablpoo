package processos.organizador;

import modelos.Organizador;
import util.ConsoleResources;
import util.DataResources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizadorService {
    static Scanner sc = new Scanner(System.in);

    private static ArrayList<Organizador> organizadores = new ArrayList<Organizador>();

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

    //CADASTRO

    public static void cadastra() {
        try {
            System.out.println("========= CADASTRO DE ORGANIZADOR ==========");
            System.out.print("Informe o nome do organizador: ");
            String nome = sc.nextLine();
            System.out.print("Informe o cpf ou cnpj do organizador: ");
            String cpfCnpj = sc.nextLine();
            LocalDate dataNascimento = DataResources.getAndValidateDate("organizador");
            System.out.print("Informe o país do organizador: ");
            String pais = sc.nextLine();
            System.out.print("Informe o estado do organizador: ");
            String estado = sc.nextLine();
            System.out.print("Informe o municipio do organizador: ");
            String municipio = sc.nextLine();

            Organizador organizador = new Organizador(nome, cpfCnpj, dataNascimento, pais, municipio, estado);
            organizadores.add(organizador);
        } catch (Exception e) {
            System.out.println("Ocorreram erros ao cadastrar um organizador. Entre em contato com o suporte.");
        }
    }
}
