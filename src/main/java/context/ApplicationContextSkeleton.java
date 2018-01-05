package context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextSkeleton {

    private static ClassPathXmlApplicationContext applicationContext;

    public static ApplicationContext getAppContext(String path){
        if(applicationContext == null) applicationContext = new ClassPathXmlApplicationContext(path);
        return applicationContext;
    }

}
