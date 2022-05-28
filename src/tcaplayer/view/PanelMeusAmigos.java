/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class PanelMeusAmigos extends JPanel{
     public JPanel painelAlign2;
     public JPanel painelAlign;
     public JButton btAmigos[];
     public JLabel lbFotoAmigo;
     public JLabel lbMensagem;
     public JPanel painelAlign3;
    
    public PanelMeusAmigos(int tam){
        this.setSize(300,300);
        this.setLayout(new BorderLayout());
        painelAlign = new JPanel();
        painelAlign.setSize(250,250);
        painelAlign2 = new JPanel();
        painelAlign3 = new JPanel();
        painelAlign2.setSize(250,250);
        
      //  painelAlign2.setLayout(new GridLayout(tam,tam,1,1));
        painelAlign.setLayout(new GridLayout(tam,tam,10,3));
         
      //  painelAlign2.setLayout(new GridLayout(tam,tam,1,1));
        painelAlign3.setLayout(new GridLayout(tam,tam,10,3));
        this.btAmigos = new JButton[tam];
        
        
        
        
    }
}
