/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.utility;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author raj.shah
 */
public class MyUtils {

    public static boolean isNullOrEmpty(String str) {
        boolean isNullOrEmpty = false;
        if (str == null || str.equals("") || str.length() == 0) {
            isNullOrEmpty = true;
        }
        return isNullOrEmpty;
    }

    public static String getCommaSeparatedStringFromList(List<String> lst) {
        String str = "";
        if (lst != null && lst.size() > 0) {
            str = String.join(",", lst);
        }
        return str;
    }

//    public static String CreateWhereClauseString(Map<String, Object> map) {
//        StringBuilder sbr = new StringBuilder();
//        if (map != null && map.size() > 0) {
//            sbr.append(" WHERE ");
//
//            for (Map.Entry<String, Object> entry : map.keySet()) {
//                Integer key = entry.getKey();
//                String value = entry.getValue();
//
//                // do stuff
//            }
//            Set<String> keySet = map.keySet();
//            Iterator itr = keySet.iterator();
//            while (itr.hasNext()) {
//                Object obj = itr.next();
//
//                if (obj instanceof String) {
//                    sbr.append()
//                } else if (obj instanceof Integer) {
//
//                }
//            }
//        }
//        return sbr.toString();
//    }
}
