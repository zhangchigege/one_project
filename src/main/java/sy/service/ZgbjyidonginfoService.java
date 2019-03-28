package sy.service;

import java.util.List;

import sy.model.Zgbjyidonginfo;
import sy.pagemodel.ZgbjyidonginfoForm;

/**
 * 中国北京移动接口
 * @author chendawen
 *
 */
public interface ZgbjyidonginfoService {
	/**
	 * 新增北京移动信息
	 * @param zgbjyidonginfo
	 */
	public void addZgbjyidonginfo(Zgbjyidonginfo zgbjyidonginfo);
	
	/**
	 * 根据移动id查询北京移动信息
	 * @param ydid
	 * @return
	 */
	public List<ZgbjyidonginfoForm> findByYdid(String ydid);
}
