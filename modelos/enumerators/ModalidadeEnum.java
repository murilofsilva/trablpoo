package modelos.enumerators;

import java.util.HashMap;
import java.util.Map;

public enum ModalidadeEnum {
    FPS("fps"),
    MOBA("moba"),
    BATTLE_ROYALE("battle royale"),
    FIGHTING("fighting"),
    FUTEBOL("futebol");

    private static final Map<String, ModalidadeEnum> items = new HashMap<>();

    static {
        for (ModalidadeEnum modalidade : ModalidadeEnum.values()) {
            items.put(modalidade.obterValor(), modalidade);
        }
    }

    private final String valor;

    ModalidadeEnum(String valorOpcao) {
        valor = valorOpcao;
    }

    public String obterValor() {
        return valor;
    }

    public static ModalidadeEnum obterModalidadePorValor(int valor) {
        return items.get(valor);
    }
}
