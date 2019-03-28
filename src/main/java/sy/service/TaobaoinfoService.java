package sy.service;

import java.util.List;

import sy.model.Taobaoaddress;
import sy.model.Taobaoinfo;
import sy.pagemodel.TaobaoaddressForm;
import sy.pagemodel.TaobaoinfoForm;

/**
 * 淘宝接口
 * @author chendawen
 *
 */
public interface TaobaoinfoService {
	/**
	 * 新增淘宝基本信息
	 * @param taobaoinfo
	 */
	public void addtaobaoinfo(Taobaoinfo taobaoinfo);
	
	/**
	 * 新增淘宝地址信息
	 * @param taobaoaddress
	 */
	public void addtaobaoaddress(Taobaoaddress taobaoaddress);
	
	/**
	 * 根据淘宝id查询淘宝基本信息
	 * @param tbid
	 * @return
	 */
	public List<TaobaoinfoForm> findBytdid(String tbid);
	
	/**
	 * 根据淘宝id查询淘宝地址信息
	 * @param tbid
	 * @return
	 */
	public List<TaobaoaddressForm> findtbAddressBytdid(String tbid);
}
