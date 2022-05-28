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
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import tcaplayer.model.Usuario;

/**
 *
 * @author Lucas
 */
public class JanelaPrincipal extends JFrame{
    
    private JLabel lbUsuario;
    private JLabel lbSenha;
    private JLabel lbEmail;
    
    public JMenuBar jmbPrincipal;
    public JMenu jmAdicionar,jmRelatorio,jmSair;
    public JMenuItem jmiAdicionarAmigo,jmiAdicionarMusica;
    public JMenuItem jmiRelatorioSeguidores,jmiRelatorioMusicas, jmiRelatorioOuve;
   
     
    public JanelaPrincipal(PanelMeuPerfil pnMeuPerfil,PanelMeusAmigos pnMeusAmigos, PanelMusicasPostadas pnMusicasPostadas){
        //this.add(new PanelBarraPlayer(new File("gamestartup.mp3").toURI().toURL()));
            
        this.setSize(700,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 255, 102));
        
        this.jmbPrincipal = new JMenuBar();
        
     
  
        this.jmSair = new JMenu("Sair");
        this.jmAdicionar = new JMenu("Adicionar");
        this.jmiAdicionarAmigo = new JMenuItem("Seguir um usuario");
        this.jmiAdicionarMusica = new JMenuItem("Postar Musica");
        this.jmAdicionar.add(jmiAdicionarAmigo);
        this.jmAdicionar.add(jmiAdicionarMusica);
        
        this.jmbPrincipal.add(this.jmAdicionar);
        
        this.jmRelatorio = new JMenu("Relatorios");
        this.jmiRelatorioSeguidores = new JMenuItem("Quem tem mais seguidores");
        this.jmiRelatorioMusicas = new JMenuItem("Quem postou mais musicas");
        this.jmiRelatorioOuve = new JMenuItem("Quem ouviu mais");
        this.jmRelatorio.add(jmiRelatorioSeguidores);
        this.jmRelatorio.add(jmiRelatorioMusicas);
        this.jmRelatorio.add(jmiRelatorioOuve);
        this.jmbPrincipal.add(this.jmRelatorio);
       //this.add(new PanelBarraPlayer(new File("gamestartup.mp3").toURI().toURL()),SOUTH); 
        this.add(pnMeuPerfil,WEST);
        this.add(pnMeusAmigos,WEST);
        this.add(pnMusicasPostadas,EAST);
        this.jmbPrincipal.add(jmSair);
        this.setJMenuBar(this.jmbPrincipal);
        
        
        this.setLocationRelativeTo(null);
        
        
    }
    public void mensagem(String mensagem){
        JOptionPane.showMessageDialog(null, ""+mensagem);
    }
    
}

