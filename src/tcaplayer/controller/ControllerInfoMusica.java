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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCMusicaDAO;
import tcaplayer.model.Musica;
import tcaplayer.view.PanelInfoMusica;

/**
 *
 * @author Lucas
 */
public class ControllerInfoMusica implements ActionListener,MouseListener {

    private Conexao fabrica;
    private PanelInfoMusica janela;
    private JDBCMusicaDAO musicaDAO;
    private Musica musica;
    private JDialog dialog;
    
    public ControllerInfoMusica(Conexao fabrica){
        this.fabrica = fabrica;
        this.musicaDAO = new JDBCMusicaDAO(fabrica);
    }
    
    
    public void abrir(Musica musica){
        this.janela = new PanelInfoMusica();
        this.musica = musica;
        
        dialog = new JDialog(this.dialog,"Musica: "+musica.getNome(),true);
        this.janela.btBaixar.addActionListener(this);
        this.janela.btBaixar.addMouseListener(this);
        this.janela.lbNomeMusica.setText("<html><p style=\"width:322px\">Nome da Musica: "+musica.getNome()+"</p></html>");
        this.janela.lbNomeUsuario.setText("Postada por: "+musica.getUsuario().getNomeDeUsuario());
        this.janela.lbDuracaoMusica.setText("Duração da musica: "+moveDuration(musica.getDuracao()));
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }




    private String moveDuration(int duracao){
        
        int segundo = duracao % 60; 
        int minutos = duracao / 60; 
        int minuto = minutos % 60; 

        String hms = String.format ("%02d:%02d", minuto, segundo); 
        return hms;
    }

      private String salvarEm(){
        JFileChooser fileChooser = null;
		
		
                fileChooser = new JFileChooser();
		
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		fileChooser.setFileFilter(null);
		fileChooser.setDialogTitle("Selecionar Diretorio");

		int userChoice = fileChooser.showOpenDialog(janela);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			String diretorio = fileChooser.getSelectedFile().getAbsolutePath();
                        
                        
                        janela.mensagem("Serão baixadas em: "+diretorio);
                        return diretorio;
                }
    		else{
                    janela.mensagem("Você não selecionou nenhum diretorio!");
                    return null;
                }
                
                
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btBaixar){
            String diretorio = salvarEm();
            janela.setCursor(Cursor.getPredefinedCursor(3));
            musicaDAO.baixar(musica.getId(),diretorio);
            janela.setCursor(Cursor.getPredefinedCursor(0));
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
