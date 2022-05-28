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
import java.net.InetAddress;
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
import tcaplayer.view.PanelBarraPlayer;
import tcaplayer.view.PanelTransmitirCliente;
import tcaplayer.view.PanelTransmitirServer;

/**
 *
 * @author Lucas
 */
public class ControllerPanelTransmitirCliente implements ActionListener,MouseListener,Runnable,WindowListener {

   private Conexao fabrica;
    
   private ObjectOutputStream output; 
   private ObjectInputStream input; 
   private String chatServer = "127.0.0.1"; 
   private Socket client; 
   private double message ; 
   private double tempo;
   private Player mediaPlayer;
   
  
    
   private PanelBarraPlayer barraPlayer;
       
   private PanelTransmitirCliente janela;
    public ControllerPanelTransmitirCliente(Conexao fabrica){
        this.fabrica = fabrica;
    }
    
     @Override
    public void run() {
        try{
            this.janela = new PanelTransmitirCliente();
            this.barraPlayer = new PanelBarraPlayer();
            this.janela.add(barraPlayer,BorderLayout.SOUTH);
           
            this.janela.btParar.addActionListener(this);
            this.janela.addWindowListener(this);
            menuController();
            runClient();
            
        }catch(Exception exception){
           closeConnection();
        }
        
    
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
          if(e.getSource() == janela.btParar){
              closeConnection();
          }
    }
    
      // conecta ao servidor e processa as mensagens recebidas
   public void runClient() 
   {
      try // conecta ao servidor, cria os objetos de fluxos e processa as mensagens
      {
         connectToServer(); // cria m socket para estabelecer a conexão
         getStreams(); // cria os objetos de fluxo
         processConnection(); // processa a conexão
      } 
      catch ( EOFException eofException ) 
      {
         System.out.println( "\nClient terminated connection" );
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
      finally 
      {
         closeConnection(); // fecha a coneão
      } 
   } 

   // conecta ao servidor
   private void connectToServer() throws IOException
   {      
      System.out.println( "Tentando conexão...\n" );

      // cria um socket para estabelecer a conexão com servidor
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

      // mostra a informação de conexão
      System.out.println( "Conectado a: " + 
         client.getInetAddress().getHostName() );
   } 

   // cria os objetos de fluxo
   private void getStreams() throws IOException
   {
      
      output = new ObjectOutputStream( client.getOutputStream() );      
      output.flush(); 

      
      input = new ObjectInputStream( client.getInputStream() );

      System.out.println( "\nFluxos estabelecidos!\n" );
   } 

   // processa a conexão com servidor
   private void processConnection() throws IOException
   {
       double last = 0;
       int w = 1;
     do{
         
         try // le uma mensagem e mostra na tela
         {
             message = (double) input.readObject(); 
             if(w == 1){
                 last = message;
             }
             
            if(message> last+3 || message < last - 3){
                System.out.println("---"+message);
                Time tw = new Time(message);
                mediaPlayer.setMediaTime(tw);
                last = message;
            }
             
           //System.out.println("ESTOU:"+message);
         } 
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            System.out.println( "\nObjeto desconhecido!!!" );
         } 
         w++;
     }while(mediaPlayer.getDuration().getSeconds()>tempo);
   } 

   // fecha os fluxos e a conexão
   private void closeConnection() 
   {
     System.out.println( "\nFechando conexão..." );
     // enterField.setEditable( false ); 

      try 
      {
         output.close(); 
         input.close(); 
         client.close(); 
      }
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   } 

   // envia uma mensagem ao servidor
   private void sendData(Time message )
   {
//      try 
//      {
//      } 
//      catch ( IOException ioException )
//      {
//      } 
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
            	// create a player to play the media specified in the URL
            	mediaPlayer = Manager.createRealizedPlayer(new File("gamestartup.mp3").toURI().toURL());
                    
            	// get the components for the video and the playback controls
            	Component video = mediaPlayer.getVisualComponent();
            	Component controls = mediaPlayer.getControlPanelComponent();
               
            
            	if ( video != null )
               	barraPlayer.add( video, BorderLayout.CENTER ); // add video component
   
            	if ( controls != null )
               	barraPlayer.add( controls, BorderLayout.SOUTH ); // add controls
   
            //	mediaPlayer.setMediaTime(new Time(0.0));
               
                    
            	mediaPlayer.start(); // start playing the media clip
              // while(true){
                
                   
                 
                
              
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
