package sy.dao;

import java.util.List;

import sy.model.Taobaoaddress;

public interface TaobaoaddressMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Taobaoaddress record);

    int insertSelective(Taobaoaddress record);

    Taobaoaddress selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Taobaoaddress record);

    int updateByPrimaryKey(Taobaoaddress record);
    
    /**
     * 根据tbid查询淘宝地址信息
     * @param tbid
     * @return
     */
    List<Taobaoaddress> selecttbAddressBytbid(String tbid);
}