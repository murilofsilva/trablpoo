package modelos;

public class Time {
    private Long id;
    private String nome;
    private Integer numeroIntegrantes;
    private String coach;
    private Localidade localidade;

    public Time(String nome, int numeroIntegrantes, String coach, Localidade localidade){
        this.nome = nome;
        this.numeroIntegrantes = numeroIntegrantes;
        this.coach = coach;
        this.localidade = localidade;
    }
    public void setnumIntegrantes(int numeroIntegrantes){
        this.numeroIntegrantes = numeroIntegrantes;
    }

}
