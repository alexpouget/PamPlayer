package Mp3Player;

/**
 * Created by alex on 06/06/2015.
 */
public class PlayerListener {


    public void onFrameRead(int frameNumber) {
        System.out.println("New Frame: " + frameNumber);
    }


    public void onStart() {
        // TODO Auto-generated method stub

    }
    public void onFinish() {
    }
}
