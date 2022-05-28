/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class PanelEditarMeuPerfil extends JPanel{
    
    public JTextField tfSobre;
    public JLabel lbFoto;
    public JButton btAlterarFoto;
    public JButton btSalvar;
    public ImageIcon imageIcon;
    private JLabel lbSobre;
    private JLabel lbEstado;
    private JLabel lbCidade;
    private JLabel lbPais;
    public JTextField tfCidade;
    public JComboBox cbEstados;
    public JComboBox cbPais;
    public JComboBox cbCidades;
    
    public JPanel painelAlign,painelAlign2;
    
    public PanelEditarMeuPerfil(){
        this.setSize(300,400);
        
        this.setLayout(new BorderLayout());
        painelAlign = new JPanel();
        painelAlign.setLayout(null);
        
       
        imageIcon = new ImageIcon(new ImageIcon("icon.jpg").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        lbFoto = new JLabel(imageIcon);
        tfSobre = new JTextField(50);
        cbPais = new JComboBox();
        cbEstados = new JComboBox();
        cbCidades = new JComboBox();
       this.lbPais = new JLabel("Pais");
       this.lbCidade = new JLabel("Cidade");
       this.lbEstado = new JLabel("Estado");
       lbPais.setBounds(10,200,200,20);
       cbPais.setBounds(10,225,200,20);
       lbEstado.setBounds(10,245,200,20);
       cbEstados.setBounds(10,275,200,20);
       lbCidade.setBounds(10,295,150,20);
       cbCidades.setBounds(10,315,150,20);
        btAlterarFoto = new JButton("Alterar");
        btSalvar = new JButton("Salvar");
        lbFoto.setBounds(93,10,80,80);
        lbSobre = new JLabel("Sobre");
        lbSobre.setBounds(10,116,100,30);
        btAlterarFoto.setBounds(90,95,90,23);
        //btAlterarFoto.setBounds(80,80,80,90);
        tfSobre.setBounds(10,140,250,50);
        btSalvar.setBounds(93,335,90,23);
        painelAlign.add(lbFoto);
       painelAlign.add(btAlterarFoto);
       painelAlign.add(lbSobre);
       painelAlign.add(tfSobre);
       
       painelAlign.add(lbPais);
       painelAlign.add(cbPais);
       painelAlign.add(lbEstado);
       painelAlign.add(cbEstados);
       painelAlign.add(lbCidade);
       painelAlign.add(cbCidades);
       painelAlign.add(btSalvar);
       add(painelAlign,BorderLayout.CENTER);
    }
     public void mensagem(String msg){
        JOptionPane.showMessageDialog(null, ""+msg);
    }
    
}
