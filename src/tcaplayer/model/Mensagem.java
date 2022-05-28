/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Mensagem {
    
    private String mensagem;
    private Usuario remetente;
    private Usuario destinatario;
    private String data;
    private boolean status;
    private String hora ;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public Mensagem(Usuario remetente,Usuario destinatario,String mensagem,String data,String hora){
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.hora = hora;
        this.data = data;
               // lbteste.setText(""+sdf.format(cal.getTime()));
               
               SimpleDateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy");
              
               
               
        
        
    }
    public Mensagem(Usuario remetente,Usuario destinatario,String mensagem){
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        long horaLong = System.currentTimeMillis();
        cal.setTimeInMillis(horaLong);
        this.hora = sdf.format(cal.getTime());
        
        Date dataDate = cal.getTime();
                
       // DateFormat f = DateFormat.getDateInstance(DateFormat.FULL); //Data COmpleta
                
        this.data = diaFormat.format(dataDate);
        

        
        
    }
    public Mensagem(Usuario remetente,Usuario destinatario){
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    public Mensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getData() {
        return this.data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
