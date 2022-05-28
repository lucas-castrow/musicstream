/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCMensagemDAO;
import tcaplayer.model.Mensagem;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaPrincipal;
import tcaplayer.view.PanelMensagemSeguidor;

import tcaplayer.view.PanelMeusAmigos;

/**
 *
 * @author Lucas
 */
public class ControllerPanelMensagemSeguidor implements ActionListener,MouseListener,Runnable,WindowListener{

    private Conexao fabrica;
    private Usuario meuUsuario;
    private Perfil meuPerfil;
    private Perfil perfilAmigo;
    private JDBCMensagemDAO mensagemDAO;
    private int tamanho;
    private PanelMensagemSeguidor janela;
    private ArrayList<Mensagem> minhasMensagens;
    private JDialog dialog;
    private Mensagem msg;
    private boolean flag = true;
   

    public ControllerPanelMensagemSeguidor(Conexao fabrica,Usuario meuUsuario,Perfil meuPerfil,Perfil perfilAmigo){
        this.fabrica = fabrica;
        this.mensagemDAO = new JDBCMensagemDAO(fabrica);
        this.meuUsuario = meuUsuario;
        this.perfilAmigo = perfilAmigo;
        this.meuPerfil = meuPerfil;
    }
    
    
      
    public void abrir(){
       
        msg = new Mensagem(meuUsuario,perfilAmigo.getUsuario());
        
        minhasMensagens = mensagemDAO.buscaMensagens(msg);
        
        tamanho = minhasMensagens.size();
        
        
        
        System.out.println(""+tamanho);
        if(tamanho > 0){
            this.janela = new PanelMensagemSeguidor();
            adicionaMensagens();
        }else{
            this.janela = new PanelMensagemSeguidor();
            semMensagens();
            
        }
        this.janela.tfMensagem.addActionListener(this);
        this.janela.btEnviar.addActionListener(this);
        this.janela.btEnviar.addMouseListener(this);
        dialog = new JDialog();
        dialog.setTitle("Mensagem para "+perfilAmigo.getUsuario().getNomeDeUsuario());
        dialog.setVisible(true);
        dialog.setContentPane(this.janela);
        dialog.setSize(453,478);
        dialog.setLocationRelativeTo(null);
       
       
    }
    
    private void semMensagens(){
             
        janela.lbMensagens = new JLabel[tamanho];
        janela.pnAlignMensagem = new JPanel[tamanho];
        janela.pnAlignMensagem3 = new JPanel();
        janela.pnAlignMensagem4 = new JPanel();
        janela.pnAlignMensagem5 = new JPanel();
        janela.pnAlignMensagem3.setLayout(new GridLayout(tamanho,1,5,5));
        //pnAlignMensagem4.setLayout(new GridLayout(tam,1,5,5));
        //pnAlignMensagem5.setLayout(new GridLayout(tam,1,5,5));
        janela.pnAlignMensagem2 = new JPanel[tamanho];
         for(int x= 0;x<1;x++){
           
            janela.lbMensagens[x] = new JLabel("<html><p style=\"width:282px\">"+"Nenhuma mensagem de "+perfilAmigo.getUsuario().getNomeDeUsuario()+"!     Envie Agora.</p></html>");
            janela.pnAlignMensagem[x] = new JPanel();
            janela.pnAlignMensagem2[x] = new JPanel();
            janela.pnAlignMensagem[x].add(janela.lbMensagens[x]);
            
            java.awt.Color backGroundMsg = new java.awt.Color(166,166,166);//Cor laranja, RECEBIDO
            janela.pnAlignMensagem[x].setBorder(javax.swing.BorderFactory.createTitledBorder(""));
          
            janela.pnAlignMensagem[x].setBackground(backGroundMsg);
   
            janela.pnAlignMensagem[x].setSize(1,1);
            
            java.awt.Color side = new java.awt.Color(227, 228, 229);
               
            
            janela.pnAlignMensagem2[x].add(janela.pnAlignMensagem[x]);
            janela.pnAlignMensagem2[x].setBackground(new Color(225,226,227));
            janela.pnAlignMensagem2[x].setSize(1,1);
       
            janela.pnAlignMensagem3.add(janela.pnAlignMensagem2[x]);
            janela.pnAlignMensagem3.setBackground(Color.lightGray);
           
            janela.pnAlignMensagem3.setSize(1,1);
               
            janela.pnAlignMensagem4.add(janela.pnAlignMensagem3);
            janela.pnAlignMensagem4.setBackground(Color.gray);
            janela.pnAlignMensagem4.setSize(1,1);
               
            janela.pnAlignMensagem5.add(janela.pnAlignMensagem4);
            janela.pnAlignMensagem5.setBackground(side);
            janela.pnAlignMensagem5.setSize(1,1);

           
        }
         
        
        JScrollPane scrollPane = new JScrollPane(janela.pnAlignMensagem5);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setValue(0);

        janela.pnCentro.add(scrollPane,CENTER);
        janela.pnCentro.setBackground(Color.yellow);
        janela.add(janela.pnCentro,BorderLayout.CENTER);
         
    }
    
