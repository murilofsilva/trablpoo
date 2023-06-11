package repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Campeonato;
import modelos.Localidade;

public class CampeonatoRepository {
    public static ArrayList<Campeonato> campeonatos = new ArrayList() {
        {
            Localidade localidade = new Localidade("Brazil", "Campo Grande", "Mato Grosso do Sul");
            add(new Campeonato(1, "brasileirao", LocalDate.now(), LocalDate.now(), localidade, TimeRepository.obter("Sapecas"), JogoRepository.obterPorNome("FIFA").get(0)));
        }
    };

    public static List<Campeonato> obter(String nome) {
        ArrayList<Campeonato> campeonatosEncontrados = new ArrayList<>();

        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getNome().contains(nome))
                campeonatosEncontrados.add(campeonato);
        }

        return campeonatosEncontrados;
    }
}
