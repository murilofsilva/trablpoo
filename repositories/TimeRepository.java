package repositories;

import modelos.Coach;
import modelos.Jogador;
import modelos.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRepository {
    private static final PessoaRepository pessoaRepository = new PessoaRepository();
    private static int proximaChaveUnica = 1;
    public static List<Time> times = new ArrayList() {
        {
            add(new Time(proximaChaveUnica,
                    "Montanha",
                    pessoaRepository.obterPorNome("Lucas").stream().map(pessoa -> (Jogador) pessoa).collect(Collectors.toList()),
                    (Coach) pessoaRepository.obter("23915319411"))
            );
            add(new Time(proximaChaveUnica + 1, "Os incríveis"));
        }
    };

    public Time obter(int id) {
        return times.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public List<Time> obter(String nome) {
        return times.stream().filter(time -> time.getNome().toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
    }

    public Time obterExatamente(String nome) {
        Time timeEncontrado = null;

        for (Time time : times) {
            if (time.getNome().equals(nome))
                timeEncontrado = time;
        }

        return timeEncontrado;
    }

    public void salvar(Time time) {
        time.setId(proximaChaveUnica);
        times.add(time);
        proximaChaveUnica++;
    }

    public void remover(Time time) {
        this.times.remove(time);

    }
}