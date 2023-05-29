package services.pessoa;

import modelos.Gerente;
import modelos.Pessoa;
import util.ConsoleResources;

import java.util.ArrayList;
import java.util.List;

public class GerenteService extends PessoaService {
    public void visualizar() {
        ConsoleResources.pularVariasLinhas();
        System.out.println("************** VISUALIZAÇÃO DO GERENTE **************");

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
            if (gerente != null) {
                imprimirInformacoesPessoa(gerente);
                break;
            }
            System.out.println("Nenhum gerente encontrado com esse CPF!");
        }
    }

    public void criar() {

    }

    protected List<Pessoa> filtrar(List<Pessoa> pessoas) {
        List<Pessoa> gerentes = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            if (pessoa instanceof Gerente) gerentes.add(pessoa);
        });
        return gerentes;
    }
}
