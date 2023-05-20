import modelos.Campeonato;
import modelos.Pessoa;
import modelos.Time;
import processos.MenuService;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<Campeonato> campeonatos = new ArrayList<>();
        ArrayList<Time> times = new ArrayList<>();

        MenuService.processaMenu();
    }
}