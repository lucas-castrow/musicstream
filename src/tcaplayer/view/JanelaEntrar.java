/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

/**
 *
 * @author Lucas
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lucas
 */
public class JanelaEntrar extends JPanel{
    
    public JTextField tfEmail;
    public JPasswordField pfSenha;
    private JLabel lbEmail;
    private JLabel lbSenha;
    public JCheckBox cbManterConectado;
    public JButton btRecuperarSenha;
    
    public JButton btEntrar;
    public JButton btCadastrar;
    public static Color backGround = new Color(236, 117, 71);
    String a = "64,128,191";
    
    public JanelaEntrar(){
        this.setSize(700,600);
        this.setLayout(null);
     
        this.setBackground(backGround);
        
        JPanel painelAlign = new JPanel();
        painelAlign.setSize(20,20);
        
        JPanel painelAlignCheck = new JPanel();
        painelAlign.setSize(20,20);
        painelAlignCheck.setBounds(100,241,400,60);
        
        JPanel painelAlign2 = new JPanel();
        painelAlign2.setSize(20,20);
        painelAlign.setLayout(new GridLayout(6,5,1,1));
        
        JPanel painelButtons = new JPanel();
        painelButtons.setSize(20,20);
        painelButtons.setLayout(new GridLayout(1,2,5,5));
        painelAlign2.setBounds(150,140,400,110);
        JPanel painelAlignButtons = new JPanel();
        painelAlignButtons.setSize(20,20);
        painelAlignButtons.setBounds(150,267,400,60);
        
        lbEmail = new JLabel("Email");
        lbSenha = new JLabel("Senha");
        tfEmail = new JTextField(18);
        pfSenha = new JPasswordField(18);
         
        this.cbManterConectado = new JCheckBox("Lembrar dados");
        this.cbManterConectado.setBackground(backGround);
        
        pfSenha.setForeground(Color.BLACK);
        tfEmail.setForeground(Color.BLACK);
        tfEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
        
        painelAlign.add(lbEmail);
        painelAlign.add(tfEmail);
        painelAlign.add(lbSenha);
        
        painelAlign.add(pfSenha);
       
        painelAlignCheck.add(this.cbManterConectado);
        
        
        btEntrar = new JButton("Entrar");
        btCadastrar = new JButton("Me cadastrar");
        btRecuperarSenha = new JButton("Esqueci minha senha");
        btRecuperarSenha.setBounds(250,327,200,25);
        btCadastrar.setBackground(Color.WHITE);
        btEntrar.setBackground(Color.WHITE);
        painelButtons.add(btEntrar);
           
        painelButtons.add(btCadastrar);
        
        painelAlignButtons.add(painelButtons);
        painelAlign2.add(painelAlign);
        painelAlign2.setBackground(backGround);
        painelAlignCheck.setBackground(backGround);
        painelAlign.setBackground(backGround);
        painelButtons.setBackground(backGround);
        painelAlignButtons.setBackground(backGround);
        
        
        
        add(painelAlignButtons);
        add(painelAlignCheck);
        add(painelAlign2);
        add(btRecuperarSenha);
        
    }
       
    public int fechar_janela(){
          if (JOptionPane.showConfirmDialog(null,"Deseja fechar o Aplicativo?")==JOptionPane.OK_OPTION){
			return 1;
                        
          }
          return -1;
    }
    
    public void mensagem(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
