package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario {
    private final String setorGerenciado;
    private final List<Funcionario> funcionariosGerenciados;

    public Gerente(String nome, String cpfCnpj, LocalDate dataNascimento, double salarioBase, LocalDate dataEntrada,
                   String setorGerenciado) {
        super(nome, cpfCnpj, dataNascimento, salarioBase, dataEntrada);
        this.setorGerenciado = setorGerenciado;
        this.funcionariosGerenciados = new ArrayList<>();
        this.calculaSalario();
    }

    protected void calculaSalario() {
        this.salarioReal = this.salarioBase +
                (LocalDate.now().getYear() - this.dataEntrada.getYear()) * 100 +
                this.funcionariosGerenciados.size() * 4;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Setor gerenciado: %s\n",
                this.cpfCnpj,
                this.nome,
                this.setorGerenciado);
    }

    public String obterInformacoesDetalhadas() {
        List<String> nomesFuncionariosGerenciados = new ArrayList<>();

        for(Funcionario funcionario: funcionariosGerenciados) {
            nomesFuncionariosGerenciados.add(funcionario.getNome());
        }

        String nomesConcatenados = String.join(", ", nomesFuncionariosGerenciados);
        return super.obterInformacoesDetalhadas() +
                "\nSetor gerenciado: " +
                this.setorGerenciado +
                "\nSalário atual: " +
                this.salarioReal +
                "\nData de entrada: " +
                this.dataEntrada +
                "\nFuncionários gerenciados: " +
                (this.funcionariosGerenciados.size() == 0 ? "Sem funcionários" : nomesConcatenados);
    }

    public void adicionarFuncionarioGerenciado(Funcionario funcionario)  {
        this.funcionariosGerenciados.add(funcionario);
    }
}
