/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import tcaplayer.model.Usuario;

/**
 *
 * @author Lucas
 */
public class PanelMeuPerfil extends JPanel {
    
    public JLabel lbFoto;
    public JLabel lbNomeUser;
    public JLabel lbStatus;
    public JLabel lbSobre;
    public JLabel lbCidade;
    public JButton btEditarPerfil;
    
    private Usuario user;
    
    public PanelMeuPerfil(){
       this.setSize(200,160);
       this.setBackground(new Color(195,195,195));
       this.setLayout(null);
       ImageIcon imageIcon = new ImageIcon(new ImageIcon("icon2.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
       lbFoto = new JLabel(imageIcon);

       lbFoto.setBounds(2,2,50,50);
       lbNomeUser = new JLabel("Unknown");
       lbNomeUser.setBounds(60,10,222,10);
       lbNomeUser.setBackground(Color.red);
       
       lbStatus = new JLabel("Online");
       Font font = null;
       font = new Font("Monospaced",Font.PLAIN,13);
                
       lbStatus.setFont(font);
       lbStatus.setForeground(new Color(27, 132, 27));

       lbStatus.setBounds(60,30,222,30);
       
       lbSobre = new JLabel("<html><p style=\"width:158px\">"+"O que o usuario desejar colocar sobre ele aparecera aqui"+ "</p></html>");
       lbSobre.setBounds(4,60,400,60);
       
       lbCidade = new JLabel("Paranaguá");
       lbCidade.setBounds(102,135,85,25);
       btEditarPerfil = new JButton("Editar");
       btEditarPerfil.setToolTipText("Editar meu perfil");
       this.btEditarPerfil.setBackground(new Color(127,127,127));
       btEditarPerfil.setBounds(2, 135, 95,25);
       
       
       add(lbFoto);
       add(lbNomeUser);
       add(lbStatus);
       add(lbSobre);
       add(lbCidade);
       add(btEditarPerfil);
         
       
    }
    
}
