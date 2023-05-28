package modelos;

import java.util.List;

public class Time {
    private Long id;
    private String nome;
    private List<Jogador> jogadores;
    private Integer numeroIntegrantes;
    private Coach coach;
    private Localidade localidade;
    private Integer maxJogadores;

    public Time(String nome, List<Jogador> jogadores, Coach coach, Localidade localidade, Integer maxJogadores){
        this.nome = nome;
        this.jogadores = jogadores;
        this.numeroIntegrantes = jogadores.size() + 1;
        this.coach = coach;
        this.localidade = localidade;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Jogador> getJogadores() {
        return this.jogadores;
    }

    public Integer getNumeroIntegrantes() {
        return this.numeroIntegrantes;
    }

    public Coach getCoach() {
        return this.coach;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }
}
