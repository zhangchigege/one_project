package sy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StampToDate {
	//时间戳 yyyyMMdd
	public String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
	
	//时间戳 yyyyMMddHHmmss
	public String stampToDateM(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
