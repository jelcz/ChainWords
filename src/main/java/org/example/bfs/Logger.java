package org.example.bfs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    public static void log(String string) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
        System.out.println(sdf.format(cal.getTime()) + " " + string);
    }
}
