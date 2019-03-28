package sy.service;

import java.util.List;

import sy.pagemodel.AppuserinfoForm;

/**
 * app用户信息接口
 * @author cc
 *
 */
public interface AppuserinfoService {
	/**
	 * 根据用户名和密码判断登录是否成功
	 * @param uname,upwd
	 * @return
	 */
	public List<AppuserinfoForm> selectUserinfo(String uname,String upwd);
}
