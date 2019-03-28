package sy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.Social_Security_Query_Mapper;
import sy.model.SbUser;
import sy.service.Social_Security_Query_Service;

@Service("Social_Security_Query_Service")
public class Social_Security_Query_Serviceimpl implements Social_Security_Query_Service{

	@Autowired
	private Social_Security_Query_Mapper social_Security_Query_Mapper;
	
	@Override
	public void addUser(SbUser sbUser) {
		social_Security_Query_Mapper.insert(sbUser);
	}

	@Override
	public List<SbUser> findBySBid(String sbuserid) {
		return social_Security_Query_Mapper.findBySBid(sbuserid);
	}
	
	
	
}
