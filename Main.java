import modelos.Campeonato;
import modelos.Pessoa;
import modelos.Time;
import processos.MenuService;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        MenuService menuService = new MenuService();

        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<Campeonato> campeonatos = new ArrayList<>();
        ArrayList<Time> times = new ArrayList<>();

        menuService.processaMenu();
    }
}
