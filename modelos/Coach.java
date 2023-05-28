package modelos;

import java.time.LocalDate;

public class Coach extends Pessoa {
    private Long id;
    private Time time;

    public Coach(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, Time time) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.time = time;
    }

    public Time getTime() {
        return this.time;
    }
}
