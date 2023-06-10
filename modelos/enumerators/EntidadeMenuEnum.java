package modelos.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum EntidadeMenuEnum {
    AUXILIAR(1),
    CAMPEONATO(2),
    COACH(3),
    GERENTE(4),
    JOGADOR(5),
    JOGO(6),
    ORGANIZADOR(7),
    TIME(8);

    private static final Map<Integer, EntidadeMenuEnum> items = new HashMap<>();

    static {
        for (EntidadeMenuEnum entidade : EntidadeMenuEnum.values()) {
            items.put(entidade.obterValor(), entidade);
        }
    }

    private final int valor;

    EntidadeMenuEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int obterValor() {
        return valor;
    }

    public static EntidadeMenuEnum obterEntidadePorValor(int valor) {
        return items.get(valor);
    }
}