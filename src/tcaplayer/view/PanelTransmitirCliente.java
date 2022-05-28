/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lucas
 */
public class PanelTransmitirCliente extends JFrame {
    public JLabel lbNomeDaMusica;
    private JPanel pnMusica;
    private JPanel pnOutro;
    public JButton btParar;
    
    public PanelTransmitirCliente(){
        super("Cliente Transmissão");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        
        pnMusica = new JPanel();
        pnOutro = new JPanel();
        pnMusica.setLayout(new GridLayout(2,1,30,30));
        lbNomeDaMusica = new JLabel("Nome da Musica");
        pnMusica.add(lbNomeDaMusica);
        
        btParar = new JButton("Parar Transmissão");
        pnOutro.add(btParar);
        pnMusica.add(pnOutro);
        
        this.add(pnMusica,BorderLayout.CENTER);
        
    }
     public int fechar_janela(){
          if (JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja parar a Stream?")==JOptionPane.OK_OPTION){
			return 1;
                        
          }
          return -1;
    }
}
