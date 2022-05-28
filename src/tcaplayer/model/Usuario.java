/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Usuario implements Serializable{
    
    private int id;
    private String nome;
    private String email;
    private String senha;
    private Date dataNasc;
    private String nomeDeUsuario;
    private boolean online;

    public Usuario(){}
    
    public Usuario(String nome,String nomeDeUsuario,String email,String senha,Date dataNasc){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNasc = dataNasc;
        this.nomeDeUsuario = nomeDeUsuario;
    }
     public Usuario(int id,String nome,String nomeDeUsuario,String email,String senha,Date dataNasc){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNasc = dataNasc;
        this.nomeDeUsuario = nomeDeUsuario;
    }
    public Usuario(int id,String nome,String nomeDeUsuario,String email,String senha){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }
    public Usuario(String email,String senha){
        this.email = email;
        this.senha = senha;
    }
    public Usuario(int id,String nome,String nomeDeUsuario,String email,Date dataNasc){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.nomeDeUsuario = nomeDeUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
   
    
    
     
}
