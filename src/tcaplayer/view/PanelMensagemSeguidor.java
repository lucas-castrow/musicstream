/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Lucas
 */
public class PanelMensagemSeguidor extends JPanel{
    
    public JLabel lbMensagens[];
    public JTextField tfMensagem;
    public JPanel pnBaixo;
    public JPanel pnCentro;
    
    public JPanel pnEnviado;
    public JPanel pnRecebido;
    
    public JButton btEnviar;
    
    
    public JPanel pnAlignMensagem[];
    public JPanel pnAlignMensagem2[];
    public JPanel pnAlignMensagem3;
    public JPanel pnAlignMensagem4;
    public JPanel pnAlignMensagem5;
    public static Color backGroundMsg = new Color(255, 133, 74);
    
    public PanelMensagemSeguidor(){
        this.setSize(453,478);
        this.setLayout(new BorderLayout());
        
        this.tfMensagem = new JTextField(60);
        btEnviar = new JButton("Enviar"); 
        this.pnBaixo = new JPanel();
        this.pnBaixo.setLayout(new GridLayout(1,2,20,20));
        this.pnBaixo.setSize(280,60);
        this.pnBaixo.add(tfMensagem);
        this.pnBaixo.add(btEnviar);
                
       
        
        
                
        
        
        
        this.pnCentro = new JPanel();
        this.pnCentro.setLayout(new BorderLayout());
        this.pnCentro.setSize(430,415);
        this.add(pnBaixo,SOUTH);
   
        
        
        
    }
    
    
    
}
