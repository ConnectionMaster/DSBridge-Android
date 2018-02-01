package wendu.dsbridge;

/**
 * Created by shangchun on 23/01/2018.
 */

public interface CallbackHandler {
    /**
     * this callback to trigger when javascript has successfully finish initialized bridging.
     */
    void onJsChannelReady();
}