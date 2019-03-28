package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.ChinaUnicomInfoMapper;
import sy.model.ChinaUnicomInfo;
import sy.pagemodel.ChinaUnicomInfoForm;
import sy.service.ChinaUnicomInfoService;

@Service("ChinaUnicomInfoService")
public class ChinaUnicomInfoServiceImpl implements ChinaUnicomInfoService{
	
	@Autowired
	private ChinaUnicomInfoMapper chinaUnicomInfoMapper;

	
	@Override
	public void addChinaUinfo(ChinaUnicomInfo chinaUnicomInfo) {
		chinaUnicomInfoMapper.insert(chinaUnicomInfo);
	}

	@Override
	public List<ChinaUnicomInfoForm> findByYdid(String ltid) {
		List<ChinaUnicomInfoForm> listform = new ArrayList<ChinaUnicomInfoForm>();
		List<ChinaUnicomInfo> list = chinaUnicomInfoMapper.selectByltid(ltid);
		ChinaUnicomInfoForm chinaUnicomInfoForm = new ChinaUnicomInfoForm();
		for (ChinaUnicomInfo ChinaUnicomInfo : list) {
			chinaUnicomInfoForm.setChinaupersonname(ChinaUnicomInfo.getChinaupersonname());
			chinaUnicomInfoForm.setChinaupersonsex(ChinaUnicomInfo.getChinaupersonsex());
			chinaUnicomInfoForm.setChinaupapernum(ChinaUnicomInfo.getChinaupapernum());
			chinaUnicomInfoForm.setChinaupaperaddress(ChinaUnicomInfo.getChinaupaperaddress());
			listform.add(chinaUnicomInfoForm);
		}
		return listform;
	}	
	
	
	
	public ChinaUnicomInfoMapper getChinaUnicomInfoMapper() {
		return chinaUnicomInfoMapper;
	}

	public void setChinaUnicomInfoMapper(ChinaUnicomInfoMapper chinaUnicomInfoMapper) {
		this.chinaUnicomInfoMapper = chinaUnicomInfoMapper;
	}

	@Override
	public List getChinaYzm() {
		// TODO Auto-generated method stub
		return null;
	}







	
	
	

	
	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
