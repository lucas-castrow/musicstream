/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import static java.awt.BorderLayout.WEST;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.PanelEditarMeuPerfil;
import tcaplayer.view.PanelMeuPerfil;

/**
 *
 * @author Lucas
 */
public class ControllerPanelMeuPerfil implements ActionListener,MouseListener{
    
    private PanelMeuPerfil janela;
    private Conexao fabrica;
    private ControllerPanelEditarPerfil ctrlEditarPerfil;
    private ControllerJanelaPrincipal ctrlPrincipal;
    private JDBCUsuarioDAO usuarioDAO;
    private JDBCPerfilDAO perfilDAO;
    private JDialog dialog;
    private Perfil perfil;
    private Usuario user;
    
    public ControllerPanelMeuPerfil(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.ctrlEditarPerfil = new ControllerPanelEditarPerfil(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
       // this.ctrlPrincipal = new ControllerJanelaPrincipal(fabrica);
      
    }
    
    public PanelMeuPerfil abrir(Perfil perfil,Usuario user){
        this.user = user;
        this.perfil = perfil;
        
        this.janela = new PanelMeuPerfil();
        
        this.janela.btEditarPerfil.addActionListener(this);
        this.janela.btEditarPerfil.addMouseListener(this);
        
        
        atualizaPerfil();
        return this.janela;
    }
    
    private void atualizaFoto(){
    //    perfilDAO.baixaFoto(perfil);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(perfil.getFoto().getAbsolutePath()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
       
        this.janela.lbFoto.setIcon(imageIcon);
    }
    
   public void atualizaPerfil(){
        this.janela.lbSobre.setText("<html><p style=\"width:170px\">"+perfil.getSobre()+ "</p></html>");
        this.janela.lbNomeUser.setText(user.getNome());
       
        this.janela.lbCidade.setText(""+perfil.getCidade_natal());
        atualizaFoto();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == janela.btEditarPerfil){
           ctrlEditarPerfil.abrir(user,perfil,janela);
           
           atualizaPerfil();
          
           System.out.println("Clicado!");
        }
        

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
        if(e.getSource() == janela.btEditarPerfil){
                janela.setCursor(Cursor.getPredefinedCursor(12));
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == janela.btEditarPerfil){
                janela.setCursor(Cursor.getPredefinedCursor(0));
        }  
    }
    
}
