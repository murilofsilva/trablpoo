package repositories;

import modelos.Gerente;
import modelos.Localidade;
import modelos.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {
    public static ArrayList<Pessoa> pessoas = new ArrayList<>() {
        {
            add(new Gerente("Emerson",
                    "06169116177",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "setor de bebidas")
            );
        }
    };

    public static Pessoa obter(String cpf) {
        return pessoas.stream().filter(x -> x.getCpfCnpj().equals(cpf)).findFirst().orElse(null);
    }

    public static List<Pessoa> obterPorNome(String nome) {
        return pessoas.stream().filter(x -> x.getNome().toLowerCase().contains(nome.toLowerCase())).toList();
    }
}
