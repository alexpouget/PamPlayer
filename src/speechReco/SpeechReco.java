package speechReco;


import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 * Created by alex on 26/06/2015.
 */
public class SpeechReco extends Thread{
    ConfigurationManager cm;
    Microphone microphone;
    Recognizer recognizer;
    boolean running;
    Result result;

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
            } else {
                System.out.println("erreur result vide\n");
            }
        }
    }
}
