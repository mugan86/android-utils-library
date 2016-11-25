/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.utils;

/**
 * @author anartzmugika
 */
public class Execution {
    
    public static void sleepExecutionBySpecifiedDuration (int sg)
    {
        System.out.println("**********************************");
        System.out.println("Sleep " + sg + " seconds...");
        try{
            //do what you want to do before sleeping
            Thread.currentThread().sleep(1000*sg);//sleep for 1000 ms
            //do what you want to do after sleeptig
        }
        catch(InterruptedException ie)
        {
        //If this thread was intrrupted by nother thread
        }
    }
    
}
