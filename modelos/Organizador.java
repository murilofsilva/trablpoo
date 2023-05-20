package modelos;

import java.time.LocalDate;

public class Organizador extends Pessoa {
    private final String codigoOrganizador;
    public Organizador(String nome, String cpfCnpj, LocalDate dataNascimento) {
        super(nome, cpfCnpj, dataNascimento, new Localidade("Brazil", "Campo Grande", "MS"));
        codigoOrganizador = Math.random() + "$d@" + System.currentTimeMillis();
    }

    public String getCodigoOrganizador() {
        return this.codigoOrganizador;
    }
}