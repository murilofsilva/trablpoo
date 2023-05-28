package modelos;

import java.time.LocalDate;

public class Organizador extends Pessoa {
    private final String codigoOrganizador;

    public Organizador(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        codigoOrganizador = Math.random() + "$d@" + System.currentTimeMillis();
    }

    public String getCodigoOrganizador() {
        return this.codigoOrganizador;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Código de segurança: %s",
                this.cpfCnpj,
                this.nome,
                this.codigoOrganizador);
    }

    public String obterInformacoesDetalhadas() {
        return "******* INFORMAÇÕES DO ORGANIZADOR *******\n" +
                super.obterInformacoesDetalhadas() +
                "\nCódigo de segurança: " +
                this.codigoOrganizador +
                "\n******* ********************** *******";
    }
}