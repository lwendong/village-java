package com.village.utils;

import java.time.LocalDate;

public class NoteUtil {

    public static String getCurrentGroup(){
        return  LocalDate.now().getYear() +"-"+ LocalDate.now().getMonthValue();
    }

    public static String getCurrentNumber(){
        LocalDate now = LocalDate.now();
        return  "d" + now.getDayOfMonth();
    }
}
