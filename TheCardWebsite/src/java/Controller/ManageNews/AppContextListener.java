
package Controller.ManageNews;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private ProcessingSendEmailQueue processSendEmail;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        processSendEmail = new ProcessingSendEmailQueue();
        processSendEmail.startBackgroundJob();
        System.out.println("Background job started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (processSendEmail != null) {
            processSendEmail.stopBackgroundJob();
            System.out.println("Background job stopped.");
        }
    }
}
