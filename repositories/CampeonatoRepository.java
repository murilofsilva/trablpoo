package repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Campeonato;
import modelos.Localidade;

public class CampeonatoRepository {
    private static int proximaChaveUnica = 1;
    private ArrayList<Campeonato> campeonatos = new ArrayList() {
        {
            Localidade localidade = new Localidade("Brazil", "Campo Grande", "Mato Grosso do Sul");
            add(new Campeonato("brasileirao", LocalDate.now(), LocalDate.now(), localidade, TimeRepository.obter("Sapecas"), JogoRepository.obterPorNome("FIFA").get(0)));
        }
    };

    public List<Campeonato> obter(String nome) {
        ArrayList<Campeonato> campeonatosEncontrados = new ArrayList<>();

        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getNome().contains(nome))
                campeonatosEncontrados.add(campeonato);
        }

        return campeonatosEncontrados;
    }

    public void salvar(Campeonato campeonato) {
        campeonato.setId(proximaChaveUnica++);
        campeonatos.add(campeonato);
    }

    public void remover(Campeonato campeonato) {
        this.campeonatos.remove(campeonato);
    }

    public List<Campeonato> obterTodos() {
        return this.campeonatos;
    }
}
