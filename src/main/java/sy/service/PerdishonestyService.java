package sy.service;

import java.util.List;

import sy.pagemodel.PerdishonestyForm;

/**
 * 个人失信信息
 * @author chendawen
 *
 */
public interface PerdishonestyService {
	/**
	 * 根据名字和身份证首尾查询个人失信信息
	 * @param jdid
	 * @return
	 */
	public List<PerdishonestyForm> findPerdishonestyBy(String pname,String pnumber);
}	
