package repositories;

import modelos.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeRepository {
    private static int proximaChaveUnica = 1;
    private static List<Time> times = new ArrayList<>() {
        {
            add(new Time(proximaChaveUnica, "Sapecas do morro"));
        }
    };

    public static Time obter(int id) {
        return times.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public static void inserir() {
        proximaChaveUnica++;
    }
}