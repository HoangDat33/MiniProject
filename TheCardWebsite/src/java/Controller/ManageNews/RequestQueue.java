
package Controller.ManageNews;


import Controller.News.RegisterGetNotification;
import Model.RegisterNotification;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {
    

    private static final BlockingQueue<RegisterNotification> queueSendEmailpending = new LinkedBlockingQueue<>();
    
    public static void addRequest(RegisterNotification rgn) {
        try {
            queueSendEmailpending.put(rgn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static RegisterNotification takeRequest() {
        try {
            return queueSendEmailpending.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
