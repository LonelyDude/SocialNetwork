package controllers;

import annotation.Inject;
import context.ApplicationContextSkeleton;
import reflection.FieldReflector;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

public class InjectionServlet extends HttpServlet {

    private static final String SPRING_CONFIG = "springConfig";

    @Override
    public void init() throws ServletException {
        try {
            String appCntxPath = this.getServletContext().getInitParameter(SPRING_CONFIG);
            ApplicationContext context = ApplicationContextSkeleton.getAppContext(appCntxPath);
            List<Field> fields = FieldReflector.collectUpTo(this.getClass(), HttpServlet.class);
            fields = FieldReflector.filterList(fields);
            for (Field field : fields) {
                String name = field.getAnnotation(Inject.class).value();
                Object bean = context.getBean(name);
                if(bean == null) throw new ServletException();
                field.setAccessible(true);
                field.set(this, bean);
            }
        }
        catch(IllegalAccessException e){
            throw new ServletException("Bean doesn't found.");
        }
    }
}

