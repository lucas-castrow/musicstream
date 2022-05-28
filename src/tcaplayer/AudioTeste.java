package tcaplayer;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.Time;
import javax.media.format.AudioFormat;

public class AudioTeste {
	public static void main(String[] args) {
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn(
			"com.sun.media.codec.audio.mp3.JavaDecoder",
			new Format[]{input1, input2},
			new Format[]{output},
			PlugInManager.CODEC
		);
		try{
			Player player = Manager.createRealizedPlayer(new MediaLocator(new File("gamestartup.mp3").toURI().toURL()));
			player.stop();
			player.setMediaTime(new Time(0.0));
                        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
                        System.out.println(""+horaFormat.format(player.getDuration()));
                        
			player.start();
			
			
			
			player.stop();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}