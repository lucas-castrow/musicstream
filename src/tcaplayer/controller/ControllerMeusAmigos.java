/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCSeguidorDAO;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.PanelMeusAmigos;

/**
 *
 * @author Lucas
 */
public class ControllerMeusAmigos implements ActionListener,MouseListener {
    
    private Conexao fabrica;
    private int tamanho;
    private PanelMeusAmigos janela;
    private Usuario usuario;
    private Perfil meuPerfil;
    private JDBCSeguidorDAO amigoDAO;
    private ControllerPanelSeguidorSelecionado ctrlAmigoSelecionado;
    private ArrayList<Perfil> perfilMeusAmigos;
    private JDBCPerfilDAO perfilDAO;
    private JDBCUsuarioDAO usuarioDAO;
    public ControllerMeusAmigos(Conexao fabrica){
        this.fabrica = fabrica;
        this.amigoDAO = new JDBCSeguidorDAO(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
        this.ctrlAmigoSelecionado = new ControllerPanelSeguidorSelecionado(fabrica);
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
    }
    
    
   
    public PanelMeusAmigos abrir(Usuario usuario,Perfil perfil){
        perfilMeusAmigos = amigoDAO.buscaMeusAmigos(usuario);
       
        this.usuario = usuario;
        this.meuPerfil = perfil;
        tamanho = perfilMeusAmigos.size();
        if(tamanho > 0){
            this.janela = new PanelMeusAmigos(tamanho);
            adicionaBotoes();
            for(int a = 0;a<tamanho;a++){
                this.janela.btAmigos[a].addActionListener(this);
                this.janela.btAmigos[a].setBackground(Color.white);
                this.janela.btAmigos[a].addMouseListener(this);
            }
        janela.setVisible(true); 
        }else{
            this.janela = new PanelMeusAmigos(1);
            janela.btAmigos[0] = new JButton("Você não tem Seguidores!");
            janela.painelAlign.add(janela.btAmigos[0]);
            janela.painelAlign2.add(janela.painelAlign);
            JScrollPane scrollPane = new JScrollPane(janela.painelAlign2);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            scrollPane.setBounds(0, 170, 200, 200);
            JPanel contentPane = new JPanel(null);
            contentPane.setPreferredSize(new Dimension(200, 100));
            contentPane.add(scrollPane);
            janela.add(contentPane,BorderLayout.WEST);

        }
        return this.janela;
    }

    
    
    
    private void adicionaBotoes(){
        
        for(int x = 0;x<tamanho;x++){

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(perfilMeusAmigos.get(x).getFoto().getAbsolutePath()).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
            this.janela.lbFotoAmigo = new JLabel();
            this.janela.lbFotoAmigo.setSize(25, 25);
            this.janela.lbFotoAmigo.setIcon(imageIcon);
            
            janela.btAmigos[x] = new JButton("<html><p style=\"width:75px\">"+perfilMeusAmigos.get(x).getUsuario().getNomeDeUsuario()+"</p></html>");
            janela.painelAlign3.add(this.janela.lbFotoAmigo);
            janela.painelAlign.add(janela.btAmigos[x]);
        }
        janela.painelAlign2.add(janela.painelAlign3);
        janela.painelAlign2.add(janela.painelAlign);
     
        //janela.add(janela.painelAlign2,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(janela.painelAlign2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
        scrollPane.setBounds(0, 170, 200, 200);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(200, 100));
        contentPane.add(scrollPane);
        janela.add(contentPane,BorderLayout.WEST);
           
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int y = 0;y<tamanho;y++){
            if(e.getSource() == janela.btAmigos[y]){
                amigoSelecionado(y);
            }
        }
        
        
    }
    private void amigoSelecionado(int x){
     int idAmigo = perfilMeusAmigos.get(x).getUsuario().getId();
     
     Perfil perfilAmigo = perfilDAO.buscaPerfilId(idAmigo);
     //Usuario amigoUsuario = usuarioDAO.buscaUsuarioId(idAmigo);
     
     
     ctrlAmigoSelecionado.abrir(usuario,meuPerfil,perfilAmigo);
        
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
        for(int y = 0;y<tamanho;y++){
            if(e.getSource() == janela.btAmigos[y]){
                this.janela.setCursor(Cursor.getPredefinedCursor(12));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(int y = 0;y<tamanho;y++){
            if(e.getSource() == janela.btAmigos[y]){
                this.janela.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }
    
}

    
    
    

