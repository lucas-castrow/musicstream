/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Lucas
 */
public class Recuperar {
 
    private static String hostName = "smtp.gmail.com";
    private static String nomeRemetente = "Mustream";
    private static String remetente = "mustream.robot@gmail.com";
    private String destinatarios;
    private String assuntoEmail ; 
    private String mensagemEmail; 
    private static String usuarioEmail = remetente;
    private static String senhaEmail = "mustream1";
    private static int portaEnvio = 465;
    
    
    public Recuperar(String destinatarios,String assuntoEmail,String mensagemEmail) throws EmailException{
        this.destinatarios = destinatarios;
        this.assuntoEmail = assuntoEmail;
        this.mensagemEmail = mensagemEmail;
        
        SimpleEmail email = new SimpleEmail();
        //configura o hostname
        email.setHostName(hostName);
        //configura porta de envio
        email.setSmtpPort(portaEnvio);
        //adiciona os emails de destino
        email.addTo(destinatarios);
        //configura o email do remetente
        email.setFrom(remetente, nomeRemetente);
        //adiciona o assunto
        email.setSubject(assuntoEmail);
        //mensagem do email
        email.setMsg(mensagemEmail);
        //autenticacao no servidor
        email.setSSL(true);
        email.setTLS(true);
        //usuario e senha do remetente
        email.setAuthentication(usuarioEmail, senhaEmail);
        email.send();
    }
}
