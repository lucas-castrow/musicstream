/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
/**
 *
 * @author Lucas
 */
public class PanelTransmitirServer extends JFrame {
    
    public JLabel lbNomeDaMusica;
    private JPanel pnMusica;
    public JButton btParar;
    public JPanel pnOutro;
    
    public PanelTransmitirServer(){
        super("Servidor de Transmissão");
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
        
        this.add(pnMusica,BorderLayout.NORTH);
   
        
    }
    
    public int fechar_janela(){
          if (JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja parar a Stream?")==JOptionPane.OK_OPTION){
			return 1;
                        
          }
          return -1;
    }
    
}
