package sy.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		
		BigDecimal totalmoney = new BigDecimal(4563.02);
		int ms = 15;
		BigDecimal mm = new BigDecimal(ms);
		BigDecimal total = totalmoney.divide(mm, 2, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(total);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        System.out.println("过去一个月："+mon);
        
  
        
        //过去一年
	    c.setTime(new Date());
	    c.add(Calendar.YEAR, -1);
	    Date y = c.getTime();
	    String year = format.format(y);
	    System.out.println("过去一年："+year);
	    String ndate = format.format(new Date());
	    
		System.out.println(isInDate("2016-11-11",year,ndate));
	}
	
	public static boolean isInDate(String date, String DateBegin,String DateEnd) {  
		String[] strDate = date.split("-");
		String[] strDateBegin = DateBegin.split("-");
		String[] strDateEnd = DateEnd.split("-");
		int tempDate = Integer.parseInt(strDate[0]+strDate[1]+strDate[2]);  
		int tempDateBegin = Integer.parseInt(strDateBegin[0]+strDateBegin[1]+strDateBegin[2]);
		int tempDateEnd = Integer.parseInt(strDateEnd[0]+strDateEnd[1]+strDateEnd[2]);
		if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {  
			return true;  
		} else {  
			return false;  
		}  
	}

	
	
	
}
