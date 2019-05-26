package buaa.jj;

import buaa.jj.es.QueryRequestManager;
import net.sf.json.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class test {
    @Test
    void test1(){
        String path = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        QueryRequestManager q = (QueryRequestManager) context.getBean("queryRequestManager");
        JSONObject.fromObject(q).toString();
    }

    @Test
    void test2() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("10.128.3.233",9200,"http")));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("1","1"));
        SearchRequest request = new SearchRequest();
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            System.out.println(JSONObject.fromObject(response).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
