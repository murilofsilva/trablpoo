package modelos.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum ModalidadeEnum {
    FPS(1),
    MOBA(2),
    BATTLE_ROYALE(3),
    FIGHTING(4),
    FUTEBOL(5);

    private static final Map<Integer, ModalidadeEnum> items = new HashMap<>();

    static {
        for (ModalidadeEnum modalidade : ModalidadeEnum.values()) {
            items.put(modalidade.obterValor(), modalidade);
        }
    }

    private final int valor;

    ModalidadeEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int obterValor() {
        return valor;
    }

    public static ModalidadeEnum obterModalidadePorValor(int valor) {
        return items.get(valor);
    }
}
