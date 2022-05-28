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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author Lucas
 */
public class JanelaCadastroUsuario extends JPanel{
    
    private JLabel lbNome;
    private JLabel lbEmail;
    private JLabel lbSenha;
    private JLabel lbDataNasc;
    private JLabel lbNomeDeUsuario;
    
    public JComboBox cbDia;
    public JComboBox cbMes;
    public JComboBox cbAno;
    
    public JTextField tfNome;
    public JTextField tfEmail;
    public JTextField tfSenha;
    public JTextField tfDataNasc;
    public JTextField tfNomeDeUsuario;
    public static Color backGround = new Color(236, 117, 71);
    
    
    public JButton btCadastrar;
    
    public JanelaCadastroUsuario(){
        this.setSize(900,600);
        this.setLayout(new BorderLayout());
        this.setBackground(backGround);
        
        JPanel painelAlign = new JPanel();
        painelAlign.setSize(50,50);
        JPanel painelAlign3 = new JPanel();
        painelAlign.setSize(50,50);
        
        JPanel painelAlign2 = new JPanel();
        painelAlign2.setSize(50,50);
        painelAlign.setLayout(new GridLayout(10,10,1,1)); 
        
        
        
        lbNome = new JLabel("Nome: ");
        tfNome = new JTextField(20);
        painelAlign.add(lbNome);
        painelAlign.add(tfNome);
        tfNome.setForeground(Color.BLACK);
        
        
        lbNomeDeUsuario = new JLabel("Nick: ");
        tfNomeDeUsuario = new JTextField(20);
        painelAlign.add(lbNomeDeUsuario);
        painelAlign.add(tfNomeDeUsuario);
        tfNomeDeUsuario.setForeground(Color.BLACK);
        
        
        
        lbEmail = new JLabel("Email: ");
        tfEmail = new JTextField(20);
        painelAlign.add(lbEmail);
        painelAlign.add(tfEmail);
        tfEmail.setForeground(Color.BLACK);
        
        lbSenha = new JLabel("Senha: ");
        tfSenha = new JTextField(20);
        tfSenha.setForeground(Color.BLACK);
        
        btCadastrar = new JButton("Cadastrar");
        btCadastrar.setBackground(Color.white);
        painelAlign.add(lbSenha);
        painelAlign.add(tfSenha);
        
        JPanel pnData = new JPanel();
        pnData.setBackground(backGround);
        pnData.setLayout(new GridLayout(1,4,5,5));
        cbDia = new JComboBox();
        cbAno = new JComboBox();
        cbMes = new JComboBox();
        
        
        lbDataNasc = new JLabel("Data de Nascimento: (dd/mm/aaaa)");
        pnData.add(lbDataNasc);
        pnData.add(cbDia);
        pnData.add(cbMes);
        pnData.add(cbAno);
        painelAlign.add(pnData);

        
        painelAlign.add(btCadastrar);
        painelAlign.setBackground(backGround);
        painelAlign2.add(painelAlign);
        painelAlign3.add(painelAlign2);
        painelAlign2.setBackground(backGround);
        add(painelAlign2,BorderLayout.CENTER);
        //add(btCadastrar,BorderLayout.SOUTH);
        
    }
    public void mensagem(String msg){
        JOptionPane.showMessageDialog(null, ""+msg);
    }
    
}
