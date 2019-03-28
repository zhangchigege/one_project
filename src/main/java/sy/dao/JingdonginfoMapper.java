package sy.dao;

import java.util.List;

import sy.model.Jingdonginfo;

public interface JingdonginfoMapper {
    int deleteByPrimaryKey(String jdid);

    int insert(Jingdonginfo record);

    int insertSelective(Jingdonginfo record);

    Jingdonginfo selectByPrimaryKey(String jdid);

    int updateByPrimaryKeySelective(Jingdonginfo record);

    int updateByPrimaryKey(Jingdonginfo record);
    
    /**
     * 根据jdid查询京东基本信息
     * @param jdid
     * @return
     */
    List<Jingdonginfo> selectByJdid(String jdid);
}