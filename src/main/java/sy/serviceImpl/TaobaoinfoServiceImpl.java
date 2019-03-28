package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.TaobaoaddressMapper;
import sy.dao.TaobaoinfoMapper;
import sy.model.Taobaoaddress;
import sy.model.Taobaoinfo;
import sy.pagemodel.TaobaoaddressForm;
import sy.pagemodel.TaobaoinfoForm;
import sy.service.TaobaoinfoService;

@Service("TaobaoinfoService")
public class TaobaoinfoServiceImpl implements TaobaoinfoService{

	@Autowired
	private TaobaoinfoMapper taobaoinfoMapper;
	
	@Autowired
	private TaobaoaddressMapper taobaoaddressMapper;
	
	@Override
	public void addtaobaoinfo(Taobaoinfo taobaoinfo) {
		taobaoinfoMapper.insert(taobaoinfo);
	}

	@Override
	public void addtaobaoaddress(Taobaoaddress taobaoaddress) {
		taobaoaddressMapper.insert(taobaoaddress);
	}

	@Override
	public List<TaobaoinfoForm> findBytdid(String tbid) {
		List<TaobaoinfoForm> listform = new ArrayList<TaobaoinfoForm>();
		List<Taobaoinfo> list = taobaoinfoMapper.selectBytbid(tbid);
		TaobaoinfoForm taobaoinfoForm = new TaobaoinfoForm();
		for (Taobaoinfo taobaoinfo : list) {
			taobaoinfoForm.setTbusername(taobaoinfo.getUsername());
			taobaoinfoForm.setTbage(taobaoinfo.getAge());
			taobaoinfoForm.setTbgender(taobaoinfo.getGender());
			taobaoinfoForm.setTbbirthdate(taobaoinfo.getBirthdate());
			taobaoinfoForm.setTbidtype(taobaoinfo.getIdtype());
			taobaoinfoForm.setTbidnumber(taobaoinfo.getIdnumber());
			taobaoinfoForm.setTbcreditscore(taobaoinfo.getCreditscore());
			taobaoinfoForm.setTbfeedbackrate(taobaoinfo.getFeedbackrate());
			taobaoinfoForm.setTbcreditlevel(taobaoinfo.getCreditlevel());
			taobaoinfoForm.setTbcreditstanding(taobaoinfo.getCreditstanding());
			taobaoinfoForm.setTbtotalamount(taobaoinfo.getTotalamount());
			taobaoinfoForm.setTbaverageamount(taobaoinfo.getAverageamount());
			taobaoinfoForm.setTbdisputes(taobaoinfo.getDisputes());
			taobaoinfoForm.setTbdisputesnumber(taobaoinfo.getDisputesnumber());
			taobaoinfoForm.setTbnearlyonemonth(taobaoinfo.getNearlyonemonth());
			taobaoinfoForm.setTbnearlyoneconsumetotal(taobaoinfo.getNearlyoneconsumetotal());
			taobaoinfoForm.setTbnearlyoneconsumemoney(taobaoinfo.getNearlyoneconsumemoney());
			taobaoinfoForm.setTbnearlytwomonth(taobaoinfo.getNearlytwomonth());
			taobaoinfoForm.setTbnearlytwoconsumetotal(taobaoinfo.getNearlytwoconsumetotal());
			taobaoinfoForm.setTbnearlytwoconsumemoney(taobaoinfo.getNearlytwoconsumemoney());
			taobaoinfoForm.setTbnearlythreemonth(taobaoinfo.getNearlythreemonth());
			taobaoinfoForm.setTbnearlythreeconsumetotal(taobaoinfo.getNearlythreeconsumetotal());
			taobaoinfoForm.setTbnearlythreeconsumemoney(taobaoinfo.getNearlythreeconsumemoney());
			listform.add(taobaoinfoForm);
		}
		return listform;
	}

	@Override
	public List<TaobaoaddressForm> findtbAddressBytdid(String tbid) {
		List<TaobaoaddressForm> listform = new ArrayList<TaobaoaddressForm>();
		List<Taobaoaddress> list = taobaoaddressMapper.selecttbAddressBytbid(tbid);
		TaobaoaddressForm taobaoaddressForm = null;
		for (Taobaoaddress taobaoaddress : list) {
			taobaoaddressForm = new TaobaoaddressForm();
			taobaoaddressForm.setTbconsignee(taobaoaddress.getConsignee());
			taobaoaddressForm.setTbareaname(taobaoaddress.getAreaname());
			taobaoaddressForm.setTbaddress(taobaoaddress.getAddress());
			taobaoaddressForm.setTbzip(taobaoaddress.getZip());
			taobaoaddressForm.setTbphonenumber(taobaoaddress.getPhonenumber());
			listform.add(taobaoaddressForm);
		}
		return listform;
	}

	public TaobaoinfoMapper getTaobaoinfoMapper() {
		return taobaoinfoMapper;
	}

	public void setTaobaoinfoMapper(TaobaoinfoMapper taobaoinfoMapper) {
		this.taobaoinfoMapper = taobaoinfoMapper;
	}

	public TaobaoaddressMapper getTaobaoaddressMapper() {
		return taobaoaddressMapper;
	}

	public void setTaobaoaddressMapper(TaobaoaddressMapper taobaoaddressMapper) {
		this.taobaoaddressMapper = taobaoaddressMapper;
	}

	
	
}
