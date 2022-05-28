/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.media.CannotRealizeException;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCMusicaDAO;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Musica;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaPrincipal;
import tcaplayer.view.PanelMeuPerfil;

/**
 *
 * @author Lucas
 */
public class ControllerJanelaPrincipal implements ActionListener,MouseListener {

    private JanelaPrincipal janela;
    private PanelMeuPerfil pnMeuPerfil;
    private JDBCUsuarioDAO usuarioDAO;
    private JDBCMusicaDAO musicaDAO;
    private ControllerPanelEditarPerfil ctrlEditarPerfil;
    private ControllerPanelAdicionarSeguidor ctrlAdicionarAmigo;
    private ControllerPanelMeuPerfil ctrlMeuPerfil;
    private ControllerMeusAmigos ctrlMeusAmigos;
    private ControllerMusicasPostadas ctrlMusicasPostadas;
    private Conexao fabrica;
    private JDBCPerfilDAO perfilDAO;
    private Perfil perfil;
   
    private Usuario u;
   
     public ControllerJanelaPrincipal(Conexao fabrica){
                this.fabrica = fabrica;
                
                
		usuarioDAO = new JDBCUsuarioDAO(fabrica);
                ctrlEditarPerfil = new ControllerPanelEditarPerfil(fabrica);
                ctrlMeuPerfil = new ControllerPanelMeuPerfil(fabrica);
                ctrlMusicasPostadas = new ControllerMusicasPostadas(fabrica);
                ctrlAdicionarAmigo = new ControllerPanelAdicionarSeguidor(fabrica);
                ctrlMeusAmigos = new ControllerMeusAmigos(fabrica);
                perfilDAO = new JDBCPerfilDAO(fabrica);
                musicaDAO = new JDBCMusicaDAO(fabrica);
    }
    
