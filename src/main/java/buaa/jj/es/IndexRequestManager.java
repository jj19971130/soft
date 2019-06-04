package buaa.jj.es;

import com.ManageServices.service_interface.PaperService;
import com.ManageServices.service_interface.PatentService;
import com.sun.org.apache.xerces.internal.xs.StringList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@Component
public class IndexRequestManager {
    @Autowired
    RestHighLevelClient client;
    @Autowired
    ApplicationContext context;
    PaperService paperService;

    @RequestMapping(value = "papers", method = RequestMethod.POST)
    public void addPapers(@RequestBody String body) throws IOException {
        body = new String(body.getBytes("iso-8859-1"),"utf-8");
        List<Map> paperList = (List<Map>) JSONArray.fromObject(body);
        PaperService paperService = context.getBean(PaperService.class);
        paperService.insert(paperList);
    }

    @RequestMapping(value = "patents", method = RequestMethod.POST)
    public void addPatents(@RequestBody String body) throws UnsupportedEncodingException {
        body = new String(body.getBytes("iso-8859-1"),"utf-8");
        List<Map> patentList = (List<Map>) JSONArray.fromObject(body);
        PatentService patentService = context.getBean(PatentService.class);
        patentService.insertPatent(patentList);
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String[] test(@RequestBody String body) throws UnsupportedEncodingException {
        body = new String(body.getBytes("iso-8859-1"),"utf-8");
        List<Map> paperList = (List<Map>) JSONArray.fromObject(body);
        String json = JSONArray.fromObject(paperList).toString();
        System.out.println(body);
        System.out.println(json);
        String[] strings = {body,json};
        return strings;
    }
}
