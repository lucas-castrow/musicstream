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
public class Musica {
    
    private int id;
    private String nome;
    private int duracao;
    private Usuario usuario;
    private File file;
    
    public Musica(int id,Usuario usuario,int duracao,String nome,File file){
        this.id = id;
        this.usuario = usuario;
        this.duracao = duracao;
        this.nome = nome;
        this.file = file;
    }
    public Musica(Usuario usuario,int duracao,String nome, File file){
        this.nome = nome;
        this.usuario = usuario;
        this.duracao = duracao;
        this.file = file;
    }
    public Musica(int id,Usuario usuario,int duracao,String nome){
        this.nome = nome;
        this.usuario = usuario;
        this.duracao = duracao;
        this.id = id;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
}
