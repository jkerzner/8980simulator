package simustreamer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kerzn002
 */
public class Worker extends SimuStreamer implements Runnable {

    
    private PrintWriter fout;
    private BufferedReader f_in;
    
    private Socket socket;
    
    Worker(Socket s) {
        this.socket = s;
    }
    
    @Override
    public void run() {
        System.err.println("Running");
        
        try {
            this.fout = new PrintWriter(socket.getOutputStream(), true);
            this.f_in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int counter = 0;
        int lc = 0;
        while (! socket.isClosed()) {
            try {
                Thread.sleep(1000);
                System.out.println("OK: " + lc + "." + counter);
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // always emit an event
            synchronized(fout) {
                fout.println(System.currentTimeMillis() + " 1");
            }
            
            if (counter == 5) {
                synchronized(fout) {
                    fout.println(System.currentTimeMillis() - 10000 + " 50");
                }
            }
            
            counter = ++counter % 10;
            if (lc == 0) { lc++; }
        }
    }
}
