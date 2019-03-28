package sy.pagemodel;

/**
 * 淘宝收货地址
 * @author chendawen
 *
 */
public class TaobaoaddressForm {
	//收货人
	private String tbconsignee;
	//所在地区
	private String tbareaname;
	//详细地址
	private String tbaddress;
	//邮编
	private String tbzip;
	//电话号码
	private String tbphonenumber;
	
	/**
	 * get、set方法
	 */
	public String getTbconsignee() {
		return tbconsignee;
	}
	public void setTbconsignee(String tbconsignee) {
		this.tbconsignee = tbconsignee;
	}
	public String getTbareaname() {
		return tbareaname;
	}
	public void setTbareaname(String tbareaname) {
		this.tbareaname = tbareaname;
	}
	public String getTbaddress() {
		return tbaddress;
	}
	public void setTbaddress(String tbaddress) {
		this.tbaddress = tbaddress;
	}
	public String getTbzip() {
		return tbzip;
	}
	public void setTbzip(String tbzip) {
		this.tbzip = tbzip;
	}
	public String getTbphonenumber() {
		return tbphonenumber;
	}
	public void setTbphonenumber(String tbphonenumber) {
		this.tbphonenumber = tbphonenumber;
	}
	
	
}
