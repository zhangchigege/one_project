package sy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sy.model.SbUser;

@Service
public interface Social_Security_Query_Service {

	
	void addUser(SbUser sbUser);

	List<SbUser> findBySBid(String sbuserid);
	
}
