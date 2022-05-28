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
public class Pais {
    
    private int id;
    private String nome;
    private String sigla;
    
    public Pais(String nome,String sigla){
        this.nome = nome;
        this.sigla = sigla;
    }
    public Pais(int id,String nome,String sigla){
        this.nome = nome;
        this.id = id;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
    
}
