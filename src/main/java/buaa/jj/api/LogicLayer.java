package buaa.jj.api;

import java.util.List;
import java.util.Map;

public interface LogicLayer {
    /**
     * 普通用户注册
     * @param username 登录用用户名
     * @param password 登录用密码
     * @param nickname 用户昵称
     * @param mail 用户邮箱
     * @return 是否成功
     */
    public boolean register(String username, String password, String nickname, String mail);

    /**
     * 普通用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户id
     */
    public int login(String username, String password);

    /**
     * 上传论文根据作者列表自行添加相关的作者关系
     * @param userId 专家的用户id
     * @param title 论文题目
     * @param author 作者列表，逗号分割
     * @param keyword 论文关键词
     * @param summary 论文摘要
     * @param price 论文价格，可为0
     * @param file 论文的文件路径
     * @return 是否成功
     */
    public boolean uploadPaper(int userId, String title, String author, String keyword, String summary, String price, String file);

    /**
     * 出现重名错误后手动更改作者信息
     * @param paperId 出错的论文id
     * @param oldUserId 出错的作者的用户id
     * @param newUserId 正确的作者的用户id
     * @return 是否成功
     */
    public boolean changeAuthor(int paperId, int oldUserId, int newUserId);

    /**
     * 上传专利
     * @param id 专利号
     * @param name 专利题目
     * @return 是否成功
     */
    public boolean uploadPatent(String id, String name);

    /**
     * 购买论文
     * @param userId 购买者用户id
     * @param paperId 购买的论文id
     * @return 是否成功
     */
    public boolean purchasePaper(int userId, int paperId);

    /**
     * 申请成为专家
     * @param userId 用户id
     * @param field 领域
     * @param name 专家姓名
     * @param organization 专家所在组织
     * @param mail 专家邮箱，可为null
     * @param tel 专家电话，可为null
     * @return 是否成功
     */
    public boolean expertRegiter(int userId, String field, String name, String organization, String mail, String tel);

    /**
     * 搜索功能
     * @param keyword 关键词
     * @param type true为站内搜索，false为站外搜索
     * @return 搜索结果的json字符串，格式未定
     */
    public String search(String keyword, boolean type);

    /**
     * 获取专家信息
     * @param userId 专家的用户id
     * @return 返回完整的专家表查询结果，key与列名相同
     */
    public Map getExpert(int userId);

    /**
     * 获取专家关系
     * @param userId 专家的用户id
     * @return 返回完整的专家关系表查询结果
     */
    public List<Map> getExpertRelationship(int userId);

    /**
     * 获取专家的所有论文
     * @param userId 专家的用户id
     * @return 返回所有的论文id
     */
    public List getExpertPaperList(int userId);

    /**
     * 获取论文的所有作者
     * @param paperId 论文id
     * @return 返回完整的专家论文关系表查询结果
     */
    public List<Map> getPaperAuthors(int paperId);

    /**
     * 获取所有订单
     * @param userId 用户id
     * @param from 开始日期
     * @param to 结束日期
     * @return 返回完整的订单表查询结果
     */
    public List<Map> getOrders(int userId, String from, String to);

    /**
     * 获取专家的所有专利信息
     * @param userId 专家用户id
     * @return 返回完整的专利表查询信息
     */
    public List<Map> getPatentList(int userId);

    /**
     * 获取论文信息
     * @param paperId 论文id
     * @return 返回完整的论文表查询结果
     */
    public Map getPaper(int paperId);
    public String getHotSpotInformation();
    public String getScientificInstitutionRank();

}
