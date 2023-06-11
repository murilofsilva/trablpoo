package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private int id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Localidade localizacao;
    private List<Time> times;
    private Jogo jogo;

    public Campeonato(String nome, LocalDate dataInicio, LocalDate dataFim, Localidade localizacao, List<Time> times, Jogo jogo) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localizacao = localizacao;
        this.times = times;
        this.jogo = jogo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Localidade getLocalizacao() {
        return this.localizacao;
    }

    public List<Time> getTimes() {
        return this.times;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void adicionarTime(Time time) {
        this.times.add(time);
    }

    public void removerTime(Time time) {
        this.times.remove(time);
    }

    public String obterInformacoes() {
        return String.format("ID: %d | Nome: %s | Data de início: %s | Data de fim: %s\n",
            this.id,
            this.nome,
            this.dataInicio,
            this.dataFim);
    }

    public String obterInformacoesDetalhadas() {
        List<String> nomesTimes = new ArrayList<>();

        for (Time time : times) {
            nomesTimes.add(time.getNome());
        }

        String nomes = String.join(", ", nomesTimes);

        return String.format("Nome: %s\nData de início: %s\nData de fim: %s\nLocalização: %s\nJogo: %s\nTimes: %s",
            this.nome,
            this.dataInicio,
            this.dataFim,
            this.localizacao,
            this.jogo.getNome(),
            nomes);
    }
}
