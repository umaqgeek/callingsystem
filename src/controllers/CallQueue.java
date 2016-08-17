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
import maryTTS.MaryClientUser;
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
                        String object[][] = new String[QueueController.getName().size()][3];
                        for (int i = 0; i < QueueController.getName().size(); i++) {
                            String str = QueueController.getName().get(i);
                            String s[] = str.split("\\|");
                            String name = s[1];
                            String doctor = s[2];
                            String roomNo = s[3];
                            String queueName = s[4];
                            String queueNo = s[5];
                            object[i][0] = name;
//                            object[i][1] = doctor;
//                            object[i][1] = roomNo;
                            object[i][1] = queueNo;
                            object[i][2] = queueName;
                        }
                        QueueCall.setData(object);
                        String dataJerit[][] = new String[QueueController.getName().size()][6];
                        for (int i = 0; i < QueueController.getName().size(); i++) {
                            String str = QueueController.getName().get(i);
                            int count = QueueController.getCount().get(i);
                            QueueController.getCount().set(i, 0);
                            String s[] = str.split("\\|");
                            String name = s[1];
                            String doctor = s[2];
                            String roomNo = s[3];
                            String queueName = s[4];
                            String queueNo = s[5];
                            dataJerit[i][0] = name;
                            dataJerit[i][1] = doctor;
                            dataJerit[i][2] = roomNo;
                            dataJerit[i][3] = count+"";
                            dataJerit[i][4] = queueName;
                            dataJerit[i][5] = queueNo;
                        }
                        for (int i = 0; i < dataJerit.length; i++) {
                            String name = dataJerit[i][0];
                            String doctor = dataJerit[i][1];
                            String roomNo = dataJerit[i][2];
                            String queueName = dataJerit[i][4];
                            String queueNo = dataJerit[i][5];
                            int count = 0;
                            try {
                                count = Integer.parseInt(dataJerit[i][3]);
                            } catch (Exception e) {
                                
                            }
                            
                            // read queue number one by one
                            String pecahNo[] = queueNo.split("");
                            String queueNo2 = "";
                            for (int j = 0; j < pecahNo.length; j++) {
                                queueNo2 += pecahNo[j] + ",";
                            }
                            
//                            String ayatCakap = name.toLowerCase()+" Room number " + roomNo.toLowerCase();
                            String ayatCakap = "Number " + queueNo2.toLowerCase()+" Queue " + queueName.toLowerCase();
                            if (roomNo.equals("Pharmacy")) {
                                ayatCakap = name.toLowerCase()+", pharmacy";
                            }
                            while (count > 0) {
                                try {
//                                    VoiceOutput.getSound(ayatCakap);
                                    VoiceOutput2.speak(ayatCakap);
//                                    MaryClientUser.speak(QueueCall.cbxServer.getSelectedItem().toString(), QueueCall.portCall, ayatCakap);
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
