package modelos;

import java.time.LocalDate;

public class Organizador extends Funcionario {
    private final String codigoOrganizador;
    private int numeroCampeonatosOrganizados = 0;

    public Organizador(String nome, String cpfCnpj, LocalDate dataNascimento, double salarioBase, LocalDate dataEntrada) {
        super(nome, cpfCnpj, dataNascimento, salarioBase, dataEntrada);
        codigoOrganizador = Math.random() + "$d@" + System.currentTimeMillis();
        this.calculaSalario();
    }

    protected void calculaSalario() {
        this.salarioReal = this.salarioBase +
                (LocalDate.now().getYear() - this.dataEntrada.getYear()) * 1.9 +
                this.numeroCampeonatosOrganizados * 20;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Código de segurança: %s",
                this.cpfCnpj,
                this.nome,
                this.codigoOrganizador);
    }

    public String obterInformacoesDetalhadas() {
        return super.obterInformacoesDetalhadas() +
                "\nCódigo de segurança: " +
                this.codigoOrganizador +
                "\nSalário atual: " +
                this.salarioReal +
                "\nNúmero de campeonatos organizados: " +
                this.numeroCampeonatosOrganizados;
    }

    public String getCodigoOrganizador() {
        return this.codigoOrganizador;
    }

    public void incrementarNumeroCampeonatosOrganizados() {
        this.numeroCampeonatosOrganizados++;
    }

    public void setNumeroCampeonatosOrganizados(int num) {
        this.numeroCampeonatosOrganizados = num;
    }
}