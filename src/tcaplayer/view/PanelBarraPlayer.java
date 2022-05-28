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

// Fig. 21.6: MediaPanel.java
    	// A JPanel the plays media from a URL
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import javax.media.CannotRealizeException;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.Time;
import javax.media.format.AudioFormat;
import javax.swing.JPanel;
   
public class PanelBarraPlayer extends JPanel
   	{
      	public PanelBarraPlayer()
      	{
         	setLayout( new BorderLayout() ); // use a BorderLayout
   
         	// Use lightweight components for Swing compatibility
   
          // end catch
	      } // end MediaPanel constructor
   	} // end class MediaPanel
