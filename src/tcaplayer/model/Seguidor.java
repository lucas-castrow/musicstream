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
public class Seguidor {
    
    private Usuario eu;
    private Usuario amigo;
    private int id;
    
    public Seguidor(Usuario eu,Usuario amigo){
        this.eu = eu;
        this.amigo = amigo;
        
    }
    public Seguidor(int id,Usuario eu,Usuario amigo){
        this.id = id;
        this.eu = eu;
        this.amigo = amigo;
    }

    public Usuario getEu() {
        return eu;
    }

    public void setEu(Usuario eu) {
        this.eu = eu;
    }

    public Usuario getAmigo() {
        return amigo;
    }

    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
