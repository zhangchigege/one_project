package sy.pagemodel;

/**
 * 淘宝订单
 * @author chendawen
 *
 */
public class TaobaoOrder {
	//订单号
	private String orderId;
	//订单日期
	private String orderDate;
	//订单金额
	private String orderMoney;
	//月份
	private String month;
	//消费笔数
	private String consumeNum;
	//消费总金额
	private String consumeMoney;
	/**
	 * get、set方法 
	 */
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}
	public String getConsumeMoney() {
		return consumeMoney;
	}
	public void setConsumeMoney(String consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	
	
}
