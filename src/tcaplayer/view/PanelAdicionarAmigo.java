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
public class PanelAdicionarAmigo extends JPanel{
    
    public JTextField tfNomeAmigo;
    public JButton btAdicionar;
    
    public PanelAdicionarAmigo(){
        tfNomeAmigo = new JTextField(20);
        btAdicionar = new JButton("Adicionar");
        tfNomeAmigo.setBounds(4,4,100,20);
        btAdicionar.setBounds(4,4,35,23);
        add(tfNomeAmigo);
        add(btAdicionar);
    }
     public void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null, ""+mensagem);
    }
    
}
