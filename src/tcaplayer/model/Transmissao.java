/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Transmissao {
    
    private int id;
    private Usuario transmissor;
    private Usuario receptor;
    private long inicio_transmissao;
    private long fim_transmissao;
    private String nome;
    
    public Transmissao(int id,Usuario transmissor,Usuario receptor,long inicio_transmissao,long fim_transmissao,String nome){
        this.id = id;
        this.transmissor = transmissor;
        this.receptor = receptor;
        this.inicio_transmissao = inicio_transmissao;
        this.fim_transmissao = fim_transmissao;
        this.nome = nome;
        
    }
      public Transmissao(Usuario transmissor,Usuario receptor ,String nome){
        this.transmissor = transmissor;
        this.receptor = receptor;
        this.nome = nome;
        this.inicio_transmissao = System.currentTimeMillis();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getTransmissor() {
        return transmissor;
    }

    public void setTransmissor(Usuario transmissor) {
        this.transmissor = transmissor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public long getInicio_transmissao() {
        return inicio_transmissao;
    }

    public void setInicio_transmissao(long inicio_transmissao) {
        this.inicio_transmissao = inicio_transmissao;
    }

    public long getFim_transmissao() {
        return fim_transmissao;
    }

    public void setFim_transmissao(long fim_transmissao) {
        this.fim_transmissao = fim_transmissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
