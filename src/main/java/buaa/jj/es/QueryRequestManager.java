package buaa.jj.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryRequestManager {
    @Autowired
    RestHighLevelClient client;
}