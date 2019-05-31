package buaa.jj.es;

import net.sf.json.JSONArray;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryRequestManager {
    @Autowired
    RestHighLevelClient client;

    @RequestMapping(value = "/paper", method = RequestMethod.PUT)
    void checkPaperExist(@RequestBody String body) {
        List<Map> papers = JSONArray.fromObject(body);
        for (Map paper : papers) {
            SearchRequest request = new SearchRequest("papers");
            SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(QueryBuilders.queryStringQuery(""));
        }

    }

    public List searchPaper(String s) throws IOException {
        List ret = new ArrayList();
        SearchRequest request = new SearchRequest("papers");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.simpleQueryStringQuery(s).field("title").field("summary")
                .field("keyword").field("author.field").field("author.organization").field("author.name"));
        builder.sort("_score", SortOrder.DESC);
        builder.size(100);
        request.source(builder);
        request.scroll(TimeValue.timeValueMinutes(10));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] searchHits = response.getHits().getHits();
        ret.add(response.getScrollId());
        if (searchHits.length == 0) {

        } else {
            for (SearchHit hit: searchHits) {
                ret.add(hit.getSourceAsMap());
            }
        }
        return ret;
    }

    public List scrollSearch(String scrollId,int times) throws IOException {
        List ret = new ArrayList();
        SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
        scrollRequest.scroll(TimeValue.timeValueMinutes(10));
        SearchResponse response = client.scroll(scrollRequest,RequestOptions.DEFAULT);
        for (int i = 1; i < times; i++) {
            response = client.scroll(scrollRequest.scrollId(response.getScrollId()),RequestOptions.DEFAULT);
        }
        ret.add(response.getScrollId());
        SearchHit[] searchHits = response.getHits().getHits();
        if (searchHits.length == 0) {

        } else {
            for (SearchHit hit: searchHits) {
                ret.add(hit.getSourceAsMap());
            }
        }
        return ret;
    }
}
