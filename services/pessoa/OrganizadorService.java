package services.pessoa;

import modelos.Gerente;
import modelos.Organizador;
import modelos.Pessoa;
import util.ConsoleResources;

import java.util.ArrayList;
import java.util.List;

public class OrganizadorService extends PessoaService {
    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("************** VISUALIZAÇÃO DO ORGANIZADOR **************");

        while(true) {
            List<Pessoa> pessoas = buscarPessoasPorNome();
            List<Pessoa> organizadores = filtrar(pessoas);
            if (organizadores.size() > 0) {
                imprimirPessoasEncontradas(organizadores);
                break;
            }
            System.out.println("Nenhum organizador encontrado com esse nome!");
        }

        while (true) {
            Pessoa gerente = buscarPessoaPorCPF();
            if (gerente != null) {
                imprimirInformacoesPessoa(gerente);
                break;
            }
            System.out.println("Nenhum organizador encontrado com esse CPF!");
        }
    }

    public void criar() {

    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> organizadores = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Organizador) organizadores.add(pessoa);
        });
        return organizadores;
    }
}