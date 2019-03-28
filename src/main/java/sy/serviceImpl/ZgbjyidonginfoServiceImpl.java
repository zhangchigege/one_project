package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.ZgbjyidonginfoMapper;
import sy.model.Zgbjyidonginfo;
import sy.pagemodel.ZgbjyidonginfoForm;
import sy.service.ZgbjyidonginfoService;

@Service("ZgbjyidonginfoService")
public class ZgbjyidonginfoServiceImpl implements ZgbjyidonginfoService{
	
	@Autowired
	private ZgbjyidonginfoMapper zgbjyidonginfoMapper;

	@Override
	public void addZgbjyidonginfo(Zgbjyidonginfo zgbjyidonginfo) {
		zgbjyidonginfoMapper.insert(zgbjyidonginfo);
	}

	
	@Override
	public List<ZgbjyidonginfoForm> findByYdid(String ydid) {
		List<ZgbjyidonginfoForm> listform = new ArrayList<ZgbjyidonginfoForm>();
		List<Zgbjyidonginfo> list = zgbjyidonginfoMapper.selectByYdid(ydid);
		ZgbjyidonginfoForm zgbjyidonginfoForm = new ZgbjyidonginfoForm();
		for (Zgbjyidonginfo zgbjyidonginfo : list) {
			zgbjyidonginfoForm.setYdusername(zgbjyidonginfo.getUsername());
			zgbjyidonginfoForm.setYdage(zgbjyidonginfo.getAge());
			zgbjyidonginfoForm.setYdgender(zgbjyidonginfo.getGender());
			zgbjyidonginfoForm.setYdbirthDate(zgbjyidonginfo.getBirthdate());
			zgbjyidonginfoForm.setYdidtype(zgbjyidonginfo.getIdtype());
			zgbjyidonginfoForm.setYdidnumber(zgbjyidonginfo.getIdnumber());
			zgbjyidonginfoForm.setYdphonenumber(zgbjyidonginfo.getPhonenumber());
			zgbjyidonginfoForm.setYdaccountbalance(zgbjyidonginfo.getAccountbalance());
			zgbjyidonginfoForm.setYdnetage(zgbjyidonginfo.getNetage());
			zgbjyidonginfoForm.setYdaccesstime(zgbjyidonginfo.getAccesstime());
			zgbjyidonginfoForm.setYdnearlyonemonth(zgbjyidonginfo.getNearlyonemonth());
			zgbjyidonginfoForm.setYdnearlyonetotalmoney(zgbjyidonginfo.getNearlyonetotalmoney());
			zgbjyidonginfoForm.setYdnearlyonesetmealmoney(zgbjyidonginfo.getNearlyonesetmealmoney());
			zgbjyidonginfoForm.setYdnearlytwomonth(zgbjyidonginfo.getNearlytwomonth());
			zgbjyidonginfoForm.setYdnearlytwototalmoney(zgbjyidonginfo.getNearlytwototalmoney());
			zgbjyidonginfoForm.setYdnearlytwosetmealmoney(zgbjyidonginfo.getNearlytwosetmealmoney());
			zgbjyidonginfoForm.setYdnearlythreemonth(zgbjyidonginfo.getNearlythreemonth());
			zgbjyidonginfoForm.setYdnearlythreetotalmoney(zgbjyidonginfo.getNearlythreetotalmoney());
			zgbjyidonginfoForm.setYdnearlythreesetmealmoney(zgbjyidonginfo.getNearlythreesetmealmoney());
			listform.add(zgbjyidonginfoForm);
		}
		return listform;
	}
	
	
	public ZgbjyidonginfoMapper getZgbjyidonginfoMapper() {
		return zgbjyidonginfoMapper;
	}

	public void setZgbjyidonginfoMapper(ZgbjyidonginfoMapper zgbjyidonginfoMapper) {
		this.zgbjyidonginfoMapper = zgbjyidonginfoMapper;
	}












	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
