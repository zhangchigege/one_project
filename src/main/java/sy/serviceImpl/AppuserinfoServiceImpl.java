package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.AppuserinfoMapper;
import sy.model.Appuserinfo;
import sy.pagemodel.AppuserinfoForm;
import sy.service.AppuserinfoService;

@Service("AppuserinfoService")
public class AppuserinfoServiceImpl implements AppuserinfoService {

	@Autowired
	private AppuserinfoMapper appuserinfoMapper;
	
	@Override
	public List<AppuserinfoForm> selectUserinfo(String uname, String upwd) {
		List<AppuserinfoForm> listform = new ArrayList<AppuserinfoForm>();
		List<Appuserinfo> list = appuserinfoMapper.selectUserinfo(uname, upwd);
		AppuserinfoForm userinfoForm = null;
		for (Appuserinfo userinfo : list) {
			userinfoForm = new AppuserinfoForm();
			userinfoForm.setUname(userinfo.getUname());
			userinfoForm.setUpwd(userinfo.getUpwd());
			listform.add(userinfoForm);
		}
		return listform;
	}

	public AppuserinfoMapper getAppuserinfoMapper() {
		return appuserinfoMapper;
	}

	public void setAppuserinfoMapper(AppuserinfoMapper appuserinfoMapper) {
		this.appuserinfoMapper = appuserinfoMapper;
	}

}
