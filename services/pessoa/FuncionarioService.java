package services.pessoa;

import modelos.Funcionario;
import util.ConsoleResources;

public abstract class FuncionarioService extends PessoaService {
    protected static final PessoaRemocaoService pessoaRemocaoService = new PessoaRemocaoService();

    protected void editarSalarioBase(Funcionario funcionario) {
        ConsoleResources.pularVariasLinhas();
        double salario = consoleResources.getDoubleFromConsole("Informe o novo salário base: ");
        funcionario.setSalarioBase(salario);
    }
}
