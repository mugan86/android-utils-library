/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mugan86.models;

import java.util.ArrayList;

/**
 *
 * @author Anartz Muxika
 */
public class Jobs {
    private String department;
    private ArrayList<String> job_list;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<String> getJob_list() {
        return job_list;
    }

    public void setJob_list(ArrayList<String> job_list) {
        this.job_list = job_list;
    }
}
