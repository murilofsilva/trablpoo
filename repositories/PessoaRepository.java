package repositories;

import modelos.*;

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
            add(new Organizador("Murilo",
                    "99232712822",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"))
            );
            add(new Coach("Dioguinho",
                    "23915319411",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"))
            );
            add(new Jogador("Lucas Vinicius",
                    "12782122376",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "blaster")
            );
            add(new Jogador("Jota e os tres mosqueteiros",
                    "22818271244",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "jotaMatador")
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
