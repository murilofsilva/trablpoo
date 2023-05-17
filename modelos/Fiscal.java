package modelos;
import java.time.LocalDate;

public class Fiscal extends Pessoa{
    // private Time nome; Como saber para qual time o fiscal será mandado?

    public Fiscal(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade) {
        super(nome, cpfCnpj, dataNascimento, localidade);
    }
}
