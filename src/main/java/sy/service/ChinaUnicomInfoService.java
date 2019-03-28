package sy.service;

import java.util.List;

import sy.model.ChinaUnicomInfo;
import sy.pagemodel.ChinaUnicomInfoForm;

/**
 * 中国联通接口
 * @author cc
 *
 */
public interface ChinaUnicomInfoService {
	/**
	 * 新增联通信息
	 * @param chinaUnicomInfo
	 */
	public void addChinaUinfo(ChinaUnicomInfo chinaUnicomInfo);
	
	/**
	 * 根据联通id查询数据
	 * @param ltid
	 * @return
	 */
	public List<ChinaUnicomInfoForm> findByYdid(String ltid);
	
	
	public List  getChinaYzm();
}
