package org.ogenvik.futures;

/**
 * Created by erik on 2017-05-31.
 */
public class Util {

    public static void println(Object o) {
        System.out.println(Thread.currentThread().getName() + " " + o.toString());
    }
    public  static <T> T println(Throwable t) {
        System.err.println(Thread.currentThread().getName() + " " + t.toString());
        return null;
    }
}
