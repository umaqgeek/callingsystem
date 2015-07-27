/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import views.MainQueue;
import views.QueueCall;
import views.VoiceOutput;

/**
 *
 * @author Administrator
 */
public class CallQueue implements Runnable {
    
    final private long seconds = 1000 * 1;

    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    Timestamp ts = new Timestamp(new java.util.Date().getTime());
                    DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    
                    QueueCall.setClearTbl();
                    int size_arr = QueueController.getName().size();
                    if (size_arr > 0) {
                        String object[][] = new String[size_arr][2];
                        for (int i = 0; i < size_arr; i++) {
                            String str = QueueController.getName().get(i);
                            String s[] = str.split("\\|");
                            String name = s[1];
                            String doctor = s[2];
                            String roomNo = s[3];
                            object[i][0] = name;
//                            object[i][1] = doctor;
                            object[i][1] = roomNo;
                        }
                        QueueCall.setData(object);
                        for (int i = 0; i < size_arr; i++) {
                            String str = QueueController.getName().get(i);
                            String s[] = str.split("\\|");
                            String name = s[1];
                            String doctor = s[2];
                            String roomNo = s[3];
                            String ayatCakap = name.toLowerCase()+" Room number " + roomNo.toLowerCase();
                            if (roomNo.equals("Pharmacy")) {
                                ayatCakap = name.toLowerCase()+", please collect your medication at pharmacy";
                            }
                            if (QueueController.getCount().get(i) > 0) {
                                VoiceOutput.getSound(ayatCakap);
                                int count = QueueController.getCount().get(i) - 1;
                                QueueController.getCount().set(i, count);
                            }
                        }
                    } else {
                        QueueCall.setClearTbl();
                    }
                    
                    //System.out.println("Sync Calling at ["+df.format(ts) +"]");
                    Thread.sleep(seconds);
                }
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Error in CallQueue: "+e.getMessage());
        } finally {
            
        }
    }
    
}
