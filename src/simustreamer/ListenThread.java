package simustreamer;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kerzn002
 */
public class ListenThread extends SimuStreamer implements Runnable {

    @Override
    public void run() {
        try {
            ss = new ServerSocket(5555, 5, InetAddress.getLocalHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        while (! ss.isClosed()) {
            try {
                Socket n = ss.accept();
                threadExecutor.execute(new Worker(n));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        threadExecutor.shutdown();
    }
    
    
}
