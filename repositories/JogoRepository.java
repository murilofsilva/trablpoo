package repositories;

import modelos.Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JogoRepository {
    private static int proximaChaveUnica = 1;
    public static ArrayList<Jogo> jogos = new ArrayList<>();

    public List<Jogo> obter(String nome) {
        return jogos.stream().filter(jogo -> jogo.getNome().toLowerCase().trim()
                .replace(" ", "").contains(nome.toLowerCase().trim()
                        .replace(" ", ""))).collect(Collectors.toList());
    }

    public Jogo obter(int id) {
        return jogos.stream().filter(jogo -> jogo.getId() == id).findFirst().orElse(null);
    }

    public void salvar(Jogo jogo) {
        jogo.setId(proximaChaveUnica);
        jogos.add(jogo);
        proximaChaveUnica++;
    }

    public void remover(Jogo jogo) {
        jogos.remove(jogo);
    }
}
