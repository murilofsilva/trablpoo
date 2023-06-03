package modelos;

import java.time.LocalDate;

public class Coach extends Pessoa {

    public Coach(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade) {
        super(nome, cpfCnpj, dataNascimento, localidade);
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s\n",
                this.cpfCnpj,
                this.nome);
    }

    public String obterInformacoesDetalhadas() {
        return "******* INFORMAÇÕES DO COACH *******\n" +
                super.obterInformacoesDetalhadas() +
                "\n******* ******************* *******";
    }
}
