package sy.dao;

import java.util.List;

import sy.model.ChinaUnicomInfo;

public interface ChinaUnicomInfoMapper {
    int deleteByPrimaryKey(String chinauid);

    int insert(ChinaUnicomInfo record);

    int insertSelective(ChinaUnicomInfo record);

    ChinaUnicomInfo selectByPrimaryKey(String chinauid);

    int updateByPrimaryKeySelective(ChinaUnicomInfo record);

    int updateByPrimaryKey(ChinaUnicomInfo record);
    
    /**
     * 根据ltid查询联通信息
     * @param ltid
     * @return
     */
    List<ChinaUnicomInfo> selectByltid(String ltid);
}