package com.ManageServices.service_interface;

import java.util.List;
import java.util.Map;

public interface PaperService {

    /**
     * 批量添加论文
     * Map {"paperId":int
     *      "title":string
     *      "summary":string
     *      "keyword":String
     *      "author":string
     *      }
     * @param list
     * @return
     */
    int insertPaperByBatch(List<Map> list);

    /**
     * 爬虫插入 论文，论文作者，作者之间关系，作者论文关系
     * @param List
     * @return
     */
    int insert(List<Map> List);

    /**
     * 批量添加专家论文
     * Map {"expertId":int
     *      "paperId":string
     *      }
     * @param list
     * @return
     */
    int insertExpertPaperByBatch(List<Map> list);

//    /**
//     * 添加论文
//     * @param title
//     * @param summary
//     * @param keyword
//     * @param author
//     * @param filePath 全文链接（ownerId为-1）或下载链接（ownerId!=-1）
//     * @param publishDate
//     * @param ownerId
//     */
//    int uploadPaper(String title,String summary, String keyword, String author, String filePath,
//                     String publishDate,int ownerId);

    /**
     * Map{
     *      "title":string
     *      "summary":string
     *      "keyword":string
     *      "author":[
     *                  {"name","organization","field"}
     *                  {"name","organization","field"}
     *                  ...
     *              ]
     *      "ownerId":int
     *      "filepath":string
     *      }
     * @param paper
     * @return
     */
    int uploadPaper(Map paper);

    /**
     * 显示论文表主页的所有内容
     * @param paperId
     * @return Map
     * filepath为全文链接或下载链接，根据ownerId判断，是下载链接则需要隐藏，全文链接显示
     */
    Map selectPaperByPid(int paperId);

    /**
     * 下载论文，
     * @param paperId
     * @param userId
     * @return null未购买不允许下载，filepath允许下载
     */
    String download(int paperId,int userId);

    /**
     * 论文定价
     * @param paperId
     * @param price
     */
    int changePrice(int paperId, int price);


}
