/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class PanelInfoMusica extends JPanel {
    
    public JLabel lbNomeMusica;
    public JLabel lbNomeUsuario;
    public JLabel lbDuracaoMusica;
    
    
    
    public JButton btBaixar;
    
    
    
    public PanelInfoMusica(){
        this.setSize(420,300);
        this.setLayout(null);
        
        lbNomeUsuario = new JLabel("Postada Por");
        lbNomeUsuario.setBounds(5,10,150,20);
        
        lbNomeMusica = new JLabel("Nome da Musica: ");
        lbNomeMusica.setBounds(5,35,500,50);
        
        lbDuracaoMusica = new JLabel("Tempo de musica");
        lbDuracaoMusica.setBounds(5,75,550,45);
        
        btBaixar = new JButton("Baixar Musica");
        btBaixar.setBounds(110,120,150,35);
        
        add(lbNomeUsuario);
        add(lbNomeMusica);
        add(lbDuracaoMusica);
        add(btBaixar);
        
    }
     public void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null, ""+mensagem);
    }
    
    
}
