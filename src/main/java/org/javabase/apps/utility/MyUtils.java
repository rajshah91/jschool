/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.utility;

import java.util.List;

/**
 *
 * @author raj.shah
 */
public class MyUtils {
 
    public static boolean isNullOrEmpty(String str){
        boolean isNullOrEmpty=false;
        if(str == null || str.equals("") || str.length()==0){
            isNullOrEmpty=true;
        }
        return isNullOrEmpty;
    }
    
    public static String getCommaSeparatedStringFromList(List<String> lst){
        String str="";
        if(lst != null && lst.size()>0){
            str=String.join(",", lst);
        }
        return str;
    }
}
