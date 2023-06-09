package modelos;

import java.time.LocalDate;

public class Jogador extends Pessoa {
    private String nomeUsuario;

    public Jogador(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, String nomeUsuario, Jogo jogo) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.nomeUsuario = nomeUsuario;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Nome de usuário: %s\n",
                this.cpfCnpj,
                this.nome,
                this.nomeUsuario);
    }

    public String obterInformacoesDetalhadas() {
        return "******* INFORMAÇÕES DO JOGADOR *******\n" +
                super.obterInformacoesDetalhadas() +
                "\nNome de usuário: " +
                this.nomeUsuario +
                "\n******* ********************* *******";
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}