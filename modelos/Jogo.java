package modelos;

import modelos.enumerators.ModalidadeEnum;

public class Jogo {
    private String nome;
    private ModalidadeEnum modalidade;

    public Jogo(String nome, ModalidadeEnum modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return this.nome;
    }

    public ModalidadeEnum getModalidade() {
        return this.modalidade;
    }
}
