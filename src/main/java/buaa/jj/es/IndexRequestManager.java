package buaa.jj.es;

import com.ManageServices.service_interface.PaperService;
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
import java.util.List;
import java.util.Map;

@RestController
@Component
public class IndexRequestManager {
    @Autowired
    RestHighLevelClient client;

    @RequestMapping(value = "papers", method = RequestMethod.POST)
    public void addPapers(@RequestBody String body) throws IOException {
        //todo 调用数据库接口
        body = new String(body.getBytes("iso-8859-1"),"utf-8");
        List<Map> paperList = (List<Map>) JSONArray.fromObject(body);
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        PaperService paperService = context.getBean(PaperService.class);
        paperService.insert(paperList);
    }
}
