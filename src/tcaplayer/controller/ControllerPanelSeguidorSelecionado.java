/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCSeguidorDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Seguidor;
import tcaplayer.model.Transmissao;
import tcaplayer.model.Usuario;
import tcaplayer.view.PanelSeguidorSelecionado;
import tcaplayer.view.PanelEditarMeuPerfil;
import tcaplayer.view.PanelMeuPerfil;

/**
 *
 * @author Lucas
 */
public class ControllerPanelSeguidorSelecionado implements ActionListener,MouseListener{
    
    private PanelSeguidorSelecionado janela;
    private Conexao fabrica;
    private JDBCUsuarioDAO usuarioDAO;
    private Perfil meuPerfil;
    private Usuario usuario;
    private JDBCPerfilDAO perfilDAO;
    private JDBCSeguidorDAO seguidorDAO;
    private JDialog dialog;
    private Perfil perfilAmigo;
    private ControllerPanelMensagemSeguidor ctrlMensagemAmigo;
    private ControllerPanelTransmitirServer ctrlServerTransmitir;
    private Thread controllerTransmitirServer;
    private Thread controllerTransmitirCliente;
    private Thread controllerMensagemSeguidor;
    public ControllerPanelSeguidorSelecionado(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
        this.seguidorDAO = new JDBCSeguidorDAO(fabrica);
        
        
        controllerTransmitirCliente = new Thread(new ControllerPanelTransmitirCliente(fabrica));
            
      
    }
    public void abrir(Usuario usuario,Perfil meuPerfil,Perfil perfilAmigo){
        this.meuPerfil = meuPerfil;
        this.usuario = usuario;
        this.perfilAmigo = perfilAmigo;
        this.janela = new PanelSeguidorSelecionado();
        this.janela.btTransmitir.addActionListener(this);
        this.janela.btMensagem.addActionListener(this);
        this.janela.btTransmitir.addMouseListener(this);
        this.janela.btMensagem.addMouseListener(this);
        this.janela.btConectar.addMouseListener(this);
        this.janela.btConectar.addActionListener(this);
        this.janela.btPararDeSeguir.addActionListener(this);
        this.janela.btPararDeSeguir.addMouseListener(this);
      //  janela.setVisible(true);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(perfilAmigo.getFoto().getAbsolutePath()).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        this.janela.lbAmigoFoto.setIcon(imageIcon);
        this.janela.lbAmigoSobre.setText(perfilAmigo.getSobre());
        this.janela.lbNomeAmigo.setText(perfilAmigo.getUsuario().getNome());
        dialog = new JDialog(this.dialog,"Amigo "+perfilAmigo.getUsuario().getNomeDeUsuario(),true);
        
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == janela.btTransmitir){
            dialog.dispose();
            Transmissao transmissao = new Transmissao(meuPerfil.getUsuario(),perfilAmigo.getUsuario(),"Musica");
            controllerTransmitirServer = new Thread(new ControllerPanelTransmitirServer(fabrica,transmissao));
            controllerTransmitirServer.start();
        }
        else if(e.getSource() == janela.btMensagem){
            dialog.dispose();
            mensagemPara();
            
        }else if(e.getSource() == janela.btConectar){
            dialog.dispose();
            controllerTransmitirCliente.start();
            
        }else if(e.getSource() == janela.btPararDeSeguir){
            pararDeSeguir();
        }

    }
    private void pararDeSeguir(){
       Seguidor dado = new Seguidor(meuPerfil.getUsuario(),perfilAmigo.getUsuario());
       if(seguidorDAO.pararDeSeguir(dado)){
           janela.mensagem("Você parou de Seguir "+perfilAmigo.getUsuario().getNomeDeUsuario());
           dialog.dispose();
       }else{
           janela.mensagem("Ocorreu um erro!");
       }
    }
    public void mensagemPara(){
      controllerMensagemSeguidor = new Thread(new ControllerPanelMensagemSeguidor(fabrica,usuario,meuPerfil,perfilAmigo));
      controllerMensagemSeguidor.start();
      //  ctrlMensagemAmigo.abrir(usuario, meuPerfil, perfilAmigo);
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
