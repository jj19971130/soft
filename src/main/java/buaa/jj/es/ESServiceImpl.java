package buaa.jj.es;

import buaa.jj.api.ESService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class ESServiceImpl implements ESService {

    @Autowired
    QueryRequestManager queryRequestManager;
    @Autowired
    IndexRequestManager indexRequestManager;

    public List searchPaper(String s) throws IOException {
        return queryRequestManager.searchPaper(s);
    }

    public List scrollSearch(String scrollId,int times) throws IOException {
        return queryRequestManager.scrollSearch(scrollId,times);
    }
}
