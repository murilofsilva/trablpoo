package repositories;

import modelos.Coach;
import modelos.Jogador;
import modelos.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRepository {
    private static int proximaChaveUnica = 1;
    private static final List<Time> times = new ArrayList() {
        {
            add(new Time(proximaChaveUnica,
                    "Sapecas do morro",
                    PessoaRepository.obterPorNome("Lucas").stream().map(pessoa -> (Jogador)pessoa).collect(Collectors.toList()),
                    (Coach)PessoaRepository.obter("23915319411"))
            );
            add(new Time(proximaChaveUnica+1, "Sapecas da montanha magica"));
        }
    };

    public static Time obter(int id) {
        return times.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public static List<Time> obter(String nome) {
        return times.stream().filter(time -> time.getNome().toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
    }

    public static void inserir() {
        proximaChaveUnica++;
    }
}