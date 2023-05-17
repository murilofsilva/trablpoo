package modelos;

public class Time {
    protected String nome;
    protected int numeroIntegrantes;
    protected String coach;
    protected Fiscal fiscal;
    protected Localidade pais;

    public Time(String nome, int numeroIntegrantes, String coach, Fiscal fiscal, Localidade pais){
        this.nome = nome;
        this.numeroIntegrantes = numeroIntegrantes;
        this.coach = coach;
        this.fiscal = fiscal;
        this.pais = pais;
    }
    public void setnumIntegrantes(int numeroIntegrantes){
        this.numeroIntegrantes = numeroIntegrantes;
    }

}
