/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class QueueController {
    
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<Integer> count = new ArrayList<Integer>();

    public static ArrayList<String> getName() {
        return name;
    }

    public static ArrayList<Integer> getCount() {
        return count;
    }
    
    public static void setCount(ArrayList<Integer> aCount) {
        count = aCount;
    }
    
    public static void startQueue() {
        try {
            Thread t = new Thread(new CallQueue());
            t.start();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error in QueueController: "+e.getMessage());
        }
    }
}
