package repositories;

import modelos.Jogo;
import modelos.enumerators.ModalidadeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JogoRepository {
    public static ArrayList<Jogo> jogos = new ArrayList<>() {
        {
            add(new Jogo("Free fire",
                    ModalidadeEnum.BATTLE_ROYALE)
            );
            add(new Jogo("FIFA 23",
                    ModalidadeEnum.FUTEBOL)
            );
        }
    };

    public static List<Jogo> obterPorNome(String nome) {
        return jogos.stream().filter(jogo -> jogo.getNome().toLowerCase().trim()
                .replace(" ", "").contains(nome.toLowerCase().trim()
                        .replace(" ", ""))).collect(Collectors.toList());
    }

    public static void salvar(Jogo jogo) {
        jogos.add(jogo);
    }
}
