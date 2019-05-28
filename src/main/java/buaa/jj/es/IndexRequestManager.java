package buaa.jj.es;

import com.sun.org.apache.xerces.internal.xs.StringList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Component
public class IndexRequestManager {
    @Autowired
    RestHighLevelClient client;

    @RequestMapping(value = "paper", method = RequestMethod.POST)
    public String addPapers(@RequestBody String body) throws IOException {
        //todo 调用数据库接口

        List<Map<String,Object>> paperList = (List<Map<String,Object>>) JSONArray.fromObject(body);
        for (Map<String,Object> o:paperList) {
            IndexRequest request = new IndexRequest("papers").source(o);
            client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                public void onResponse(IndexResponse indexResponse) {

                }

                public void onFailure(Exception e) {

                }
            });
        }
        return body;
    }
}
