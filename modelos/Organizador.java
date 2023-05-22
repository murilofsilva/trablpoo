package modelos;

import java.time.LocalDate;

public class Organizador extends Pessoa {
    private final String codigoOrganizador;
    public Organizador(String nome, String cpfCnpj, LocalDate dataNascimento, String pais, String municipio, String estado) {
        super(nome, cpfCnpj, dataNascimento, new Localidade(pais, municipio, estado));
        codigoOrganizador = Math.random() + "$d@" + System.currentTimeMillis();
    }

    public String getCodigoOrganizador() {
        return this.codigoOrganizador;
    }
}