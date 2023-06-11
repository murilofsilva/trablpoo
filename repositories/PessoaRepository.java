package repositories;

import modelos.*;
import modelos.enumerators.ModalidadeEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaRepository {
    public static ArrayList<Pessoa> pessoas = new ArrayList() {
        {
            add(new Gerente("Emerson",
                    "06169116177",
                    LocalDate.now(),
                    2300.10,
                    LocalDate.now(),
                    "setor de bebidas")
            );
            add(new Organizador("Murilo",
                    "99232712822",
                    LocalDate.now(), 4600.92, LocalDate.now())
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
                    "blaster",
                    new Jogo("Welligton Rato", ModalidadeEnum.BATTLE_ROYALE)
                   )
            );
            add(new Jogador("Jota e os tres mosqueteiros",
                    "22818271244",
                    LocalDate.now(),
                    new Localidade("br", "cg", "ms"),
                    "jotaMatador",
                    new Jogo("Ariana Grande", ModalidadeEnum.FPS))
            );
            add(new Auxiliar("Carlos Alberto",
                    "06369196177",
                    LocalDate.now(),
                    2100.19,
                    LocalDate.now()));
        }
    };

    public static Pessoa obter(String cpf) {
        return pessoas.stream().filter(x -> x.getCpfCnpj().equals(cpf)).findFirst().orElse(null);
    }

    public static List<Pessoa> obterPorNome(String nome) {
        return pessoas.stream().filter(x -> x.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
    }

    public List<Pessoa> obterTodos() {
        return pessoas;
    }

    public static void salvar(Pessoa pessoa) {
        pessoas.add(pessoa);
    }
}