    public void selecionaArquivo(){
     
        JFileChooser fileChooser = null;
		
		
                fileChooser = new JFileChooser();
                
		
		
		FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Selecionar Arquivo de Musica(*.MP3)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".mp3");
				}
			}
		};

		
		fileChooser.setFileFilter(wavFilter);
		fileChooser.setDialogTitle("Selecionar Musica");
		fileChooser.setAcceptAllFileFilterUsed(false);
               
		int userChoice = fileChooser.showOpenDialog(janela);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			String arquivow = fileChooser.getSelectedFile().getAbsolutePath();
                        
                        File fileMusica = new File(arquivow);
                        String nomeMusica = fileChooser.getSelectedFile().getName();
                        int duracao = 5;
                    Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
                    Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
                    Format input2 = new AudioFormat(AudioFormat.MPEG);
                    Format output = new AudioFormat(AudioFormat.LINEAR);
                    PlugInManager.addPlugIn(
                            "com.sun.media.codec.audio.mp3.JavaDecoder",
                            new Format[]{input1, input2},
                            new Format[]{output},
                            PlugInManager.CODEC
                    );
                        
                    try {
                        Player mediaPlayer = Manager.createRealizedPlayer(fileMusica.toURI().toURL());

                         duracao = (int) mediaPlayer.getDuration().getSeconds(); 
                         System.out.println("A"+duracao);


                    } catch (IOException ex) {

                    } catch (NoPlayerException ex) {

                    } catch (CannotRealizeException ex) {

                    }
                Musica musica = new Musica(u,duracao,nomeMusica,fileMusica); 
                musicaDAO.insere(musica);
            }
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource() == janela.jmiAdicionarAmigo){
            ctrlAdicionarAmigo.abrir(u, perfil,janela);
        }else if(e.getSource() == janela.jmiAdicionarMusica){
            selecionaArquivo();
        }else if(e.getSource() == janela.jmiRelatorioSeguidores){
            relatorioMaisSeguidos();
        }else if(e.getSource() == janela.jmiRelatorioOuve){
            relatorioMaisOuviu();
        }else if(e.getSource() == janela.jmiRelatorioMusicas){
            relatorioMaisMusicas();
        }
       
    }
    public void abrir(Usuario u) throws Exception{
        this.u = u;
        perfil = perfilDAO.buscaPerfil(u);
        this.janela = new JanelaPrincipal(ctrlMeuPerfil.abrir(perfil,u),ctrlMeusAmigos.abrir(u, perfil),ctrlMusicasPostadas.abrir());
      //  this.pnMeuPerfil = new PanelMeuPerfil();
        this.janela.jmiAdicionarAmigo.addActionListener(this);
        this.janela.jmiAdicionarMusica.addActionListener(this);
        this.janela.jmiRelatorioSeguidores.addActionListener(this);
        this.janela.jmiRelatorioMusicas.addActionListener(this);        
        this.janela.jmiRelatorioOuve.addActionListener(this);
        this.janela.jmRelatorio.addMouseListener(this);
        this.janela.jmAdicionar.addMouseListener(this);
        this.janela.jmSair.addMouseListener(this);
        
        this.janela.jmiAdicionarAmigo.addMouseListener(this);
        this.janela.jmiAdicionarMusica.addMouseListener(this);
        this.janela.jmiRelatorioSeguidores.addMouseListener(this);
        this.janela.jmiRelatorioMusicas.addMouseListener(this);        
        this.janela.jmiRelatorioOuve.addMouseListener(this);
        this.janela.jmSair.addMouseListener(this);
        janela.setVisible(true);    
      // janela.add(pnMeuPerfil,WEST);
    }

    public void relatorioMaisSeguidos(){
        try{
            JRResultSetDataSource relatResul = new JRResultSetDataSource(usuarioDAO.relatorioMaisSeguidos());
            JasperPrint jpPrint = JasperFillManager.fillReport("report/relatorio_mais_seguidos.jasper",new HashMap(),relatResul);
            JasperViewer jv = new JasperViewer(jpPrint,false);
            jv.setVisible(true);
            jv.toFront();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void relatorioMaisMusicas(){
        try{
            JRResultSetDataSource relatResul = new JRResultSetDataSource(usuarioDAO.relatorioMaisMusicas());
            JasperPrint jpPrint = JasperFillManager.fillReport("report/relatorio_mais_musica.jasper",new HashMap(),relatResul);
            JasperViewer jv = new JasperViewer(jpPrint,false);
            jv.setVisible(true);
            jv.toFront();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void relatorioMaisOuviu(){
        try{
            JRResultSetDataSource relatResul = new JRResultSetDataSource(usuarioDAO.relatorioMaisOuviu());
            JasperPrint jpPrint = JasperFillManager.fillReport("report/relatorio_quem_mais_ouve.jasper",new HashMap(),relatResul);
            JasperViewer jv = new JasperViewer(jpPrint,false);
            jv.setVisible(true);
            jv.toFront();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == janela.jmiAdicionarAmigo || e.getSource() == janela.jmiAdicionarMusica || e.getSource() == janela.jmiRelatorioMusicas || e.getSource() == janela.jmiRelatorioMusicas || e.getSource() == janela.jmiRelatorioOuve || e.getSource() == janela.jmiRelatorioSeguidores || e.getSource() == janela.jmAdicionar ||  e.getSource() == janela.jmRelatorio ||  e.getSource() == janela.jmSair){
                janela.setCursor(Cursor.getPredefinedCursor(12));
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
         if(e.getSource() == janela.jmiAdicionarAmigo || e.getSource() == janela.jmiAdicionarMusica || e.getSource() == janela.jmiRelatorioMusicas || e.getSource() == janela.jmiRelatorioMusicas || e.getSource() == janela.jmiRelatorioOuve || e.getSource() == janela.jmiRelatorioSeguidores || e.getSource() == janela.jmAdicionar ||  e.getSource() == janela.jmRelatorio ||  e.getSource() == janela.jmSair){
              janela.setCursor(Cursor.getPredefinedCursor(0));
        }  
    }
    
}
