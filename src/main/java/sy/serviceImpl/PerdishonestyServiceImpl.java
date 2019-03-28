package sy.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.PerdishonestyMapper;
import sy.model.Perdishonesty;
import sy.pagemodel.PerdishonestyForm;
import sy.service.PerdishonestyService;

@Service("PerdishonestyService")
public class PerdishonestyServiceImpl implements PerdishonestyService{

	@Autowired
	private PerdishonestyMapper perdishonestyMapper;
	
	@Override
	public List<PerdishonestyForm> findPerdishonestyBy(String pname, String pnumber) {
		List<PerdishonestyForm> listform = new ArrayList<PerdishonestyForm>();
		List<Perdishonesty> list = perdishonestyMapper.selectBynn(pname, pnumber);
		PerdishonestyForm perdishonestyForm = null;
		for (Perdishonesty perdishonesty : list) {
			perdishonestyForm = new PerdishonestyForm();
			perdishonestyForm.setSxname(perdishonesty.getName());
			perdishonestyForm.setSxgender(perdishonesty.getGender());
			perdishonestyForm.setSxage(perdishonesty.getAge());
			perdishonestyForm.setSxcertno(perdishonesty.getCertno());
			perdishonestyForm.setSxenforceorgname(perdishonesty.getEnforceorgname());
			perdishonestyForm.setSxprovincename(perdishonesty.getProvincename());
			perdishonestyForm.setSxenforcefileno(perdishonesty.getEnforcefileno());
			perdishonestyForm.setSxregisterdate(perdishonesty.getRegisterdate());
			perdishonestyForm.setSxcaseno(perdishonesty.getCaseno());
			perdishonestyForm.setSxcourtname(perdishonesty.getCourtname());
			perdishonestyForm.setSxcasereason(perdishonesty.getCasereason());
			perdishonestyForm.setSxalreadyenforcecase(perdishonesty.getAlreadyenforcecase());
			perdishonestyForm.setSxpublishdate(perdishonesty.getPublishdate());
			listform.add(perdishonestyForm);
		}
		return listform;
	}

	public PerdishonestyMapper getPerdishonestyMapper() {
		return perdishonestyMapper;
	}

	public void setPerdishonestyMapper(PerdishonestyMapper perdishonestyMapper) {
		this.perdishonestyMapper = perdishonestyMapper;
	}

	
	
}
