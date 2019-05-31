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

    String s = "[{'title': 'ASP.NET AJAX框架研究及其在Web开发中的应用', 'summary': '随着Web技术的发展,越来越多ASP.NET应用程序中引入AJAX技术,以降低服务器负担和解决整页刷新带来的白屏问题。而微软推出的ASP.NET AJAX框架与ASP.NET 2.0编程模型无缝集成,可大大降低程序员开发AJAX程序的难度。首先介绍AJAX引擎原理和ASP.NET AJAX服务器端与客户端架构;继而阐述ASP.NET AJAX在Web站点中的配置方法;最后以工程机械远程定位监控系统的车辆监控模块为实例,引入AJAX功能以实现异步局部更新和定时刷新,从而为用户提供了友好的交互界面。', 'author': [{'name': '仰燕兰', 'organization': '东南大学自动化学院', 'field': '系统工程 地图制图学与地理信息工程'}, {'name': '金晓雪', 'organization': '东南大学自动化学院', 'field': '分布式与并行计算 计算机网络 软件工程 通信与信息系统'}, {'name': '叶桦', 'organization': '东南大学自动化研究所', 'field': '系统工程'}], 'keyword': ['AJAX', 'ASP.NET', 'AJAX', '远程定位监控系统', '异步局部更新', '定时刷新']}]";

    @Test
    void test(){
        System.out.print(JSONArray.fromObject("[{'name':'王锦波','organization':'中国农业大学信息与电气工程学院','field':'肿瘤学','expertId':22},{'name':'王莲芝','organization':'中国农业大学信息与电气工程学院','field':'课程与教学论','expertId':23},{'name':'高万林','organization':'中国农业大学信息与电气工程学院','field':'农业经济管理','expertId':24},{'name':'喻健','organization':'中国农业大学信息与电气工程学院','field':'新闻学 编辑出版学','expertId':25},{'name':'喻健','organization':'中国农业大学信息与电气工程学院','field':'新闻学 编辑出版学','expertId':25}]"));
    }

    @Test
    void test1() throws IOException {
        String path = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        IndexRequestManager i = (IndexRequestManager) context.getBean("indexRequestManager");
        QueryRequestManager q = context.getBean(QueryRequestManager.class);
        System.out.print(JSONArray.fromObject(q.searchPaper("\"一\"")).toString());
//        i.addPapers(s);
    }

    @Test
    void test11() throws IOException {
        String path = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        IndexRequestManager i = (IndexRequestManager) context.getBean("indexRequestManager");
        QueryRequestManager q = context.getBean(QueryRequestManager.class);
//        System.out.print(JSONArray.fromObject(q.scrollSearch("DXF1ZXJ5QW5kRmV0Y2gBAAAAAAAADgUWWjYtMGJ1aFJTcUdSSi1EcVNqVE90QQ==")).toString());
//        i.addPapers(s);
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
        int loop = 100000;
        int state5 = 0;
        for (int i = 0; i < loop; ) {
            for (int j = 0; j < 7; j++) {
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
                            buff1 += 8;
                        else
                            buff2 += 8;
                    } else {
                        state[k / 2] = true;
                        if (k % 2 == 0)
                            buff1 += 8;
                        else
                            buff2 += 8;
                    }
                    if (state[0] && state[1] && state[2])
                        state[3] = true;
                } else {
                    k = (int) (Math.random() * 2);
                    System.out.println(k);
                    if (k % 2 == 0) {
                        buff1 += 8;
                    } else
                        buff2 += 8;
                }
                if (j >= 3) {
                    if (re < 3)
                        re++;
                    else
                        state5++;
                    if (cd1 > 0)
                        cd1--;
                }
                if (cd1 == 0) {
                    i++;
                    if(state[3]) {
                        state4++;
                        buff1 += 3 * 4;
                        buff2 += 3 * 4;
                        cd1 = 6;
                    } else {
                        buff1 += 3 * 4;
                        buff2 += 3 * 4;
                        cd1 = 6;
                    }
                    for (int s = 0; s < 4; s++) {
                        state[s] = false;
                    }
                }
            }
        }
        System.out.println(state4);
        System.out.println(state5/loop);
        System.out.println(buff1/loop);
        System.out.println(buff2/loop);
    }

    @Test
    void Test5(){
        byte[] b = {91,123,34,116,105,116,108,101,34,58,32,34,65,83,80,46,78,69,84,32,65,74,65,88,-61,-90,-62,-95,-62,-122,-61,-90,-62,-98,-62,-74,-61,-89,-62,-96,-62,-108,-61,-89,-62,-87,-62,-74,-61,-91,-62,-113,-62,-118,-61,-91,-62,-123,-62,-74,-61,-91,-62,-100,-62,-88,87,101,98,-61,-91,-62,-68,-62,-128,-61,-91,-62,-113,-62,-111,-61,-92,-62,-72,-62,-83,-61,-89,-62,-102,-62,-124,-61,-91,-62,-70,-62,-108,-61,-89,-62,-108,-62,-88};
        String s = new String(b);
        System.out.println(s);
    }

}
