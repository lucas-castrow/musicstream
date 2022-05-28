/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class JanelaRecuperarSenha extends JPanel{
    
    public JTextField tfUsername;
    public JLabel lbUsername;
    public JButton btEnviar;
    
    public JPanel pnAlign;
    
    public JPanel pnAlign2;
    public JanelaRecuperarSenha(){
        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        
        lbUsername = new JLabel("Coloque seu login (nick)");
        
        
        pnAlign = new JPanel();
        pnAlign2 = new JPanel();
        
        pnAlign.setLayout(new GridLayout(3,1,10,10));
        tfUsername = new JTextField(20);
        btEnviar = new JButton("Enviar Senha");
        pnAlign.add(lbUsername);
        pnAlign.add(tfUsername);
        pnAlign.add(btEnviar);
        pnAlign2.add(pnAlign);
        this.add(pnAlign2,BorderLayout.CENTER);
        
        
    }
    
      public void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null, ""+mensagem);
    }
    
    
    
}
