package modelos.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum EntidadeMenuEnum {
    AUXILIAR(1),
    ORGANIZADOR(2),
    GERENTE(3),
    JOGADOR(4),
    COACH(5),
    EVENTO(6),
    TIME(7),
    FORNECEDOR(8);

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