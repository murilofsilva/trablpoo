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
}