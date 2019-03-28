package sy.dao;

import java.util.List;

import sy.model.Zgbjyidonginfo;

public interface ZgbjyidonginfoMapper {
    int deleteByPrimaryKey(String ydid);

    int insert(Zgbjyidonginfo record);

    int insertSelective(Zgbjyidonginfo record);

    Zgbjyidonginfo selectByPrimaryKey(String ydid);

    int updateByPrimaryKeySelective(Zgbjyidonginfo record);

    int updateByPrimaryKey(Zgbjyidonginfo record);
    
    /**
     * 根据ydid查询移动信息
     * @param ydid
     * @return
     */
    List<Zgbjyidonginfo> selectByYdid(String ydid);
}