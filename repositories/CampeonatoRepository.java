package repositories;

import java.util.ArrayList;
import java.util.List;

import modelos.Campeonato;

public class CampeonatoRepository {
    private static int proximaChaveUnica = 1;
    public static ArrayList<Campeonato> campeonatos = new ArrayList<>();

    public List<Campeonato> obter(String nome) {
        ArrayList<Campeonato> campeonatosEncontrados = new ArrayList<>();

        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getNome().contains(nome))
                campeonatosEncontrados.add(campeonato);
        }

        return campeonatosEncontrados;
    }

    public Campeonato obterPorNome(String nome) {
        return campeonatos.stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Campeonato obterPorId(int id) {
        return campeonatos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void salvar(Campeonato campeonato) {
        campeonato.setId(proximaChaveUnica++);
        campeonatos.add(campeonato);
    }

    public void remover(Campeonato campeonato) {
        campeonatos.remove(campeonato);
    }
}
