/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

/**
 *
 * @author Lucas
 */
public class Estado {
    
    private int id;
    private Pais pais;
    private String nome;
    private String uf;
    
    
    public Estado(Pais pais,String nome,String uf){
        this.pais = pais;
        this.nome = nome;
        this.uf = uf;
        
    }
    public Estado(int id,Pais pais,String nome,String uf){
        this.pais = pais;
        this.nome = nome;
        this.uf = uf;
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
