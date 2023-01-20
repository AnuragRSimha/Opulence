package opulence;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import opulence.adminpage.*;

class opulenceAdministrator {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        adminpage_C a = (adminpage_C) context.getBean("configureadminHome");
        a.configureadmin();

    }
}