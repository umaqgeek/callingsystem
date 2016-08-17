/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oms.rmi.server;

import controllers.QueueController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import views.QueueCall;

/**
 *
 * @author End User
 */
public class MessageImplementation extends UnicastRemoteObject implements Message {    
    
    public MessageImplementation() throws RemoteException {
        
    }
    
    public void setCall(String str_pdi) throws RemoteException {
        QueueController.getName().add(str_pdi);
        QueueController.getCount().add(3);
    }
    
    public void setCallNo(String str_pdi, String patientNo) throws RemoteException {
        QueueController.getName().add(str_pdi);
        QueueController.getCount().add(3);
    }

    public void destroyCall(String pmino) throws RemoteException {
        int arr_size = QueueController.getName().size();
        int arr_index = -1;
        for (int i = 0; i < arr_size; i++) {
            if (QueueController.getName().get(i).contains(pmino)) {
                arr_index = i;
                break;
            }
        }
        QueueController.getName().remove(arr_index);
        QueueController.getCount().remove(arr_index);
    }
}
