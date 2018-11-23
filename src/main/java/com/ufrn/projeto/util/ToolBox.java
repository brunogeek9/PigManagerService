/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufrn.projeto.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author jamelli
 */
public class ToolBox {
    public static Date currentDate(){
        TimeZone timeZone = TimeZone.getTimeZone("America/Recife");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(timeZone);
        cal.setTime(data);
        return cal.getTime();
    }
}
