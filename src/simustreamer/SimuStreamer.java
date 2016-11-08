package simustreamer;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author kerzn002
 */
public class SimuStreamer {
    
    static ServerSocket ss = null;
    protected ExecutorService threadExecutor = Executors.newFixedThreadPool(5);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListenThread listenThread = new ListenThread();
        
        (new Thread(listenThread, "ListenThread")).start();
    }
    
}
