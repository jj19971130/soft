package buaa.jj;

import buaa.jj.es.IndexRequestManager;
import buaa.jj.es.QueryRequestManager;
import net.sf.json.JSONArray;
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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class test {
    @Test
    void test1() throws IOException {
        String path = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        IndexRequestManager q = (IndexRequestManager) context.getBean("indexRequestManager");
        q.addPapers();
    }

    @Test
    void test2() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("10.128.3.233",9200,"http")));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("password","123"));
        SearchRequest request = new SearchRequest("soft");
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit sh:
            response.getHits().getHits()) {
                System.out.println(sh.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3() {
        boolean state[] = new boolean[4];
        int re = 3;
        int buff1 = 0;
        int buff2 = 0;
        int state4 = 0;
        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            System.out.println("ok");
            if (state[3]) {
                state4 += 1;
                buff1 += 4 * 2;
                buff2 += 4 * 2;
            } else {
                buff1 += 2 * 2;
                buff2 += 2 * 2;
            }
            for (int j = 0; j < 4; j++) {
                state[j] = false;
            }
            for (int j = 0; j < 3; j++) {
                int k = (int) (Math.random() * 6);
                System.out.println(k);
                if (!state[3]) {
                    while (state[k / 2] && re > 0) {
                        re--;
                        k = (int) (Math.random() * 6);
                        System.out.println("re" + re + ": " + k);
                    }
                    if (state[k / 2]) {
                        if (k % 2 == 0)
                            buff1 += 4;
                        else
                            buff2 += 4;
                    } else {
                        state[k / 2] = true;
                        if (k % 2 == 0)
                            buff1 += 3;
                        else
                            buff2 += 3;
                    }
                    if (state[0] && state[1] && state[2])
                        state[3] = true;
                } else {
                    k = (int) (Math.random() * 2);
                    System.out.println(k);
                    if (k % 2 == 0) {
                        buff1 += 4;
                    } else
                        buff2 += 4;
                }

            }
            for (int j = 0; j < 6; j++) {
                int k = (int) (Math.random() * 6);
                System.out.println(k);
                if (!state[3]) {
                    while (state[k / 2] && re > 0) {
                        re--;
                        k = (int) (Math.random() * 6);
                        System.out.println("re" + re + ": " + k);
                    }
                    if (state[k / 2]) {
                        if (k % 2 == 0)
                            buff1 += 4;
                        else
                            buff2 += 4;
                    } else {
                        state[k / 2] = true;
                        if (k % 2 == 0)
                            buff1 += 3;
                        else
                            buff2 += 3;
                    }
                    if (state[0] && state[1] && state[2])
                        state[3] = true;
                } else {
                    k = (int) (Math.random() * 2);
                    System.out.println(k);
                    if (k % 2 == 0) {
                        buff1 += 4;
                    } else
                        buff2 += 4;
                }
                if (re < 3)
                    re++;
            }
        }
        System.out.println(state4);
        System.out.println(buff1/loop);
        System.out.println(buff2/loop);
    }

    @Test
    void test4() {
        boolean state[] = new boolean[4];
        int re = 3;
        int buff1 = 0;
        int buff2 = 0;
        int state4 = 0;
        int cd1 = 0;
        int loop = 10000;
        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < 9; j++) {
                int k = (int) (Math.random() * 6);
                System.out.println(k);
                if (!state[3]) {
                    while (state[k / 2] && re > 0) {
                        re--;
                        k = (int) (Math.random() * 6);
                        System.out.println("re" + re + ": " + k);
                    }
                    if (state[k / 2]) {
                        if (k % 2 == 0)
                            buff1 += 4;
                        else
                            buff2 += 4;
                    } else {
                        state[k / 2] = true;
                        if (k % 2 == 0)
                            buff1 += 3;
                        else
                            buff2 += 3;
                    }
                    if (state[0] && state[1] && state[2])
                        state[3] = true;
                } else {
                    k = (int) (Math.random() * 2);
                    System.out.println(k);
                    if (k % 2 == 0) {
                        buff1 += 4;
                    } else
                        buff2 += 4;
                }
                if (j >= 3) {
                    if (re < 3)
                        re++;
                    if (cd1 > 0)
                        cd1--;
                }
                if (cd1 == 0 && state[3] == true) {
                    state4++;
                    buff1 += 4 * 2;
                    buff2 += 4 * 2;
                    cd1 = 6;
                    for (int s = 0; s < 4; s++) {
                        state[s] = false;
                    }
                }
            }
        }
        System.out.println(state4);
        System.out.println(buff1/loop);
        System.out.println(buff2/loop);
    }


}
