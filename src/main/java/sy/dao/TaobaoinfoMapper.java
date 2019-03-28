package sy.dao;

import java.util.List;

import sy.model.Taobaoinfo;

public interface TaobaoinfoMapper {
    int deleteByPrimaryKey(String tbid);

    int insert(Taobaoinfo record);

    int insertSelective(Taobaoinfo record);

    Taobaoinfo selectByPrimaryKey(String tbid);

    int updateByPrimaryKeySelective(Taobaoinfo record);

    int updateByPrimaryKey(Taobaoinfo record);

    /**
     * 根据tbid查询淘宝基本信息
     * @param tbid
     * @return
     */
    List<Taobaoinfo> selectBytbid(String tbid);
}