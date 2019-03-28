package sy.dao;

import java.util.List;

import sy.model.SbUser;

public interface Social_Security_Query_Mapper {
	
	int insert(SbUser sbUser);

	List<SbUser> findBySBid(String sbuserid);

}
