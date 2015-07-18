package speechReco;


import main.Main;
import javafx.event.ActionEvent;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import graphique.MyWindow;
import graphique.evenement.MyEvent;

/**
 * Created by alex on 26/06/2015.
 */
public class SpeechReco extends Thread{
    private ConfigurationManager cm;
    private Microphone microphone;
    private Recognizer recognizer;
    private boolean running;
    private Result result;
    private MyEvent m;

    public SpeechReco(){
        cm = new ConfigurationManager(SpeechReco.class.getResource("helloworld.config.xml"));
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
        
        microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("probleme micro !!");
            recognizer.deallocate();
            System.exit(1);
        }

        m = new MyEvent();

    }

    public void run(){
        running = true;
        while(running)
        {
            System.out.println("quel commande ?\n");

            result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("commande : " + resultText + '\n');
                if(resultText.compareToIgnoreCase("play")==0) {
                	if(m.player!=null){
                		
                	}else{
	                	if(Main.fileName==null){
	                		Main.fileName = MyWindow.listMusic.get(0).getPath();
	                	}
	                	m.startPlayer();
                	}
                }
                if(resultText.compareToIgnoreCase("stop")==0) {
                	if (m.player == null) {
                		
                    }else{
	                    m.player.stop();
	                    m.player = null;
	                    MyWindow.play.setText("play");
	                    try {
	                        Thread.sleep(100);
	                        MyWindow.jSlider.setValue(0);
	                        MyWindow.infoMusic.setText("");
	                    } catch (InterruptedException e1) {
	                        e1.printStackTrace();
	                    }
                    }
                }
            } else {
                System.out.println("erreur result vide\n");
            }
        }
    }
}
