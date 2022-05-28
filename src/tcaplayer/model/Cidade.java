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
public class Cidade {
    private int id;
    private String nome;
    private Estado estado;
    
    public Cidade(Estado estado,String nome){
        this.estado = estado;
        this.nome = nome;
    }
    public Cidade(int id,Estado estado,String nome){
        this.id = id;
        this.estado = estado;
        this.nome = nome;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
    
    @Override
    public String toString(){
        return this.nome;
    }
    
}
