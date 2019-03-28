package sy.dao;

import java.util.List;

import sy.model.Jingdongaddress;

public interface JingdongaddressMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Jingdongaddress record);

    int insertSelective(Jingdongaddress record);

    Jingdongaddress selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Jingdongaddress record);

    int updateByPrimaryKey(Jingdongaddress record);
    
    /**
     * 根据jdid查询京东地址信息
     * @param jdid
     * @return
     */
    List<Jingdongaddress> selectjdAddressByJdid(String jdid);
}