package modelos;

import java.util.List;

public class Time {
    private final int id;
    private String nome;
    private List<Jogador> jogadores;
    private int numeroIntegrantes;
    private Coach coach;

    public Time(int id, String nome, List<Jogador> jogadores, Coach coach){
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
        this.numeroIntegrantes = jogadores.size() + 1;
        this.coach = coach;
    }

    public Time(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return this.id; }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Coach getCoach() {
        return this.coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Jogador> getJogadores() {
        return this.jogadores;
    }

    public int getNumeroIntegrantes() {
        return this.numeroIntegrantes;
    }
}
