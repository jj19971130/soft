package buaa.jj.es;

import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryRequestManager {
    @Autowired
    RestHighLevelClient client;

    void searchExperts(List<Map> experts) {

    }

    public List<Map> searchPaper(String s) throws IOException {
        List ret = new ArrayList();
        SearchRequest request = new SearchRequest("papers");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery(s,"title","summery","author.*","keyword"));
        builder.sort(SortBuilders.scoreSort());
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
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
