package Mp3Player;

import Graphique.MyEvent;
import Graphique.MyWindow;

/**
 * Created by alex on 08/06/2015.
 */
public class PlayerListener {

    public void start(int frame){
        if (MyEvent.maxLen != 0) {
            MyWindow.jSlider.setValue(100 * frame / MyEvent.maxLen);
        }
    }
}
