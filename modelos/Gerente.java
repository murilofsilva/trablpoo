package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {
    private final String setorGerenciado;

    public Gerente(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade, String setorGerenciado) {
        super(nome, cpfCnpj, dataNascimento, localidade);
        this.setorGerenciado = setorGerenciado;
    }

    public String obterInformacoes() {
        return String.format("CPF: %s | Nome: %s | Setor gerenciado no momento: %s\n",
                this.cpfCnpj,
                this.nome,
                this.setorGerenciado);
    }

    public String obterInformacoesDetalhadas() {
        return "******* INFORMAÇÕES DO GERENTE *******\n" +
                super.obterInformacoesDetalhadas() +
                "\nSetor gerenciado: " +
                this.setorGerenciado +
                "\n******* ********************** *******";
    }

    public static List<Pessoa> filtrarGerentes(List<Pessoa> pessoas) {
        List<Pessoa> gerentes = new ArrayList<>();
        for (Pessoa pessoa: pessoas) {
            if (pessoa instanceof Gerente)
                gerentes.add(pessoa);
        }
        return gerentes;
    }
}
