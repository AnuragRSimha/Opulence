package opulence;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import opulence.homepage.*;

class opulenceCustomer {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        homepage_C h = (homepage_C) context.getBean("configureHome");
        h.configureHome();

    }
}