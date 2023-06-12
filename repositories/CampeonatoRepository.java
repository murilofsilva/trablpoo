package repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Campeonato;
import modelos.Localidade;

public class CampeonatoRepository {
    private static final TimeRepository timeRepository = new TimeRepository();
    private static int proximaChaveUnica = 1;
    private static final JogoRepository jogoRepository = new JogoRepository();
    public static ArrayList<Campeonato> campeonatos = new ArrayList() {
        {
            Localidade localidade = new Localidade("Brazil", "Campo Grande", "Mato Grosso do Sul");
            add(new Campeonato("major", LocalDate.now(), LocalDate.now(), localidade, timeRepository.obter("Sapecas"), jogoRepository.obter("FIFA").get(0)));
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

    public Campeonato obterPorNome(String nome) {
        return this.campeonatos.stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
    }

    public Campeonato obterPorId(int id) {
        return this.campeonatos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void salvar(Campeonato campeonato) {
        campeonato.setId(proximaChaveUnica++);
        campeonatos.add(campeonato);
    }

    public void remover(Campeonato campeonato) {
        this.campeonatos.remove(campeonato);
    }
}
