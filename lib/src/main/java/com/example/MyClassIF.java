/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example;

/**************************************************************************************************
 * Created by anartzmugika on 24/11/16.
 * Example to create a java library from Android Studio
 **************************************************************************************************/

public interface MyClassIF {
    /**
     * @return only print "hello!!" message
     */
    String sayHello();

    /**@param name String to show with personalize hello!!
     * @return print hello with parameter (name)
     */
    String sayHello(String name);
}
