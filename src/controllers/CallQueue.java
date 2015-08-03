/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import views.MainQueue;
import views.QueueCall;

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
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    
                    QueueCall.setClearTbl();
                    if (QueueController.getName().size() > 0) {
                        String object[][] = new String[QueueController.getName().size()][2];
                        for (int i = 0; i < QueueController.getName().size(); i++) {
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
                        String dataJerit[][] = new String[QueueController.getName().size()][4];
                        for (int i = 0; i < QueueController.getName().size(); i++) {
                            String str = QueueController.getName().get(i);
                            int count = QueueController.getCount().get(i);
                            QueueController.getCount().set(i, 0);
                            String s[] = str.split("\\|");
                            String name = s[1];
                            String doctor = s[2];
                            String roomNo = s[3];
                            dataJerit[i][0] = name;
                            dataJerit[i][1] = doctor;
                            dataJerit[i][2] = roomNo;
                            dataJerit[i][3] = count+"";
                        }
                        for (int i = 0; i < dataJerit.length; i++) {
                            String name = dataJerit[i][0];
                            String doctor = dataJerit[i][1];
                            String roomNo = dataJerit[i][2];
                            int count = 0;
                            try {
                                count = Integer.parseInt(dataJerit[i][3]);
                            } catch (Exception e) {
                                
                            }
                            String ayatCakap = name.toLowerCase()+" Room number " + roomNo.toLowerCase();
                            if (roomNo.equals("Pharmacy")) {
                                ayatCakap = name.toLowerCase()+", please collect your medication at pharmacy";
                            }
                            while (count > 0) {
                                try {
//                                    VoiceOutput.getSound(ayatCakap);
                                    VoiceOutput2.speak(ayatCakap);
                                } catch (Exception et) {
                                    Date newdate = new Date();
                                    System.out.println("Time: "+df.format(newdate)+"You need an internet to google translate!!");
                                }
                                count -= 1;
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
