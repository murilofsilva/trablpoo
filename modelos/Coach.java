package modelos;

import java.time.LocalDate;

public class Coach extends Pessoa {
    private Time time;

    public Coach(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, Time time) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.time = time;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Nome do time atual: %s\n",
                this.cpfCnpj,
                this.nome,
                this.time.getNome());
    }

    public String obterInformacoesDetalhadas() {
        return "******* INFORMAÇÕES DO COACH *******\n" +
                super.obterInformacoesDetalhadas() +
                "\nTime atual: " +
                this.time.getNome() +
                "\n******* ******************* *******";
    }
}
