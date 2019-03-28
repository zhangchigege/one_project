package sy.pagemodel;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 */
public class AjaxResultJson implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 是否成功 */
	private boolean success = false;

	/** 提示信息 */
	private String msg = "";

	/**  */
	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
