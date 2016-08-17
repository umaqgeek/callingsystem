/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author End User
 */
public interface Message extends Remote {
    
    //Vector getQueueCallingSystem() throws RemoteException;
    
    void setCall(String nama) throws RemoteException;
    void setCallNo(String nama, String patientNo) throws RemoteException;
    
    void destroyCall(String nama) throws RemoteException;
}
