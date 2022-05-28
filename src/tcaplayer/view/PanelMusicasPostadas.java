/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
/**
 *
 * @author Lucas
 */
public class PanelMusicasPostadas extends JPanel {
    public JPanel painelAlign2;
    public JPanel painelAlign;
    public JButton btMusicas[];
    
    public PanelMusicasPostadas(int tam){
        this.setSize(400,500);
        this.setLayout(new BorderLayout());
        
        painelAlign = new JPanel();
        painelAlign.setSize(580,420);
        painelAlign2 = new JPanel();
        painelAlign2.setSize(580,420);
        painelAlign2.setBackground(new Color(181, 182, 183));

        painelAlign.setLayout(new GridLayout(tam,tam,1,1)); 
        this.btMusicas = new JButton[tam];
        this.setBackground(Color.red);
        
    }
    
}