    private void adicionaMensagens(){
            //janela.removeAll();
        
        janela.lbMensagens = new JLabel[tamanho];
        janela.pnAlignMensagem = new JPanel[tamanho];
        janela.pnAlignMensagem3 = new JPanel();
        janela.pnAlignMensagem4 = new JPanel();
        janela.pnAlignMensagem5 = new JPanel();
        janela.pnAlignMensagem3.removeAll();
        janela.pnAlignMensagem3.setLayout(new GridLayout(tamanho,1,5,5));
        //pnAlignMensagem4.setLayout(new GridLayout(tam,1,5,5));
        //pnAlignMensagem5.setLayout(new GridLayout(tam,1,5,5));
        janela.pnAlignMensagem2 = new JPanel[tamanho];
        for(int x= 0;x<tamanho;x++){
            
            janela.lbMensagens[x] = new JLabel("<html><p style=\"width:282px\">"+minhasMensagens.get(x).getMensagem()+"</p></html>");
            janela.pnAlignMensagem[x] = new JPanel();
            janela.pnAlignMensagem2[x] = new JPanel();
            janela.pnAlignMensagem[x].add(janela.lbMensagens[x]);
            
         
            if(minhasMensagens.get(x).getRemetente().getId() == meuUsuario.getId()){
                java.awt.Color backGroundMsg = new java.awt.Color(63,234,186);//Cor verde, ENVIADO
                janela.pnAlignMensagem[x].setBorder(javax.swing.BorderFactory.createTitledBorder("Você"));
          
                janela.pnAlignMensagem[x].setBackground(backGroundMsg);
            }else{
                java.awt.Color backGroundMsg = new java.awt.Color(255,133,74);//Cor laranja, RECEBIDO
                janela.pnAlignMensagem[x].setBorder(javax.swing.BorderFactory.createTitledBorder(""+minhasMensagens.get(x).getRemetente().getNome()));
          
                janela.pnAlignMensagem[x].setBackground(backGroundMsg);
            }
          
            janela.pnAlignMensagem[x].setSize(1,1);          
                
            janela.pnAlignMensagem[x].setToolTipText("Enviado: "+minhasMensagens.get(x).getData()+" às "+minhasMensagens.get(x).getHora());

            java.awt.Color side = new java.awt.Color(227, 228, 229);

            janela.pnAlignMensagem2[x].add(janela.pnAlignMensagem[x]);
            janela.pnAlignMensagem2[x].setBackground(new Color(225,226,227));
            janela.pnAlignMensagem2[x].setSize(1,1);
       
            janela.pnAlignMensagem3.add(janela.pnAlignMensagem2[x]);
            janela.pnAlignMensagem3.setBackground(Color.lightGray);
           
            janela.pnAlignMensagem3.setSize(1,1);
               
            janela.pnAlignMensagem4.add(janela.pnAlignMensagem3);
            janela.pnAlignMensagem4.setBackground(Color.gray);
            janela.pnAlignMensagem4.setSize(1,1);
               
            janela.pnAlignMensagem5.add(janela.pnAlignMensagem4);
            janela.pnAlignMensagem5.setBackground(side);
            janela.pnAlignMensagem5.setSize(1,1);
            
              
        }
        
        JScrollPane scrollPane = new JScrollPane(janela.pnAlignMensagem5);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setValue(0);
        janela.pnCentro.add(scrollPane,CENTER);
        janela.pnCentro.setBackground(Color.BLUE);
        janela.add(janela.pnCentro,BorderLayout.CENTER);
     
    }
    

    
    @Override
    public void actionPerformed(ActionEvent e) {
                if(e.getSource()== janela.btEnviar){
                        enviandoMensagem(janela.tfMensagem.getText());
                }
    }
    private void enviandoMensagem(String mensagemTexto){
        Mensagem envia = new Mensagem(meuUsuario,perfilAmigo.getUsuario(),mensagemTexto);
        mensagemDAO.enviaMensagem(envia);
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

    @Override
    public void run() {
        int w = 1;
             
          while(flag){
              
              
            try{
                if(w==1)
                {
                    abrir();
                    w++;
                }
                msg = new Mensagem(meuUsuario,perfilAmigo.getUsuario());
                
                minhasMensagens = mensagemDAO.buscaMensagens(msg);
        
             //   tamanho = minhasMensagens.size();
                
                if(tamanho < minhasMensagens.size()){
                    //janela.removeAll();
                    tamanho = minhasMensagens.size();
               
                    adicionaMensagens();
                    System.out.println("ata");
                }else{
                    Thread.sleep(1000);
                }
                
                
            }catch(Exception e){
                System.out.println(""+e);
            }
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("FECHO");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
