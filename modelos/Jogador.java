package modelos;
import java.time.LocalDate;

public class Jogador extends Pessoa{
    private Time time;

    public Jogador(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, Time time) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.time = time;
    }
}
