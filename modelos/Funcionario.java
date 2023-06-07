package modelos;

import java.time.LocalDate;
import java.util.Date;

public abstract class Funcionario extends Pessoa {
    protected double salarioBase;
    protected LocalDate dataEntrada;
    protected double salarioReal;

    public Funcionario(String nome, String cpfCnpj, LocalDate dataNascimento, double salarioBase, LocalDate dataEntrada) {
        super(nome, cpfCnpj, dataNascimento);
        this.salarioBase = salarioBase;
        this.dataEntrada = dataEntrada;
    }

    protected abstract void calculaSalario();

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
        this.calculaSalario();
    }
}
