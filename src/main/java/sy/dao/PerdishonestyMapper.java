package sy.dao;

import java.util.List;

import sy.model.Perdishonesty;

public interface PerdishonestyMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Perdishonesty record);

    int insertSelective(Perdishonesty record);

    Perdishonesty selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Perdishonesty record);

    int updateByPrimaryKeyWithBLOBs(Perdishonesty record);

    int updateByPrimaryKey(Perdishonesty record);
    
    List<Perdishonesty> selectBynn(String pname,String pnumber);
}