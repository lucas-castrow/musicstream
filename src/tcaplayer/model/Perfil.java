/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.File;

/**
 *
 * @author Lucas
 */
public class Perfil {
    
    private Usuario usuario;
    private Cidade cidade_natal;
    private File foto;
    private String sobre;
    
    public Perfil(Usuario usuario,String sobre){
        this.usuario = usuario;
        this.sobre = sobre;
        if(sobre == null){
            this.sobre = "Hey there! I am using StreamMusic";
        }
    }
    public Perfil(Usuario usuario,Cidade cidade_natal,String sobre){
        this.usuario = usuario;
        this.cidade_natal = cidade_natal;
        this.sobre = sobre;
        
       if(sobre == null){
            this.sobre = "Hey there! I am using StreamMusic";
        }
    }
    public Perfil(Usuario usuario,Cidade cidade_natal,File foto,String sobre){
        this.usuario = usuario;
        this.cidade_natal = cidade_natal;
        this.sobre = sobre;
        this.foto = foto;
         if(sobre == null){
            this.sobre = "Hey there! I am using StreamMusic";
        }
    }
  
   

   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cidade getCidade_natal() {
        return cidade_natal;
    }

    public void setCidade_natal(Cidade cidade_natal) {
        this.cidade_natal = cidade_natal;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
    
}
