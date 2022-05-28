/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCMusicaDAO;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCSeguidorDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Musica;
import tcaplayer.model.Usuario;
import tcaplayer.view.PanelMeusAmigos;
import tcaplayer.view.PanelMusicasPostadas;

/**
 *
 * @author Lucas
 */
public class ControllerMusicasPostadas implements ActionListener,MouseListener {


    private ArrayList<Musica> todasMusicas;
    private JDBCPerfilDAO perfilDAO;
    private JDBCUsuarioDAO usuarioDAO;
    private JDBCMusicaDAO musicaDAO;
    private Conexao fabrica;
    private PanelMusicasPostadas janela;
    private ControllerInfoMusica ctrlInfoMusica;
    public ControllerMusicasPostadas(Conexao fabrica){
        this.fabrica = fabrica;
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.musicaDAO = new JDBCMusicaDAO(fabrica);
        this.ctrlInfoMusica = new ControllerInfoMusica(fabrica);
    }
    
      public PanelMusicasPostadas abrir(){
        todasMusicas = musicaDAO.todasMusicas();
        int tam = todasMusicas.size();
        if(tam>0){
        this.janela = new PanelMusicasPostadas(tam);
        adicionaBotoes(tam);
        for(int a = 0;a<tam;a++){
            this.janela.btMusicas[a].addActionListener(this);
            this.janela.btMusicas[a].setBackground(new Color(255, 124, 68));
            this.janela.btMusicas[a].addMouseListener(this);
        }
        }else{
            this.janela = new PanelMusicasPostadas(1);
            janela.btMusicas[0] = new JButton("<html><p style=\"width:152px\">"+"Não há musicas postadas para mostrar</p></html>");
            janela.painelAlign.add(janela.btMusicas[0]);
            janela.painelAlign2.add(janela.painelAlign);
            janela.add(janela.painelAlign2);
        }
        janela.setVisible(true); 
        
        return this.janela;
    }
    
    
    
    private void adicionaBotoes(int tamanho){
        
        for(int x = 0;x<tamanho;x++){
           
            janela.btMusicas[x] = new JButton("<html><p style=\"width:252px\">"+pegaNomeMusica(x)+"</p></html>");
            janela.painelAlign.add(janela.btMusicas[x]);
        }
        janela.painelAlign2.add(janela.painelAlign);
        janela.add(janela.painelAlign2);
        
    }
    private String pegaNomeMusica(int i){
        String name = todasMusicas.get(i).getNome();
        int tm = name.length();
        name = name.substring(0,tm - 4);
        return name;
    }





    @Override
    public void actionPerformed(ActionEvent e) {
           for(int y = 0;y<todasMusicas.size();y++){
            if(e.getSource() == janela.btMusicas[y]){
                musicaSelecionada(y);
            }
        }
    }
    private void musicaSelecionada(int y){
        ctrlInfoMusica.abrir(todasMusicas.get(y));
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
          for(int y = 0;y<todasMusicas.size();y++){
            if(e.getSource() == janela.btMusicas[y]){
                this.janela.setCursor(Cursor.getPredefinedCursor(12));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
         for(int y = 0;y<todasMusicas.size();y++){
            if(e.getSource() == janela.btMusicas[y]){
                this.janela.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }
    
}
