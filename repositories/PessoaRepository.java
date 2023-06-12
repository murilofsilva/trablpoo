package repositories;

import modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaRepository {
    public static ArrayList<Pessoa> pessoas = new ArrayList<>();

    public Pessoa obter(String cpf) {
        return pessoas.stream().filter(x -> x.getCpfCnpj().equals(cpf)).findFirst().orElse(null);
    }

    public List<Pessoa> obterPorNome(String nome) {
        return pessoas.stream().filter(x -> x.getNome().toLowerCase().trim()
                        .replace(" ", "").contains(nome.toLowerCase().trim().replace(" ", "")))
                .collect(Collectors.toList());
    }

    public void salvar(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void remover(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }
}
