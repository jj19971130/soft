package buaa.jj.es;

import com.ManageServices.service.PaperService;
import com.sun.org.apache.xerces.internal.xs.StringList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
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

    @RequestMapping(value = "paper", method = RequestMethod.POST)
    public void addPapers(@RequestBody String body) throws IOException {
        //todo 调用数据库接口
        List<Map<String,Object>> paperList = (List<Map<String,Object>>) JSONArray.fromObject(body);
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        PaperService paperService = context.getBean(PaperService.class);
        paperService.insertPaperByBatch(JSONArray.fromObject(body));
        for (Map<String,Object> o:paperList) {
            IndexRequest request = new IndexRequest("papers").source(o);
            client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                public void onResponse(IndexResponse indexResponse) {

                }

                public void onFailure(Exception e) {

                }
            });
        }
    }

    @RequestMapping(value = "expert", method = RequestMethod.POST)
    public void addExpert(@RequestBody String body) {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        PaperService paperService = context.getBean(PaperService.class);
        List<Map<String,Object>> paperList = (List<Map<String,Object>>) JSONArray.fromObject(body);
        for (Map<String,Object> o:paperList) {
            IndexRequest request = new IndexRequest("experts").source(o);
            client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                public void onResponse(IndexResponse indexResponse) {

                }

                public void onFailure(Exception e) {

                }
            });
        }
    }
}
