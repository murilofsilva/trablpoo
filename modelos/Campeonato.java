package modelos;

import java.time.LocalDate;
import java.util.List;

public class Campeonato {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Localidade localizacao;
    private List<Time> times;
    private Jogo jogo;

    public Campeonato(LocalDate dataInicio, LocalDate dataFim, Localidade localizacao, List<Time> times, Jogo jogo) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localizacao = localizacao;
        this.times = times;
        this.jogo = jogo;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
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
}
