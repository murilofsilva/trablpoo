package modelos;

import java.time.LocalDate;

public class Auxiliar extends Funcionario {
    public Auxiliar(String nome, String cpfCnpj, LocalDate dataNascimento, double salarioBase, LocalDate dataEntrada) {
        super(nome, cpfCnpj, dataNascimento, salarioBase, dataEntrada);
        this.calculaSalario();
    }

    protected void calculaSalario() {
        this.salarioReal = this.salarioBase + (LocalDate.now().getYear() - this.dataEntrada.getYear()) * 0.9;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Salário: %f\n",
                this.cpfCnpj,
                this.nome,
                this.salarioReal);
    }

    public String obterInformacoesDetalhadas() {
        return super.obterInformacoesDetalhadas() +
                String.format("\nSalário atual: %f\nData de entrada: %s", this.salarioReal, this.dataEntrada);
    }
}
