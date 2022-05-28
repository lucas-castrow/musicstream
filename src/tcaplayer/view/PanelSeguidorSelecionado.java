/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class PanelSeguidorSelecionado extends JPanel{
    
    public JLabel lbAmigoFoto;
    public JLabel lbAmigoOnline;
    public JButton btMensagem;
    public JButton btTransmitir;
    public JButton btConectar;
    public ImageIcon imageIcon;
    public JLabel lbAmigoSobre;
    public JLabel lbNomeAmigo;
    public JButton btPararDeSeguir;
    
    public JPanel painelAlign,painelAlign2;
    
    public PanelSeguidorSelecionado(){
        this.setSize(430,300);
        
        this.setLayout(new BorderLayout());
        painelAlign = new JPanel();
        painelAlign.setLayout(null);
        
       
        imageIcon = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        lbAmigoFoto = new JLabel(imageIcon);
        lbNomeAmigo = new JLabel("Amigo");
        btMensagem = new JButton("Mensagem");
        btTransmitir = new JButton("Transmitir");
        btConectar = new JButton("Conectar");
        btPararDeSeguir = new JButton("Parar de Seguir");
        lbAmigoSobre = new JLabel("Sobre");
        lbAmigoSobre.setBounds(10,60,300,60);
        btTransmitir.setBounds(10,125,125,23);
        btConectar.setBounds(135,125,125,23);
        //btAlterarFoto.setBounds(80,80,80,90);
        btMensagem.setBounds(260,125,125,23);
        lbNomeAmigo.setBounds(70,10,100,15);
        lbAmigoFoto.setBounds(1,10,60,60);
        btPararDeSeguir.setBounds(220,10,150,20);
        painelAlign.add(lbAmigoFoto);
        painelAlign.add(lbNomeAmigo);
        painelAlign.add(lbAmigoSobre);
        painelAlign.add(btTransmitir);
        painelAlign.add(btConectar);
        painelAlign.add(btMensagem);
        painelAlign.add(btPararDeSeguir);        
        add(painelAlign,BorderLayout.CENTER);
    }
     public void mensagem(String msg){
        JOptionPane.showMessageDialog(null, ""+msg);
    }
    
    
    
    
    
    
    
    
    
    
}
