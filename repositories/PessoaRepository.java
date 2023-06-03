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
                    new Localidade("br", "cg", "ms"),
                    TimeRepository.obter(1))
            );
            add(new Jogador("Lucas Vinicius",
                    "12782122376",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "blaster",
                    TimeRepository.obter(1))
            );
            add(new Jogador("Lucas Vinicius",
                    "12782122376",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "blaster",
                    TimeRepository.obter(1))
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
