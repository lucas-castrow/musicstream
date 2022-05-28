/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import org.apache.commons.mail.EmailException;
import tcaplayer.model.Conexao;
import tcaplayer.model.Usuario;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Recuperar;
import tcaplayer.view.JanelaRecuperarSenha;

/**
 *
 * @author Lucas
 */
public class ControllerJanelaRecuperarSenha implements ActionListener,MouseListener{
 
    private Conexao fabrica;
    private JanelaRecuperarSenha janela;
    private Usuario usuario;
    private JDBCUsuarioDAO usuarioDAO;
    private JDialog dialog;
    
    
    public ControllerJanelaRecuperarSenha(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        
    }
    
    
    public void abrir(){
        this.janela = new JanelaRecuperarSenha();
        this.janela.btEnviar.addMouseListener(this);
        this.janela.btEnviar.addActionListener(this);
        dialog = new JDialog();
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setVisible(true); 
        dialog.setLocationRelativeTo(null);
       
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btEnviar){
            recuperar();
        }
    }
    
    private void recuperar(){
        janela.setCursor(Cursor.getPredefinedCursor(3));
        String nick = janela.tfUsername.getText();
        Usuario userRecovery = usuarioDAO.recuperar(nick);
        String assuntoEmail = "Olá "+userRecovery.getNome()+", você solicitou a recuperação de senha";
        String mensagemEmail = "O Mustream está reenviando sua senha perdida, Sua senha é ["+userRecovery.getSenha()+"]";
        
        
        try {
            Recuperar recovery = new Recuperar(userRecovery.getEmail(),assuntoEmail,mensagemEmail);
        } catch (EmailException ex) {
            
        }
        janela.setCursor(Cursor.getPredefinedCursor(12));
        janela.mensagem("A sua senha foi enviada para o endereço de email "+userRecovery.getEmail());
            
        dialog.dispose();
    }
    
    
     @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == janela.btEnviar){
                janela.setCursor(Cursor.getPredefinedCursor(12));
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == janela.btEnviar){
                janela.setCursor(Cursor.getPredefinedCursor(0));
        }  
    }

}
