package Controller.ManageNews;

import Controller.Checkout.ProcessingCheckoutQueue;
import Model.RegisterNotification;
import DAL.EmailDAO;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessingSendEmailQueue {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startBackgroundJob() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Processing queued orders...");
                try {
                    processQueuedSendEmail();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcessingCheckoutQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    }

    public void stopBackgroundJob() {
        scheduler.shutdown();
    }

    private void processQueuedSendEmail() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            RegisterNotification rgn = RequestQueue.takeRequest();
            EmailDAO.sendEmail(
                    rgn.getEmail(),
                    "THÔNG BÁO BÀI ĐĂNG MỚI | The Card Shop",
                    "<div class=\"email-content\" style=\"padding: 20px;\">\n"
                    + "    <p>Xin chào, " + rgn.getName() + "</p>\n"
                    + "    <p>Trang chủ của chúng tôi vừa đăng tải bài viết mới! Bạn có thể truy cập <a href=\"http://localhost:8080/TheCardWebsite/news\">Tại đây</a> để theo dõi.</p>\n"
                    + "    <p>Xin cảm ơn!</p>\n"
                    + "</div>"
            );
        }
    }

}
