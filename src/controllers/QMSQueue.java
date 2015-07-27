/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import views.MainQueue;

/**
 *
 * @author End User
 */
public class QMSQueue implements Runnable {

    final private long seconds = 1000 * 5;
    
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    Timestamp ts = new Timestamp(new java.util.Date().getTime());
                    DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    
                    MainQueue.list_Queue();
                    
                    System.out.println("Sync Calling at ["+df.format(ts) +"]");
                    Thread.sleep(seconds);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            
        }
    }
    
}
