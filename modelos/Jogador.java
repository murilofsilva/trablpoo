package modelos;

import java.time.LocalDate;

public class Jogador extends Pessoa{
    Long id;
    private Long idTime;
    private String jogo;
    private Time time;

    public Jogador(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, String jogo, Time time) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.jogo = jogo;
        this.time = time;
    }

    public Time getTime() {
        return this.time;
    }
}
