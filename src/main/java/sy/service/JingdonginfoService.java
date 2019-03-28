package sy.service;

import java.util.List;

import sy.model.Jingdongaddress;
import sy.model.Jingdonginfo;
import sy.pagemodel.JingdongAddressForm;
import sy.pagemodel.JingdonginfoForm;

/**
 * 京东接口
 * @author chendawen
 *
 */
public interface JingdonginfoService {
	/**
	 * 新增京东基本信息
	 * @param jingdonginfo
	 */
	public void addjingdonginfo(Jingdonginfo jingdonginfo);
	
	/**
	 * 新增京东地址信息
	 * @param jingdongaddress
	 */
	public void addjingdongaddress(Jingdongaddress jingdongaddress);
	
	/**
	 * 根据京东id查询京东基本信息
	 * @param jdid
	 * @return
	 */
	public List<JingdonginfoForm> findByJdid(String jdid);
	
	/**
	 * 根据京东id查询京东地址信息
	 * @param jdid
	 * @return
	 */
	public List<JingdongAddressForm> findjdAddressByJdid(String jdid);
}
