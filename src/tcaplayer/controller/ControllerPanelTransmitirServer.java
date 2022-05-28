/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.media.CannotRealizeException;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.Time;
import javax.media.format.AudioFormat;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCTransmissaoDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Transmissao;
import tcaplayer.view.PanelBarraPlayer;
import tcaplayer.view.PanelTransmitirServer;

/**
 *
 * @author Lucas
 */
public class ControllerPanelTransmitirServer implements ActionListener,MouseListener,Runnable,WindowListener{

   private Conexao fabrica;
    
   private ObjectOutputStream output; // output stream para o cliente
   private ObjectInputStream input; // input stream do cliente
   private ServerSocket server; // server socket
   private Socket connection; // socket para gerenciar a conexão com o cliente
   private int counter = 1; // contador de número de conexões
   
   private PanelTransmitirServer janela;
   private PanelBarraPlayer barraPlayer;
   private Player mediaPlayer;
   private Transmissao transmissao;
   private JDBCTransmissaoDAO transmissaoDAO;
   private double tempo;
  
    public ControllerPanelTransmitirServer(Conexao fabrica,Transmissao transmissao){
        this.fabrica = fabrica;
        this.transmissao = transmissao;
        this.transmissaoDAO = new JDBCTransmissaoDAO(fabrica);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btParar){
            closeConnection();
           transmissao.setFim_transmissao(System.currentTimeMillis());
           if(transmissaoDAO.insereTransmissao(transmissao)){
               System.out.println("OK");
           }else{
               System.out.println("Bosta");
           }
        }
    }
    
    public void abrir(){
        this.janela = new PanelTransmitirServer();
        this.barraPlayer = new PanelBarraPlayer();
        this.janela.add(barraPlayer,SOUTH);
        menuController();
        
        runServer();
    }
    private void menuController(){
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
        
        try
         	{
            	mediaPlayer = Manager.createRealizedPlayer(new File("gamestartup.mp3").toURI().toURL());
                    
            	Component video = mediaPlayer.getVisualComponent();
            	Component controls = mediaPlayer.getControlPanelComponent();
               
            
            	if ( video != null )
               	barraPlayer.add( video, BorderLayout.CENTER ); 
   
            	if ( controls != null )
               	barraPlayer.add( controls, BorderLayout.SOUTH ); 
   
            	mediaPlayer.setMediaTime(new Time(0.0));
               
                    
            	mediaPlayer.start(); 
   
              
             	} // end try
         	catch ( NoPlayerException noPlayerException )
         	{
            	System.err.println( "No media player found" );
        	} // end catch
         	catch ( CannotRealizeException cannotRealizeException )
   	{
            	System.err.println( "Could not realize media player" );
         	} // end catch
         	catch ( IOException iOException )
	   {
            	System.err.println( "Error reading from the source" );
         	}
    }

    
   public void runServer()
   {
      try 
      {
         server = new ServerSocket( 12345, 3 ); 

         //while ( true ) 
        // {
            try 
            {
               waitForConnection(); 
               getStreams(); 
               processConnection(); 
            } 
            catch ( EOFException eofException ) 
            {
                System.out.println("Server terminated connection");
            } 
            finally 
            {
               closeConnection(); //  fehca a coneão
               ++counter;
            } 
         //} 
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   } 

   private void waitForConnection() throws IOException
   {
       System.out.println("Waiting for Connection! ");
      connection = server.accept(); // permite o servidor aceitar conexões            
      System.out.println( "Connection " + counter + " received from: " +
         connection.getInetAddress().getHostName() );
   } 

   private void getStreams() throws IOException
   {
      output = new ObjectOutputStream( connection.getOutputStream() );
      output.flush(); 
      
      input = new ObjectInputStream( connection.getInputStream() );
      System.out.println("Fluxos estabelecidos! ");
   }

   private void processConnection() throws IOException
   {
    sendData();

   } 

   private void closeConnection() 
   {
       System.out.println("Finalizando Conexao!");

      try 
      { //fecha os fluxos e a conexão
         output.close(); 
         input.close(); 
         connection.close(); 
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   } 

   private void sendData()
   {
       double last=0;
       boolean pause = false;
       do{
          
       tempo = mediaPlayer.getMediaTime().getSeconds();
      try
      {
          
          if(tempo > last+3 || tempo < last-3){
               System.out.println("Aumentou Pra Caralho " +mediaPlayer.getMediaTime().getSeconds());
               output.writeObject(mediaPlayer.getMediaTime().getSeconds());
               
              output.flush();
              last = tempo;
              
           }
              
      } 
      catch ( IOException ioException ) 
      {
      } 
       }while(mediaPlayer.getDuration().getSeconds()>tempo);
   } 
    
    
    //INICIA A THREAD
     @Override
    public void run() {
       
        try{
           this.janela = new PanelTransmitirServer();
           this.barraPlayer = new PanelBarraPlayer();
           this.janela.add(barraPlayer,SOUTH);
           
           
           this.janela.btParar.addActionListener(this);
           this.janela.addWindowListener(this);
           menuController();
           runServer();
          
        }catch(Exception e){
           System.out.println("EXCECAO: "+e);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
          int op = janela.fechar_janela();
      if(op == 1){
          transmissao.setFim_transmissao(System.currentTimeMillis());
          transmissaoDAO.insereTransmissao(transmissao);
          mediaPlayer.close();
          closeConnection();
          janela.dispose();
      }
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
