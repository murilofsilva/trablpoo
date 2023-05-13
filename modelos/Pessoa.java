package modelos;
import java.time.LocalDate;

public abstract class Pessoa {
    protected long id;
    protected String nome;
    protected String cpfCnpj;
    protected LocalDate dataNascimento;
    protected Localidade localidade;

    public Pessoa(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade){
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
        this.localidade = localidade;

    }

}
