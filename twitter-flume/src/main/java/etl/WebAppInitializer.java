package etl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer {
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {  
    
    ServletRegistration.Dynamic   servletRegistration = null;
    AnnotationConfigWebApplicationContext rootContext = null;
    
    rootContext =
      new AnnotationConfigWebApplicationContext();  
    
    rootContext.scan("etl.config");
    rootContext.setServletContext(servletContext);    
    
    servletRegistration = 
      servletContext.addServlet(
        "dispatcher", new DispatcherServlet(rootContext));  
    
    servletRegistration.addMapping("/");  
    servletRegistration.setLoadOnStartup(1);
    
    servletContext.addListener(
        new ContextLoaderListener(rootContext));
  }  
} 