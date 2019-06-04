package buaa.jj.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ESService {
    List searchPaper(String s) throws IOException;
    List scrollSearch(String scrollId,int times) throws IOException;
    List searchPatent(String s) throws IOException;
}
