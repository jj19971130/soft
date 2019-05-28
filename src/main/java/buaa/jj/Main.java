package buaa.jj;

import buaa.jj.es.QueryRequestManager;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Main {
    public static void main(String[] args){
        String path = "beans.xml";

        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        ((ClassPathXmlApplicationContext) context).start();
        QueryRequestManager q = (QueryRequestManager) context.getBean("queryRequestManager");
        JSONObject.fromObject(q).toString();
    }
}
