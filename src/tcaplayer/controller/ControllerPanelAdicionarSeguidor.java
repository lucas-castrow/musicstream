/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

/**
 *
 * @author Lucas
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JDialog;
import tcaplayer.model.Seguidor;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCSeguidorDAO;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaEntrar;
import tcaplayer.view.JanelaPrincipal;
import tcaplayer.view.PanelAdicionarAmigo;
import tcaplayer.view.PanelEditarMeuPerfil;

/**
 *
 * @author Lucas
 */
public class ControllerPanelAdicionarSeguidor implements ActionListener, MouseListener{

    private PanelAdicionarAmigo janela;
    private JanelaPrincipal janelaPrincipal;
    private Conexao fabrica;
    private ControllerJanelaCadastroUsuario ctrlCadastrar;
    
    private JDBCUsuarioDAO usuarioDAO;
    private JDBCSeguidorDAO amigoDAO;
    private Perfil perfil;
    private Usuario usuario;
    private JDBCPerfilDAO perfilDAO;
    private JDialog dialog;
    
    public ControllerPanelAdicionarSeguidor(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.ctrlCadastrar = new ControllerJanelaCadastroUsuario(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
        this.amigoDAO = new JDBCSeguidorDAO(fabrica);
    }
    public void abrir(Usuario usuario,Perfil perfil,JanelaPrincipal janelaPrincipal){
        this.perfil = perfil;
        this.usuario = usuario;
        this.janela = new PanelAdicionarAmigo();
        this.janelaPrincipal = janelaPrincipal;
        this.janela.btAdicionar.addActionListener(this);
        this.janela.btAdicionar.addMouseListener(this);
      //  janela.setVisible(true);
        dialog = new JDialog(this.janelaPrincipal,"Buscar para seguir",true);
        dialog.setContentPane(this.janela);
        dialog.setSize(300,200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btAdicionar){
            adicionaAmigo();
        }
        
        
    }
    
    
    private void adicionaAmigo(){
        String text = janela.tfNomeAmigo.getText();
        int meuId = perfil.getUsuario().getId();
        if(amigoDAO.insereAmigo(meuId,text)){
            janela.mensagem("Seguindo usuario!");
        }else{
            janela.mensagem("Erro ao seguir este usuario!");
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
