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
 *
 * Classe de reconnaissance vocal etendu de thread utilise l'Api sphinx
 */
public class SpeechReco extends Thread{
    private ConfigurationManager cm;
    private Microphone microphone;
    private Recognizer recognizer;
    private boolean running;
    private Result result;
    private MyEvent m;

    public SpeechReco(){
        /*on definit le fichier de configuration qui contient
         * notamment le fichier de grammaire et le dictionnaire
         */
        cm = new ConfigurationManager(SpeechReco.class.getResource("helloworld.config.xml"));
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();
        
        microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            recognizer.deallocate();
            System.exit(1);
        }

        m = new MyEvent();

    }

    //réecriture de la method run de Thread
    public void run(){
        running = true;
        while(running)
        {
            result = recognizer.recognize();
            //traitement des resultats obtenue
            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
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
	                    	Main.logger.error(e1.getMessage());
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
