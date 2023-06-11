package modelos;

import modelos.enumerators.ModalidadeEnum;

public class Jogo {
    private Long id;
    private String nome;
    private ModalidadeEnum modalidade;

    public Jogo(String nome, ModalidadeEnum modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
    }

    public String obterInformacoes() {
        return "ID: " + this.id + ", nome: " + this.nome + ", modalidade: " + modalidade.obterValor();
    }

    public String getNome() {
        return this.nome;
    }

    public ModalidadeEnum getModalidade() {
        return this.modalidade;
    }
}
