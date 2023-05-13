package modelos;

public class Localidade {
    private String pais;
    private String municipio;
    private String estado;

    public Localidade(String pais, String municipio, String estado){
        this.pais = pais;
        this.municipio = municipio;
        this.estado = estado;
    }
    public void setPais(String pais){
        this.pais = pais;
    }
    public void setMunicipio(String municipio){
        this.municipio = municipio;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public String getPais(){
        return this.pais;
    }
    public String getMunicipio(){
        return this.municipio;
    }
    public String getEstado(){
        return this.estado;
    }
}
