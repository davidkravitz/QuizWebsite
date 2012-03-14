package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.DBConnection;

@WebListener
public class QuizWebsiteListener implements ServletContextListener {

    public QuizWebsiteListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
//        ServletContext context = event.getServletContext();
//        DBConnection database = new ProductDatabase();
//        context.setAttribute("database", database);
//        ShoppingCart cart = new ShoppingCart();
//        context.setAttribute("cart", cart);    
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
	
}
