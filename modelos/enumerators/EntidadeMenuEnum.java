package modelos.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum EntidadeMenuEnum {
    ORGANIZADOR(1),
    GERENTE(2),
    EVENTO(3),
    AUXILIAR(4),
    JOGADOR(5),
    TECNICO(6),
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