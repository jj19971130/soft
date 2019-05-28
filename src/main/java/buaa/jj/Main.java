package buaa.jj;

import buaa.jj.es.IndexRequestManager;
import buaa.jj.es.QueryRequestManager;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Main {
    public static void main(String[] args){
        ContextLoader.getCurrentWebApplicationContext().getBean(IndexRequestManager.class);
    }
}
