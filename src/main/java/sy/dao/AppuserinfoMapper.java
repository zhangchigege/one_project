package sy.dao;

import java.util.List;

import sy.model.Appuserinfo;

public interface AppuserinfoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(Appuserinfo record);

    int insertSelective(Appuserinfo record);

    Appuserinfo selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Appuserinfo record);

    int updateByPrimaryKey(Appuserinfo record);
    
    /**
     * 根据用户名和密码判断是否登录成功
     * @param mainusername
     * @param mainuserpwd
     * @return
     */
    List<Appuserinfo> selectUserinfo(String mainusername, String mainuserpwd);
}