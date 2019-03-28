package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.JingdongaddressMapper;
import sy.dao.JingdonginfoMapper;
import sy.model.Jingdongaddress;
import sy.model.Jingdonginfo;
import sy.pagemodel.JingdongAddressForm;
import sy.pagemodel.JingdonginfoForm;
import sy.service.JingdonginfoService;

@Service("JingdonginfoService")
public class JingdonginfoServiceImpl implements JingdonginfoService{
	
	@Autowired
	private JingdonginfoMapper jingdonginfoMapper;
	
	@Autowired
	private JingdongaddressMapper jingdongaddressMapper;

	@Override
	public void addjingdonginfo(Jingdonginfo jingdonginfo) {
		jingdonginfoMapper.insert(jingdonginfo);
	}

	@Override
	public void addjingdongaddress(Jingdongaddress jingdongaddress) {
		jingdongaddressMapper.insert(jingdongaddress);
	}
	
	@Override
	public List<JingdonginfoForm> findByJdid(String jdid) {
		List<JingdonginfoForm> listform = new ArrayList<JingdonginfoForm>();
		List<Jingdonginfo> list = jingdonginfoMapper.selectByJdid(jdid);
		JingdonginfoForm jingdonginfoForm = new JingdonginfoForm();
		for (Jingdonginfo jingdonginfo : list) {
			jingdonginfoForm.setJdusername(jingdonginfo.getUsername());
			jingdonginfoForm.setJdage(jingdonginfo.getAge());
			jingdonginfoForm.setJdgender(jingdonginfo.getGender());
			jingdonginfoForm.setJdbirthdate(jingdonginfo.getBirthdate());
			jingdonginfoForm.setJdidtype(jingdonginfo.getIdtype());
			jingdonginfoForm.setJdidnumber(jingdonginfo.getIdnumber());
			jingdonginfoForm.setJdname(jingdonginfo.getJdname());
			jingdonginfoForm.setJdulevel(jingdonginfo.getUlevel());
			jingdonginfoForm.setJdxylevel(jingdonginfo.getXylevel());
			jingdonginfoForm.setJdsxzed(jingdonginfo.getSxzed());
			jingdonginfoForm.setJdfqje(jingdonginfo.getFqje());
			jingdonginfoForm.setJdyyzed(jingdonginfo.getYyzed());
			jingdonginfoForm.setJdnearlyonemonth(jingdonginfo.getNearlyonemonth());
			jingdonginfoForm.setJdnearlyoneconsumetotal(jingdonginfo.getNearlyoneconsumetotal());
			jingdonginfoForm.setJdnearlyoneconsumemoney(jingdonginfo.getNearlyoneconsumemoney());
			jingdonginfoForm.setJdnearlytwomonth(jingdonginfo.getNearlytwomonth());
			jingdonginfoForm.setJdnearlytwoconsumetotal(jingdonginfo.getNearlytwoconsumetotal());
			jingdonginfoForm.setJdnearlytwoconsumemoney(jingdonginfo.getNearlytwoconsumemoney());
			jingdonginfoForm.setJdnearlythreemonth(jingdonginfo.getNearlythreemonth());
			jingdonginfoForm.setJdnearlythreeconsumetotal(jingdonginfo.getNearlythreeconsumetotal());
			jingdonginfoForm.setJdnearlythreeconsumemoney(jingdonginfo.getNearlythreeconsumemoney());
			listform.add(jingdonginfoForm);
		}
		return listform;
	}
	
	@Override
	public List<JingdongAddressForm> findjdAddressByJdid(String jdid) {
		List<JingdongAddressForm> listform = new ArrayList<JingdongAddressForm>();
		List<Jingdongaddress> list = jingdongaddressMapper.selectjdAddressByJdid(jdid);
		JingdongAddressForm jingdonginfoForm = null;
		for (Jingdongaddress jingdongaddress : list) {
			jingdonginfoForm = new JingdongAddressForm();
			jingdonginfoForm.setJdconsignee(jingdongaddress.getConsignee());
			jingdonginfoForm.setJdareaname(jingdongaddress.getAreaname());
			jingdonginfoForm.setJdaddress(jingdongaddress.getAddress());
			jingdonginfoForm.setJdphone(jingdongaddress.getPhone());
			jingdonginfoForm.setJdphonenumber(jingdongaddress.getPhonenumber());
			jingdonginfoForm.setJdemail(jingdongaddress.getEmail());
			listform.add(jingdonginfoForm);
		}
		return listform;
	}
	
	
	public JingdonginfoMapper getJingdonginfoMapper() {
		return jingdonginfoMapper;
	}

	public void setJingdonginfoMapper(JingdonginfoMapper jingdonginfoMapper) {
		this.jingdonginfoMapper = jingdonginfoMapper;
	}

	public JingdongaddressMapper getJingdongaddressMapper() {
		return jingdongaddressMapper;
	}

	public void setJingdongaddressMapper(JingdongaddressMapper jingdongaddressMapper) {
		this.jingdongaddressMapper = jingdongaddressMapper;
	}

	

	





	
	
	
	
	
	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
