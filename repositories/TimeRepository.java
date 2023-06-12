package repositories;

import modelos.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRepository {
    private static int proximaChaveUnica = 1;
    public static List<Time> times = new ArrayList<>();

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
        times.remove(time);
    }
}