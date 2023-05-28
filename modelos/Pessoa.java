package modelos;
import java.time.LocalDate;

public abstract class Pessoa {
    protected final long id;
    protected String nome;
    protected final String cpfCnpj;
    protected LocalDate dataNascimento;
    protected Localidade localidade;

    public Pessoa(String nome, String cpfCnpj, LocalDate dataNascimento, Localidade localidade){
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
        this.localidade = localidade;
        this.id = System.currentTimeMillis();
    }

    public String getNome() {
        return this.nome;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public abstract String obterInformacoes();

    public String obterInformacoesDetalhadas() {
        return String.format("Nome: %s\nCPF: %s\nData do nascimento: %s", this.nome, this.cpfCnpj, this.dataNascimento);
    };
}