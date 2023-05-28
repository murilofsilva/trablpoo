package modelos;

import java.time.LocalDate;

public class Jogador extends Pessoa{
    Long id;
    private Long idTime;
    private String jogo;

    public Jogador(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, Long time, String jogo) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.idTime = time;
        this.jogo = jogo;
    }
}
