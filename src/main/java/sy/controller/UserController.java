package sy.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import sy.model.ChinaUnicomInfo;
import sy.model.Jingdongaddress;
import sy.model.Jingdonginfo;
import sy.model.SbUser;
import sy.model.Taobaoaddress;
import sy.model.Taobaoinfo;
import sy.model.User;
import sy.model.Zgbjyidonginfo;
import sy.pagemodel.AjaxResultJson;
import sy.pagemodel.AppuserinfoForm;
import sy.pagemodel.ChinaUnicomInfoForm;
import sy.pagemodel.JingdongAddressForm;
import sy.pagemodel.JingdonginfoForm;
import sy.pagemodel.PerdishonestyForm;
import sy.pagemodel.TaobaoOrder;
import sy.pagemodel.TaobaoaddressForm;
import sy.pagemodel.TaobaoinfoForm;
import sy.pagemodel.ZgbjyidonginfoForm;
import sy.service.AppuserinfoService;
import sy.service.ChinaUnicomInfoService;
import sy.service.JingdonginfoService;
import sy.service.PerdishonestyService;
import sy.service.Social_Security_Query_Service;
import sy.service.TaobaoinfoService;
import sy.service.UserService;
import sy.service.ZgbjyidonginfoService;
import sy.util.HttpClientPoolUtil;
import sy.util.MD5Util;
import sy.util.RegexUtil;
import sy.util.StampToDate;

@Controller
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TaobaoinfoService taobaoinfoService;

	@Autowired
	private JingdonginfoService jingdonginfoService;

	@Autowired
	private ZgbjyidonginfoService zgbjyidonginfoService;

	@Autowired
	private ChinaUnicomInfoService chinaUnicomInfoService;

	@Autowired
	private PerdishonestyService perdishonestyService;

	@Autowired
	private AppuserinfoService appuserinfoService;
	@Autowired
	private Social_Security_Query_Service social_Security_Query_Service;
	
	private HttpResponse response;
    private BufferedWriter writer;
	
	//手机验证码地址
    private static String phoneUrl = "http://www.bjrbj.gov.cn/csibiz/indinfo/passwordSetAction!getTelSafeCode";

  //验证码下载网址
    private static String checkCodeURL = "http://www.bjrbj.gov.cn/csibiz/indinfo/validationCodeServlet.do";
    
	private static CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(true, null, 20000, 20000, 20000);

	public static void headerWrapper(AbstractHttpMessage methord) {
		methord.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
		methord.setHeader("accept", "*/*");
		methord.setHeader("accept-language", "zh-CN");
		methord.setHeader("Accept-Encoding", "gzip, deflate");
	}

	/**
	 * 跳转到登陆界面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/applogin")
	@ResponseBody
	public AjaxResultJson applogin(@RequestParam("mainusername") String mainusername,
			@RequestParam("mainpassword") String mainpassword) {
		int status = 0;
		List<AppuserinfoForm> list = appuserinfoService.selectUserinfo(mainusername, mainpassword);
		AjaxResultJson resultJson = new AjaxResultJson();
		if (list.size() > 0) {
			status = list.size();
			resultJson.setObj(status);
			resultJson.setMsg("成功拉！");
			resultJson.setSuccess(true);
		} else {
			resultJson.setSuccess(false);
		}
		return resultJson;
	}

	/**
	 * 获取中国联通短信验证码
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/getchinaUyzm")
	@ResponseBody
	public AjaxResultJson getchinaUyzm(@RequestParam("chinaUusername") String chinaUusername) throws Exception {
		String bodyzm = "";
		HttpGet hg1 = new HttpGet("https://uac.10010.com/portal/Service/SendMSG?mobile=" + chinaUusername);// 短信请求url
		headerWrapper(hg1);
		// 发送请求
		HttpResponse httpresponseyzm = httpClient.execute(hg1);
		HttpEntity entityyzm = httpresponseyzm.getEntity();
		bodyzm = EntityUtils.toString(entityyzm);
		AjaxResultJson resultJson = new AjaxResultJson();
		resultJson.setObj(bodyzm);
		resultJson.setMsg("获取验证码成功！");
		resultJson.setSuccess(true);
		return resultJson;
	}

	/**
	 * 中国联通
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/chinaUlist")
	public String chinaUlist(HttpServletRequest request) {
		StampToDate stampToDate = new StampToDate();
		String tbuserid = request.getParameter("tbuserid4");// 淘宝id
		String jduserid = request.getParameter("jduserid3");// 京东id
		String sbuserid = request.getParameter("sbuserid2");// 社保id
		System.err.println(sbuserid);
		String username = request.getParameter("chinaUusername");
		String userpwd = request.getParameter("chinaUpassword");
		String ltcheckbox = request.getParameter("ltcheckbox");// 是否授权(1授权/0不授权)
		String ltuserid = "";// 联通id
		if ((!"".equals(username)) && (!"".equals(userpwd))) {
			ltuserid = "lt_" + username + "_" + MD5Util.string2MD5(userpwd) + "_"
					+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis()));// 联通id
			User user = new User();
			user.setUserid(ltuserid);
			user.setUsername(username);
			user.setUserpwd(MD5Util.string2MD5(userpwd));
			user.setFlag(ltcheckbox);
			user.setLoaddate(new Date());
			userService.addUser(user);

			String chinaUpersonname = ""; // 姓名
			String chinaUpersonsex = ""; // 性别
			String chinaUpaperNum = ""; // 身份证
			String chinaUpaperAddress = ""; // 地址

			try {
				HttpGet hg2 = new HttpGet(
						"https://uac.10010.com/portal/Service/MallLogin?redirectURL=http%3A%2F%2Fwww.10010.com&userName="
								+ username + "&password=" + userpwd
								+ "&pwdType=02&productType=01&redirectType=01&rememberMe=1");
				hg2.setHeader("Referer", "https://uac.10010.com/portal/homeLogin");
				HttpResponse httpresponse2 = httpClient.execute(hg2);
				HttpEntity entity2 = httpresponse2.getEntity();
				String body2 = EntityUtils.toString(entity2);
				JSONObject json = JSON.parseObject(body2);
				if (json.getString("resultCode").indexOf("0000") != -1) {// 判断是否登录成功
																			// 0000为成功标识
					// 主页
					// HttpGet hgzy = new HttpGet("http://www.10010.com/bj/");
					// headerWrapper(hgzy);
					// HttpResponse httpresponsezy = httpClient.execute(hgzy);
					// HttpEntity entityzy = httpresponsezy.getEntity();
					//
					// HttpGet hg22 = new
					// HttpGet("https://uac.10010.com/cust/infomgr/infomgrInit");
					// hg22.setHeader("Referer", "http://www.10010.com/bj/");
					// HttpResponse hg33 = httpClient.execute(hg22);

					// 个人信息
					HttpGet hgperson = new HttpGet("https://uac.10010.com/cust/infomgr/anonymousInfoAJAX");
					hgperson.setHeader("Referer", "Referer: https://uac.10010.com/cust/infomgr/infomgrInit");
					headerWrapper(hgperson);
					HttpResponse httpresponse1 = httpClient.execute(hgperson);
					HttpEntity entityperson = httpresponse1.getEntity();
					String bodyperson = EntityUtils.toString(entityperson);

					JSONObject personinfo = JSON.parseObject(bodyperson);// 获取个人信息
					chinaUpersonname = personinfo.getString("name");
					String personsex1 = personinfo.getString("sex");
					if ("1".equals(personsex1)) {
						chinaUpersonsex = "男";
					} else if ("".equals(personsex1)) {
						chinaUpersonsex = "";
					} else {
						chinaUpersonsex = "女";
					}
					chinaUpaperNum = personinfo.getString("paperNum");
					chinaUpaperAddress = personinfo.getString("paperAddr");

					ChinaUnicomInfo chinaUnicomInfo = new ChinaUnicomInfo();
					chinaUnicomInfo.setChinauid(ltuserid);
					chinaUnicomInfo.setChinaupersonname(chinaUpersonname);
					chinaUnicomInfo.setChinaupersonsex(chinaUpersonsex);
					chinaUnicomInfo.setChinaupapernum(chinaUpaperNum);
					chinaUnicomInfo.setChinaupaperaddress(chinaUpaperAddress);
					chinaUnicomInfo.setLoaddate(new Date());
					chinaUnicomInfoService.addChinaUinfo(chinaUnicomInfo);

				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!"null".equals(tbuserid) && tbuserid.length() > 4) {// 如果用户没有淘宝，则tbuserid为空
			request.setAttribute("tbuserid", tbuserid);
		}
		if (!"null".equals(jduserid) && jduserid.length() > 4) {// 如果用户没有京东，则jduserid为空
			request.setAttribute("jduserid", jduserid);
		}
		if (!"null".equals(sbuserid) && sbuserid.length() > 4) {// 如果用户没有社保，则sbuserid为空
			request.setAttribute("sbuserid", sbuserid);//sbuserid
		}
		request.setAttribute("ltuserid", ltuserid);

		if (tbuserid.length() > 50) {// 判断tbid是否存在
			List<TaobaoinfoForm> taobaoinfoformlist = taobaoinfoService.findBytdid(tbuserid);
			for (TaobaoinfoForm taobaoinfoForm : taobaoinfoformlist) {
				System.out.println("淘宝内的信息是"+taobaoinfoForm.getTbusername());
			}
			
			request.setAttribute("taobaoinfoformlist", taobaoinfoformlist);
			// 地址信息
			List<TaobaoaddressForm> taobaoaddressFormlist = taobaoinfoService.findtbAddressBytdid(tbuserid);
			request.setAttribute("taobaoaddressFormlist", taobaoaddressFormlist);
			// 查询失信信息
			String pname = "";
			String pnumber = "";
			if (taobaoinfoformlist.size() > 0) {
				if (taobaoinfoformlist.get(0).getTbusername() != null
						&& taobaoinfoformlist.get(0).getTbusername().length() > 0) {
					pname = taobaoinfoformlist.get(0).getTbusername();
					pname = pname.substring(1, pname.length());
				}
				if (taobaoinfoformlist.get(0).getTbidnumber() != null
						&& taobaoinfoformlist.get(0).getTbidnumber().length() > 0) {
					String pn1 = "";
					String pn2 = "";
					pn1 = taobaoinfoformlist.get(0).getTbidnumber().substring(0, 1);
					pn2 = taobaoinfoformlist.get(0).getTbidnumber().substring(
							taobaoinfoformlist.get(0).getTbidnumber().length() - 1,
							taobaoinfoformlist.get(0).getTbidnumber().length());
					pnumber = pn1 + pn2;
				}
				List<PerdishonestyForm> perdishonestyFormlist = perdishonestyService.findPerdishonestyBy(pname,
						pnumber);
				request.setAttribute("perdishonestyFormlist", perdishonestyFormlist);
			}
		}
		if (jduserid.length() > 50) {// 判断jdid是否存在
			// 京东信息
			List<JingdonginfoForm> jingdonginfoformlist = jingdonginfoService.findByJdid(jduserid);
			request.setAttribute("jingdonginfoformlist", jingdonginfoformlist);
			// 京东地址信息
			List<JingdongAddressForm> jingdongAddressFormlist = jingdonginfoService.findjdAddressByJdid(jduserid);
			request.setAttribute("jingdongAddressFormlist", jingdongAddressFormlist);
			if (tbuserid.length() < 10) {// 如果淘宝信息为空，则判断京东信息
				// 查询失信信息
				String pname = "";
				String pnumber = "";
				if (jingdonginfoformlist.size() > 0) {
					if (jingdonginfoformlist.get(0).getJdusername() != null
							&& jingdonginfoformlist.get(0).getJdusername().length() > 0) {
						pname = jingdonginfoformlist.get(0).getJdusername();
						pname = pname.substring(1, pname.length());
					}
					if (jingdonginfoformlist.get(0).getJdidnumber() != null
							&& jingdonginfoformlist.get(0).getJdidnumber().length() > 0) {
						String pn1 = "";
						String pn2 = "";
						pn1 = jingdonginfoformlist.get(0).getJdidnumber().substring(0, 1);
						pn2 = jingdonginfoformlist.get(0).getJdidnumber().substring(
								jingdonginfoformlist.get(0).getJdidnumber().length() - 1,
								jingdonginfoformlist.get(0).getJdidnumber().length());
						pnumber = pn1 + pn2;
					}
					List<PerdishonestyForm> perdishonestyFormlist = perdishonestyService.findPerdishonestyBy(pname,
							pnumber);
					request.setAttribute("perdishonestyFormlist", perdishonestyFormlist);
				}
			}
		}
			// 社保信息
			List<SbUser> sbUsers = social_Security_Query_Service.findBySBid(sbuserid);
			System.err.println("保存的社保信息查询后是"+sbUsers.toString());
			request.setAttribute("sbUsers", sbUsers);
		if (ltuserid.length() > 50) {// 判断ltid是否存在
			// 北京联通信息
			List<ChinaUnicomInfoForm> chinaUnicomInfoFormlist = chinaUnicomInfoService.findByYdid(ltuserid);
			request.setAttribute("chinaUnicomInfoFormlist", chinaUnicomInfoFormlist);
			if (tbuserid.length() < 10 && jduserid.length() < 10 && sbuserid.length() < 10) {// 如果淘宝信息和京东信息和移动都为空，则判断移动信息
				// 查询失信信息
				String pname = "";
				String pnumber = "";
				if (chinaUnicomInfoFormlist.size() > 0) {
					if (chinaUnicomInfoFormlist.get(0).getChinaupersonname() != null
							&& chinaUnicomInfoFormlist.get(0).getChinaupersonname().length() > 0) {
						pname = chinaUnicomInfoFormlist.get(0).getChinaupersonname();
						pname = pname.substring(1, pname.length());
					}
					if (chinaUnicomInfoFormlist.get(0).getChinaupapernum() != null
							&& chinaUnicomInfoFormlist.get(0).getChinaupapernum().length() > 0) {
						String pn1 = "";
						String pn2 = "";
						pn1 = chinaUnicomInfoFormlist.get(0).getChinaupapernum().substring(0, 1);
						pn2 = chinaUnicomInfoFormlist.get(0).getChinaupapernum().substring(
								chinaUnicomInfoFormlist.get(0).getChinaupapernum().length() - 1,
								chinaUnicomInfoFormlist.get(0).getChinaupapernum().length());
						pnumber = pn1 + pn2;
					}
					List<PerdishonestyForm> perdishonestyFormlist = perdishonestyService.findPerdishonestyBy(pname,
							pnumber);
					request.setAttribute("perdishonestyFormlist", perdishonestyFormlist);
				}
			}
		}
		return "listinfo";
	}

	

	

	/**
	 * 淘宝
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		StampToDate stampToDate = new StampToDate();
		// String userid = request.getParameter("tbuserid");
		String username = request.getParameter("tbusername");// 用户名
		String userpwd = request.getParameter("tbuserpwd");// 密码
		//System.out.println(username+""+userpwd);
		String tbcheckbox = request.getParameter("tbcheckbox");// 是否授权(1授权/0不授权)
		System.out.println(tbcheckbox
				+ "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		String tbuserid = "";// 淘宝id
		if ((!"".equals(username)) && (!"".equals(userpwd))) {
			tbuserid = "tb_" + username + "_" + MD5Util.string2MD5(userpwd) + "_"
					+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis()));
			User user = new User();
			user.setUserid(tbuserid);
			//System.out.println(tbuserid);
			user.setUsername(username);
			user.setUserpwd(MD5Util.string2MD5(userpwd));
			user.setFlag(tbcheckbox);
			user.setLoaddate(new Date());
			userService.addUser(user);

			try {
				// 模拟登陆淘宝
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				HttpPost httpPost = new HttpPost("https://login.taobao.com/member/login.jhtml");
				headerWrapper(httpPost);
				httpPost.setHeader("accept-Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

				params.add(new BasicNameValuePair("TPL_password_2", userpwd));
				params.add(new BasicNameValuePair("TPL_username", username));
				/*params.add(new BasicNameValuePair("newlogin", "1"));
				params.add(new BasicNameValuePair("callback", "1"));*/

				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

				// 发送请求
				HttpResponse httpresponse = httpClient.execute(httpPost);
				HttpEntity entity = httpresponse.getEntity();
				String body = EntityUtils.toString(entity);

				String endUrl = RegexUtil.regerMatch("top.location.href = \"(.*?)\";", body, 1);
				System.out.println(endUrl);

				List<String> rUrl = RegexUtil.regerMatchList("<script src=\"(.[^>]*?)\"></script>", body, 1);
				if (endUrl != null) {
					String tbusername = ""; // 真实名字
					String tbage = ""; // 年龄
					String tbgender = ""; // 性别
					String tbbirthdate = ""; // 出生年月
					String tbidtype = ""; // 证件类型
					String tbidnumber = ""; // 证件号码
					String tbcreditscore = ""; // 信用积分
					String tbfeedbackrate = ""; // 好评率
					String tbcreditlevel = ""; // 信用等级
					String tbcreditstanding = "";// 信用度
					String tbtotalamount = ""; // 最近一年购买总金额
					String tbaverageamount = "";// 平均每单金额
					String tbdisputes = ""; // 交易纠纷数
					String tbdisputesnumber = "";// 拍下未付款笔数

					for (String url : rUrl) {
						HttpGet hg1 = new HttpGet(url);
						headerWrapper(hg1);
						HttpResponse httpresponse1 = httpClient.execute(hg1);
						HttpEntity entity1 = httpresponse1.getEntity();
						String body1 = EntityUtils.toString(entity1);
					}

					HttpGet hg1 = new HttpGet(endUrl);
					headerWrapper(hg1);
					HttpResponse httpresponse1 = httpClient.execute(hg1);
					HttpEntity entity1 = httpresponse1.getEntity();
					String body1 = EntityUtils.toString(entity1);

					HttpGet hguser = new HttpGet("https://i.taobao.com/user/baseInfoSet.htm");// 获取用户信息
					headerWrapper(hguser);
					HttpResponse httpresponse3 = httpClient.execute(hguser);
					HttpEntity entity3 = httpresponse3.getEntity();
					String body3 = EntityUtils.toString(entity3);
					Document zname = Jsoup.parse(body3);
					if (zname == null) {
						return null;
					} else {
						String elemszgender = "";// 0是男 1是女
						tbusername = zname.select("input[name=_fm.b._0.r]").get(0).attr("value");
						elemszgender = zname.select("input[checked=checked]").get(0).attr("value");
						if ("0".equals(elemszgender)) {// 翻译
							tbgender = "男";
						} else {
							tbgender = "女";
						}
						Elements elemszbirthday = zname.select("option[selected=selected]");
						if (elemszbirthday.toString().contains("年")) {
							tbbirthdate = "";// 出生年月
							tbage = "";// 年龄
						} else {
							String text4 = "";
							StringBuffer tt = new StringBuffer();
							for (int i = 0; i < elemszbirthday.size(); i++) {
								text4 = elemszbirthday.get(i).html();
								tt.append(text4 + "-");
							}
							Calendar cal = Calendar.getInstance();
							int year = cal.get(Calendar.YEAR);
							int a = Integer.parseInt(elemszbirthday.get(0).html());
							int ag = year - a;
							tbage = String.valueOf(ag);
							tbbirthdate = tt.substring(0, tt.length() - 1).toString();
						}

						// 获取用户信息身份证号
						HttpGet hgid = new HttpGet("https://member1.taobao.com/member/fresh/certify_info.htm");
						headerWrapper(hgid);
						HttpResponse httpresponseid = httpClient.execute(hgid);
						HttpEntity entityid = httpresponseid.getEntity();
						String bodyid = EntityUtils.toString(entityid);
						Document docid = Jsoup.parse(bodyid);
						if (docid == null) {
							return null;
						} else {
							Elements elemsis = docid.select("div[class=explain-info]");// 头部
							tbidtype = elemsis.select("span").get(3).html();
							tbidtype = tbidtype.substring(0, tbidtype.length() - 1).toString();
							tbidnumber = elemsis.select("div[class=left]").get(3).html();
						}
						// 声明list集合来存储用户信息
						// List<TaobaoUser> tblistuser = new
						// ArrayList<TaobaoUser>();
						// TaobaoUser taobaoUser = new TaobaoUser();
						// taobaoUser.setUsername(elemszname);
						// taobaoUser.setAge(age);
						// taobaoUser.setGender(elemszgender);
						// taobaoUser.setBirthDate(birthdate);
						// taobaoUser.setIdtype(elemsid);
						// taobaoUser.setIdnumber(elemsidnumber);
						// tblistuser.add(taobaoUser);
						// //用户信息
						// request.setAttribute("tblistuser",tblistuser);
					}

					// 获取订单数据
					HttpPost httpPostborder = new HttpPost(
							"https://buyertrade.taobao.com/trade/itemlist/asyncBought.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&_input_charset=utf8");
					headerWrapper(httpPostborder);
					httpPostborder.setHeader("accept-Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
					httpPostborder.setHeader("X-Requested-With", "XMLHttpRequest");
					httpPostborder.setHeader("Referer",
							"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
					List<NameValuePair> paramstb = new ArrayList<NameValuePair>();
					paramstb.add(new BasicNameValuePair("pageNum", "1"));
					paramstb.add(new BasicNameValuePair("pageSize", "15"));
					paramstb.add(new BasicNameValuePair("prePageNo", "1"));
					httpPostborder.setEntity(new UrlEncodedFormEntity(paramstb, "UTF-8"));
					// 发送请求
					HttpResponse httpresponsetborder = httpClient.execute(httpPostborder);
					HttpEntity entitytborder = httpresponsetborder.getEntity();
					String bodytborder = EntityUtils.toString(entitytborder);
					bodytborder = bodytborder.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)",
							"");
					JSONObject json = JSON.parseObject(bodytborder);// json字符串转换成jsonobject对象
					JSONObject page = json.getJSONObject("page");
					int totalPage = Integer.parseInt(page.getString("totalPage"));// 总页数
					// 声明list集合来存储订单信息
					List<TaobaoOrder> listorder = new ArrayList<TaobaoOrder>();
					TaobaoOrder taobaoOrder = null;
					for (int i = 0; i < totalPage; i++) {
						String tp = String.valueOf(i + 1);
						HttpPost httpPostborder2 = new HttpPost(
								"https://buyertrade.taobao.com/trade/itemlist/asyncBought.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&_input_charset=utf8");
						headerWrapper(httpPostborder2);
						httpPostborder2.setHeader("accept-Content-Type",
								"application/x-www-form-urlencoded; charset=UTF-8");
						httpPostborder2.setHeader("X-Requested-With", "XMLHttpRequest");
						httpPostborder2.setHeader("Referer",
								"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");

						List<NameValuePair> paramstb2 = new ArrayList<NameValuePair>();
						paramstb2.add(new BasicNameValuePair("pageNum", tp));
						paramstb2.add(new BasicNameValuePair("pageSize", "15"));
						paramstb2.add(new BasicNameValuePair("prePageNo", "1"));
						httpPostborder2.setEntity(new UrlEncodedFormEntity(paramstb2, "UTF-8"));
						// 发送请求
						HttpResponse httpresponsetborder2 = httpClient.execute(httpPostborder2);
						HttpEntity entitytborder2 = httpresponsetborder2.getEntity();
						String bodytborder2 = EntityUtils.toString(entitytborder2);
						bodytborder2 = bodytborder2.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1")
								.replaceAll("^((\r\n)|\n)", "");
						for (int j = 0; j < 15; j++) {
							JSONObject json2 = JSON.parseObject(bodytborder2);// json字符串转换成jsonobject对象
							JSONArray mainOrders = json2.getJSONArray("mainOrders");
							int mainOrderssize = mainOrders.size();
							if (mainOrderssize == 15) {
								JSONObject orderInfo = mainOrders.getJSONObject(j).getJSONObject("orderInfo");
								String orderid = orderInfo.getString("id");// 订单号
								String createDay = orderInfo.getString("createDay");// 订单日期
								JSONObject payInfo = mainOrders.getJSONObject(j).getJSONObject("payInfo");
								String actualFee = payInfo.getString("actualFee");// 订单金额
								System.out.println(orderid + "---" + actualFee + "---" + createDay);
								taobaoOrder = new TaobaoOrder();
								taobaoOrder.setOrderId(orderid);
								taobaoOrder.setOrderDate(createDay);
								taobaoOrder.setOrderMoney(actualFee);
								listorder.add(taobaoOrder);
							} else {
								if (j == 0) {
									for (int a = 0; a < mainOrderssize; a++) {
										JSONObject orderInfo = mainOrders.getJSONObject(a).getJSONObject("orderInfo");
										String orderid = orderInfo.getString("id");// 订单号
										String createDay = orderInfo.getString("createDay");// 订单日期
										JSONObject payInfo = mainOrders.getJSONObject(a).getJSONObject("payInfo");
										String actualFee = payInfo.getString("actualFee");// 订单金额
										System.out.println(orderid + "---" + actualFee + "---" + createDay);
										taobaoOrder = new TaobaoOrder();
										taobaoOrder.setOrderId(orderid);
										taobaoOrder.setOrderDate(createDay);
										taobaoOrder.setOrderMoney(actualFee);
										listorder.add(taobaoOrder);
									}
								}
							}
						}
					}
					BigDecimal totalmoney = BigDecimal.ZERO;
					int bs = 0;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					// 过去一年
					c.setTime(new Date());
					c.add(Calendar.YEAR, -1);
					Date y = c.getTime();
					String gqyear = format.format(y);
					String DateBegin = format.format(new Date());
					// 当前时间
					String[] strDateBegin = DateBegin.split("-");
					// 过去一年时间
					String[] strDateEnd = gqyear.split("-");
					int tempDateBegin = Integer.parseInt(strDateBegin[0] + strDateBegin[1] + strDateBegin[2]);
					int tempDateEnd = Integer.parseInt(strDateEnd[0] + strDateEnd[1] + strDateEnd[2]);
					for (int i = 0; i < listorder.size(); i++) {
						String[] strDate = listorder.get(i).getOrderDate().split("-");
						int tempDate = Integer.parseInt(strDate[0] + strDate[1] + strDate[2]);
						if ((tempDate <= tempDateBegin && tempDate >= tempDateEnd)) {
							totalmoney = totalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 总金额
							bs = bs + 1;// 笔数
						}
					}
					BigDecimal mm = new BigDecimal(bs);
					// 平均金额
					BigDecimal baverageAmount = totalmoney.divide(mm, 2, BigDecimal.ROUND_HALF_EVEN);
					tbtotalamount = totalmoney.toString();
					tbaverageamount = baverageAmount.toString();

					// 获取淘宝信息
					HttpGet hgxy = new HttpGet("https://rate.taobao.com/myRate.htm");
					headerWrapper(hgxy);
					HttpResponse httpresponsexy = httpClient.execute(hgxy);
					HttpEntity entityxy = httpresponsexy.getEntity();
					String bodyxy = EntityUtils.toString(entityxy);
					// System.out.println("=======================================================");
					Document xy = Jsoup.parse(bodyxy);
					if (xy == null) {
						return null;
					}
					// 信用积分
					tbcreditscore = xy.select("h4[class=tb-rate-ico-bg ico-buyer]>a").get(0).html();
					// 好评率
					tbfeedbackrate = xy.select("p[class=rate-summary]>strong").get(0).html();

					// 获取淘气值
					HttpPost httpPosttqz = new HttpPost("https://vip.taobao.com/ajax/getGoldUser.do");
					headerWrapper(httpPosttqz);
					httpPosttqz.setHeader("Referer",
							"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
					// 发送请求
					HttpResponse httpresponsetqz = httpClient.execute(httpPosttqz);
					HttpEntity entitytqz = httpresponsetqz.getEntity();
					String bodytqz = EntityUtils.toString(entitytqz);
					bodytqz = bodytqz.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
					System.out.print(bodytqz);
					JSONObject jsontqz = JSON.parseObject(bodytqz);// json字符串转换成jsonobject对象
					JSONObject datatqz = jsontqz.getJSONObject("data");
					tbcreditstanding = datatqz.getString("taoScore");// 信用度（淘气值）
					if (Integer.parseInt(tbcreditstanding) <= 1000) {
						tbcreditlevel = "普通会员";
					} else
						if (Integer.parseInt(tbcreditstanding) > 1000 && Integer.parseInt(tbcreditstanding) <= 2500) {
						tbcreditlevel = "超级会员";
					} else if (Integer.parseInt(tbcreditstanding) > 2500) {
						tbcreditlevel = "APASS";
					}
					// 声明list集合来存储用户信息
					/* List<Taobaoinfo> tblistinfo = new ArrayList<Taobaoinfo>();
					 Taobaoinfo taobaoinfo = new Taobaoinfo();
					 taobaoinfo.setCreditScore(elemsxyf);
					 taobaoinfo.setFeedbackRate(elemshbp);
					 taobaoinfo.setCreditLevel(taoxylevel);
					 taobaoinfo.setCreditStanding(taoScore);
					 taobaoinfo.setTotalAmount(totalmoney.toString());
					 taobaoinfo.setAverageAmount(baverageAmount.toString());
					 tblistinfo.add(taobaoinfo);
					// //淘宝信息
					 request.setAttribute("tblistinfo",tblistinfo);*/

					// 淘宝收货人地址<a class="html-attribute-value html-external-link" target="_blank" href="//member1.taobao.com/member/fresh/deliver_address.htm" rel="noreferrer noopener">//member1.taobao.com/member/fresh/deliver_address.htm</a>
					HttpGet hg2 = new HttpGet(
                            "https://member1.taobao.com/member/fresh/deliver_address.htm");
                    headerWrapper(hg2);
                    HttpResponse httpresponse2 = httpClient.execute(hg2);
                    HttpEntity entity2 = httpresponse2.getEntity();
                    String body2 = EntityUtils.toString(entity2);
                    Document doc = Jsoup.parse(body2);
					if (doc == null) {
						return null;
					}
					Elements elems = doc.select("div[class=addressList]");// 地址
					Elements elems2 = doc.select("tr[class=next-table-row first]");// 默认地址
					
					
					if (elems.isEmpty()) {
						return null;
					}
					if (elems2.isEmpty()) {
						return null;
					}
					String text;// 默认收货地址
					String text2;// 其余收货地址
					Taobaoaddress ta = new Taobaoaddress();
					List<Taobaoaddress> tblistaddress = new ArrayList<Taobaoaddress>();
					text = elems2.get(0).html();
					text = text.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", ""); // 正则表达式过滤标签
					text = text.replaceAll("[(/>)<]", "");
					text = text.replaceAll("\n", "|");
					text = text.replaceAll(" ", "");
					String s[] = text.split("\\|");
					ta.setConsignee(s[0]);
					ta.setAreaname(s[1]);
					ta.setAddress(s[2]);
					ta.setZip(s[3]);
					ta.setPhonenumber(s[4]);
					tblistaddress.add(ta);
					for (int i = 0; i < elems.size(); i++) {
						ta = new Taobaoaddress();
						text2 = elems.get(i).html();
						text2 = text2.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", ""); // 正则表达式过滤标签
						text2 = text2.replaceAll("[(/>)<]", "");
						text2 = text2.replaceAll("\n", "|");
						text2 = text2.replaceAll(" ", "");
						String[] s2 = text2.split("\\|");
						ta.setConsignee(s2[0]);
						ta.setAreaname(s2[1]);
						ta.setAddress(s2[2]);
						ta.setZip(s2[3]);
						ta.setPhonenumber(s2[4]);
						tblistaddress.add(ta);
					}
					// 收获地址信息
					// request.setAttribute("tblistaddress",tblistaddress);

					BigDecimal jototalmoney = BigDecimal.ZERO;
					BigDecimal jttotalmoney = BigDecimal.ZERO;
					BigDecimal jstotalmoney = BigDecimal.ZERO;
					int oybs = 0;
					int tybs = 0;
					int sybs = 0;
					// 过去一月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -1);
					Date m = c.getTime();
					String mon = format.format(m);
					// 过去一个月时间
					String[] mDateEnd = mon.split("-");
					int mtempDateEnd = Integer.parseInt(mDateEnd[0] + mDateEnd[1]);
					String ymonth = mDateEnd[0] + "-" + mDateEnd[1];

					// 过去2月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -2);
					Date m2 = c.getTime();
					String mon2 = format.format(m2);
					// 过去近2个月时间
					String[] mDateEnd2 = mon2.split("-");
					int mtempDateEnd2 = Integer.parseInt(mDateEnd2[0] + mDateEnd2[1]);
					String tmonth = mDateEnd2[0] + "-" + mDateEnd2[1];

					// 过去3月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -3);
					Date m3 = c.getTime();
					String mon3 = format.format(m3);
					// 过去近3个月时间
					String[] mDateEnd3 = mon3.split("-");
					int mtempDateEnd3 = Integer.parseInt(mDateEnd3[0] + mDateEnd3[1]);
					String smonth = mDateEnd3[0] + "-" + mDateEnd3[1];
					// 声明list集合来存储订单消费信息
					// List<TaobaoOrder> tblistorderconsume = new
					// ArrayList<TaobaoOrder>();
					// TaobaoOrder taobaoOrderconsume1 = new TaobaoOrder();
					// TaobaoOrder taobaoOrderconsume2 = new TaobaoOrder();
					// TaobaoOrder taobaoOrderconsume3 = new TaobaoOrder();
					for (int i = 0; i < listorder.size(); i++) {
						String[] strDate = listorder.get(i).getOrderDate().split("-");
						int tempDate = Integer.parseInt(strDate[0] + strDate[1]);
						if (tempDate == mtempDateEnd) {
							jototalmoney = jototalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近一个月月消费总金额
							oybs = oybs + 1;
						} else if (tempDate == mtempDateEnd2) {
							jttotalmoney = jttotalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近2个月月消费总金额
							tybs = tybs + 1;
						} else if (tempDate == mtempDateEnd3) {
							jstotalmoney = jstotalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近3个月月消费总金额
							sybs = sybs + 1;
						}
					}
					// taobaoOrderconsume1.setMonth(ymonth);
					// taobaoOrderconsume1.setConsumeNum(String.valueOf(oybs));
					// taobaoOrderconsume1.setConsumeMoney(jototalmoney.toString());
					//
					// taobaoOrderconsume2.setMonth(tmonth);
					// taobaoOrderconsume2.setConsumeNum(String.valueOf(tybs));
					// taobaoOrderconsume2.setConsumeMoney(jttotalmoney.toString());
					//
					// taobaoOrderconsume3.setMonth(smonth);
					// taobaoOrderconsume3.setConsumeNum(String.valueOf(sybs));
					// taobaoOrderconsume3.setConsumeMoney(jstotalmoney.toString());
					//
					// tblistorderconsume.add(taobaoOrderconsume1);
					// tblistorderconsume.add(taobaoOrderconsume2);
					// tblistorderconsume.add(taobaoOrderconsume3);
					// //订单消费信息
					// request.setAttribute("tblistorderconsume",tblistorderconsume);

					// *******************将淘宝信息入库start******************************
					Taobaoinfo taobaoinfo = new Taobaoinfo();
					taobaoinfo.setTbid(tbuserid);
					taobaoinfo.setUsername(tbusername);
					taobaoinfo.setAge(tbage);
					taobaoinfo.setGender(tbgender);
					taobaoinfo.setBirthdate(tbbirthdate);
					taobaoinfo.setIdtype(tbidtype);
					taobaoinfo.setIdnumber(tbidnumber);
					taobaoinfo.setCreditscore(tbcreditscore);
					taobaoinfo.setFeedbackrate(tbfeedbackrate);
					taobaoinfo.setCreditlevel(tbcreditlevel);
					taobaoinfo.setCreditstanding(tbcreditstanding);
					taobaoinfo.setTotalamount(tbtotalamount);
					taobaoinfo.setAverageamount(tbaverageamount);
					taobaoinfo.setDisputes(tbdisputes);
					taobaoinfo.setDisputesnumber(tbdisputesnumber);
					taobaoinfo.setNearlyonemonth(ymonth);
					taobaoinfo.setNearlyoneconsumetotal(String.valueOf(oybs));
					taobaoinfo.setNearlyoneconsumemoney(jototalmoney.toString());
					taobaoinfo.setNearlytwomonth(tmonth);
					taobaoinfo.setNearlytwoconsumetotal(String.valueOf(tybs));
					taobaoinfo.setNearlytwoconsumemoney(jttotalmoney.toString());
					taobaoinfo.setNearlythreemonth(smonth);
					taobaoinfo.setNearlythreeconsumetotal(String.valueOf(sybs));
					taobaoinfo.setNearlythreeconsumemoney(jstotalmoney.toString());
					taobaoinfo.setLoaddate(new Date());
					taobaoinfoService.addtaobaoinfo(taobaoinfo);

					Taobaoaddress taobaoaddress = null;
					/*for (int i = 0; i < tblistaddress.size(); i++) {
						taobaoaddress = new Taobaoaddress();
						taobaoaddress.setTid("t" + i + username
								+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis())));// 规则是t+i+用户名+时间戳（确保唯一ID）
						taobaoaddress.setTbid(tbuserid);
						taobaoaddress.setConsignee(tblistaddress.get(i).getConsignee());
						taobaoaddress.setAreaname(tblistaddress.get(i).getAreaname());
						taobaoaddress.setAddress(tblistaddress.get(i).getAddress());
						taobaoaddress.setZip(tblistaddress.get(i).getZip());
						taobaoaddress.setPhonenumber(tblistaddress.get(i).getPhonenumber());
						taobaoaddress.setLoaddate(new Date());
						taobaoinfoService.addtaobaoaddress(taobaoaddress);
					}*/
					// *******************将淘宝信息入库end******************************
				} else {
					String tbusername = ""; // 真实名字
					String tbage = ""; // 年龄
					String tbgender = ""; // 性别
					String tbbirthdate = ""; // 出生年月
					String tbidtype = ""; // 证件类型
					String tbidnumber = ""; // 证件号码
					String tbcreditscore = ""; // 信用积分
					String tbfeedbackrate = ""; // 好评率
					String tbcreditlevel = ""; // 信用等级
					String tbcreditstanding = "";// 信用度
					String tbtotalamount = ""; // 最近一年购买总金额
					String tbaverageamount = "";// 平均每单金额
					String tbdisputes = ""; // 交易纠纷数
					String tbdisputesnumber = "";// 拍下未付款笔数

					String sessionid = body.substring(
							body.indexOf("https://passport.alipay.com/mini_apply_st.js?site=0&token="),
							body.lastIndexOf("&callback"));
					System.out.println(sessionid);

					HttpGet hg1 = new HttpGet(sessionid + "&callback=vstCallback65");
					headerWrapper(hg1);
					HttpResponse httpresponse1 = httpClient.execute(hg1);
					HttpEntity entity1 = httpresponse1.getEntity();
					String body1 = EntityUtils.toString(entity1);

					String st = "";
					String regex = "vstCallback65\\((.*)\\)";
					Pattern compile = Pattern.compile(regex);
					Matcher matcher = compile.matcher(body1);
					while (matcher.find()) {
						String group = matcher.group(1);
						JSONObject string2Json = String2Json(group);
						try {
							JSONObject object = (JSONObject) string2Json.get("data");
							st = (String) object.get("st");
							System.out.println(st);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					HttpGet hg2 = new HttpGet("https://login.taobao.com/member/vst.htm?st=" + st
							+ "&params=style%3Dminisimple%26sub%3Dtrue%26TPL_username%3D" + username
							+ "%26loginsite%3D0%26from_encoding%3D%26not_duplite_str%3D%26guf%3D%26full_redirect%3D%26isIgnore%3D%26need_sign%3D%26sign%3D%26from%3Ddatacube%26TPL_redirect_url%3Dhttp%25253A%25252F%25252Fmofang.taobao.com%25252Fs%25252Flogin%26css_style%3D%26allp%3D&_ksTS=1404787873165_78&callback=jsonp79");
					headerWrapper(hg2);
					HttpResponse httpresponse2 = httpClient.execute(hg2);
					HttpEntity entity2 = httpresponse2.getEntity();
					String body2 = EntityUtils.toString(entity2);

					HttpGet hg4 = new HttpGet("https://www.taobao.com/");
					headerWrapper(hg4);
					HttpResponse httpresponse4 = httpClient.execute(hg4);
					HttpEntity entity4 = httpresponse4.getEntity();
					String body4 = EntityUtils.toString(entity4);

					HttpGet hguser = new HttpGet("https://i.taobao.com/user/baseInfoSet.htm");// 获取用户信息
					headerWrapper(hguser);
					HttpResponse httpresponse3 = httpClient.execute(hguser);
					HttpEntity entity3 = httpresponse3.getEntity();
					String body3 = EntityUtils.toString(entity3);
					Document zname = Jsoup.parse(body3);
					if (zname == null) {
						return null;
					} else {
						String elemszgender = "";// 0是男 1是女
						tbusername = zname.select("input[name=_fm.b._0.r]").get(0).attr("value");
						elemszgender = zname.select("input[checked=checked]").get(0).attr("value");
						if ("0".equals(elemszgender)) {// 翻译
							tbgender = "男";
						} else {
							tbgender = "女";
						}
						Elements elemszbirthday = zname.select("option[selected=selected]");
						if (elemszbirthday.toString().contains("年")) {
							tbbirthdate = "";// 出生年月
							tbage = "";// 年龄
						} else {
							String text4 = "";
							StringBuffer tt = new StringBuffer();
							for (int i = 0; i < elemszbirthday.size(); i++) {
								text4 = elemszbirthday.get(i).html();
								tt.append(text4 + "-");
							}
							Calendar cal = Calendar.getInstance();
							int year = cal.get(Calendar.YEAR);
							int a = Integer.parseInt(elemszbirthday.get(0).html());
							int ag = year - a;
							tbage = String.valueOf(ag);
							tbbirthdate = tt.substring(0, tt.length() - 1).toString();
						}

						// 获取用户信息身份证号
						HttpGet hgid = new HttpGet("https://member1.taobao.com/member/fresh/certify_info.htm");
						headerWrapper(hgid);
						HttpResponse httpresponseid = httpClient.execute(hgid);
						HttpEntity entityid = httpresponseid.getEntity();
						String bodyid = EntityUtils.toString(entityid);
						Document docid = Jsoup.parse(bodyid);
						if (docid == null) {
							return null;
						} else {
							Elements elemsis = docid.select("div[class=explain-info]");// 头部
							tbidtype = elemsis.select("span").get(3).html();
							tbidtype = tbidtype.substring(0, tbidtype.length() - 1).toString();
							tbidnumber = elemsis.select("div[class=left]").get(3).html();
						}
					}

					// 获取订单数据
					HttpPost httpPostborder = new HttpPost(
							"https://buyertrade.taobao.com/trade/itemlist/asyncBought.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&_input_charset=utf8");
					headerWrapper(httpPostborder);
					httpPostborder.setHeader("accept-Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
					httpPostborder.setHeader("X-Requested-With", "XMLHttpRequest");
					httpPostborder.setHeader("Referer",
							"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
					List<NameValuePair> paramstb = new ArrayList<NameValuePair>();
					paramstb.add(new BasicNameValuePair("pageNum", "1"));
					paramstb.add(new BasicNameValuePair("pageSize", "15"));
					paramstb.add(new BasicNameValuePair("prePageNo", "1"));
					httpPostborder.setEntity(new UrlEncodedFormEntity(paramstb, "UTF-8"));
					// 发送请求
					HttpResponse httpresponsetborder = httpClient.execute(httpPostborder);
					HttpEntity entitytborder = httpresponsetborder.getEntity();
					String bodytborder = EntityUtils.toString(entitytborder);
					bodytborder = bodytborder.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)",
							"");
					JSONObject json = JSON.parseObject(bodytborder);// json字符串转换成jsonobject对象
					JSONObject page = json.getJSONObject("page");
					int totalPage = Integer.parseInt(page.getString("totalPage"));// 总页数
					// 声明list集合来存储订单信息
					List<TaobaoOrder> listorder = new ArrayList<TaobaoOrder>();
					TaobaoOrder taobaoOrder = null;
					for (int i = 0; i < totalPage; i++) {
						String tp = String.valueOf(i + 1);
						HttpPost httpPostborder2 = new HttpPost(
								"https://buyertrade.taobao.com/trade/itemlist/asyncBought.htm?action=itemlist/BoughtQueryAction&event_submit_do_query=1&_input_charset=utf8");
						headerWrapper(httpPostborder2);
						httpPostborder2.setHeader("accept-Content-Type",
								"application/x-www-form-urlencoded; charset=UTF-8");
						httpPostborder2.setHeader("X-Requested-With", "XMLHttpRequest");
						httpPostborder2.setHeader("Referer",
								"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");

						List<NameValuePair> paramstb2 = new ArrayList<NameValuePair>();
						paramstb2.add(new BasicNameValuePair("pageNum", tp));
						paramstb2.add(new BasicNameValuePair("pageSize", "15"));
						paramstb2.add(new BasicNameValuePair("prePageNo", "1"));
						httpPostborder2.setEntity(new UrlEncodedFormEntity(paramstb2, "UTF-8"));
						// 发送请求
						HttpResponse httpresponsetborder2 = httpClient.execute(httpPostborder2);
						HttpEntity entitytborder2 = httpresponsetborder2.getEntity();
						String bodytborder2 = EntityUtils.toString(entitytborder2);
						bodytborder2 = bodytborder2.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1")
								.replaceAll("^((\r\n)|\n)", "");
						for (int j = 0; j < 15; j++) {
							JSONObject json2 = JSON.parseObject(bodytborder2);// json字符串转换成jsonobject对象
							JSONArray mainOrders = json2.getJSONArray("mainOrders");
							int mainOrderssize = mainOrders.size();
							if (mainOrderssize == 15) {
								JSONObject orderInfo = mainOrders.getJSONObject(j).getJSONObject("orderInfo");
								String orderid = orderInfo.getString("id");// 订单号
								String createDay = orderInfo.getString("createDay");// 订单日期
								JSONObject payInfo = mainOrders.getJSONObject(j).getJSONObject("payInfo");
								String actualFee = payInfo.getString("actualFee");// 订单金额
								// System.out.println(orderid+"---"+actualFee+"---"+createDay);
								taobaoOrder = new TaobaoOrder();
								taobaoOrder.setOrderId(orderid);
								taobaoOrder.setOrderDate(createDay);
								taobaoOrder.setOrderMoney(actualFee);
								listorder.add(taobaoOrder);
							} else {
								if (j == 0) {
									for (int a = 0; a < mainOrderssize; a++) {
										JSONObject orderInfo = mainOrders.getJSONObject(a).getJSONObject("orderInfo");
										String orderid = orderInfo.getString("id");// 订单号
										String createDay = orderInfo.getString("createDay");// 订单日期
										JSONObject payInfo = mainOrders.getJSONObject(a).getJSONObject("payInfo");
										String actualFee = payInfo.getString("actualFee");// 订单金额
										System.out.println(orderid + "---" + actualFee + "---" + createDay);
										taobaoOrder = new TaobaoOrder();
										taobaoOrder.setOrderId(orderid);
										taobaoOrder.setOrderDate(createDay);
										taobaoOrder.setOrderMoney(actualFee);
										listorder.add(taobaoOrder);
									}
								}
							}
						}
					}
					BigDecimal totalmoney = BigDecimal.ZERO;
					int bs = 0;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					// 过去一年
					c.setTime(new Date());
					c.add(Calendar.YEAR, -1);
					Date y = c.getTime();
					String gqyear = format.format(y);
					String DateBegin = format.format(new Date());
					// 当前时间
					String[] strDateBegin = DateBegin.split("-");
					// 过去一年时间
					String[] strDateEnd = gqyear.split("-");
					int tempDateBegin = Integer.parseInt(strDateBegin[0] + strDateBegin[1] + strDateBegin[2]);
					int tempDateEnd = Integer.parseInt(strDateEnd[0] + strDateEnd[1] + strDateEnd[2]);
					for (int i = 0; i < listorder.size(); i++) {
						String[] strDate = listorder.get(i).getOrderDate().split("-");
						int tempDate = Integer.parseInt(strDate[0] + strDate[1] + strDate[2]);
						if ((tempDate <= tempDateBegin && tempDate >= tempDateEnd)) {
							totalmoney = totalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 总金额
							bs = bs + 1;// 笔数
						}
					}
					BigDecimal mm = new BigDecimal(bs);
					// 平均金额
					BigDecimal baverageAmount = totalmoney.divide(mm, 2, BigDecimal.ROUND_HALF_EVEN);
					tbtotalamount = totalmoney.toString();
					tbaverageamount = baverageAmount.toString();

					// 获取淘宝信息
					HttpGet hgxy = new HttpGet("https://rate.taobao.com/myRate.htm");
					headerWrapper(hgxy);
					HttpResponse httpresponsexy = httpClient.execute(hgxy);
					HttpEntity entityxy = httpresponsexy.getEntity();
					String bodyxy = EntityUtils.toString(entityxy);
					Document xy = Jsoup.parse(bodyxy);
					if (xy == null) {
						return null;
					}
					// 信用积分
					tbcreditscore = xy.select("h4[class=tb-rate-ico-bg ico-buyer]>a").get(0).html();
					// 好评率
					tbfeedbackrate = xy.select("p[class=rate-summary]>strong").get(0).html();

					// 获取淘气值
					HttpPost httpPosttqz = new HttpPost("https://vip.taobao.com/ajax/getGoldUser.do");
					headerWrapper(httpPosttqz);
					httpPosttqz.setHeader("Referer",
							"https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");
					// 发送请求
					HttpResponse httpresponsetqz = httpClient.execute(httpPosttqz);
					HttpEntity entitytqz = httpresponsetqz.getEntity();
					String bodytqz = EntityUtils.toString(entitytqz);
					bodytqz = bodytqz.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
					System.out.print(bodytqz);
					JSONObject jsontqz = JSON.parseObject(bodytqz);// json字符串转换成jsonobject对象
					JSONObject datatqz = jsontqz.getJSONObject("data");
					tbcreditstanding = datatqz.getString("taoScore");// 信用度（淘气值）
					if (Integer.parseInt(tbcreditstanding) <= 1000) {
						tbcreditlevel = "普通会员";
					} else
						if (Integer.parseInt(tbcreditstanding) > 1000 && Integer.parseInt(tbcreditstanding) <= 2500) {
						tbcreditlevel = "超级会员";
					} else if (Integer.parseInt(tbcreditstanding) > 2500) {
						tbcreditlevel = "APASS";
					}

					// 淘宝收货人地址
					HttpGet hgaddr = new HttpGet("http://member1.taobao.com/member/fresh/deliver_address.htm");
					headerWrapper(hgaddr);
					HttpResponse httpresponseaddr = httpClient.execute(hgaddr);
					HttpEntity entityaddr = httpresponseaddr.getEntity();
					String bodyaddr = EntityUtils.toString(entityaddr);
					Document doc = Jsoup.parse(bodyaddr);
					if (doc == null) {
						return null;
					}
					Elements elems = doc.select("tr[class=thead-tbl-address]");// 地址
					Elements elems2 = doc.select("tr[class=thead-tbl-address  address-default]");// 默认地址
					if (elems.isEmpty()) {
						return null;
					}
					if (elems2.isEmpty()) {
						return null;
					}
					String text;// 默认收货地址
					String text2;// 其余收货地址
					Taobaoaddress ta = new Taobaoaddress();
					List<Taobaoaddress> tblistaddress = new ArrayList<Taobaoaddress>();
					text = elems2.get(0).html();
					text = text.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", ""); // 正则表达式过滤标签
					text = text.replaceAll("[(/>)<]", "");
					text = text.replaceAll("\n", "|");
					text = text.replaceAll(" ", "");
					String s[] = text.split("\\|");
					ta.setConsignee(s[0]);
					ta.setAreaname(s[1]);
					ta.setAddress(s[2]);
					ta.setZip(s[3]);
					ta.setPhonenumber(s[4]);
					tblistaddress.add(ta);
					for (int i = 0; i < elems.size(); i++) {
						ta = new Taobaoaddress();
						text2 = elems.get(i).html();
						text2 = text2.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", ""); // 正则表达式过滤标签
						text2 = text2.replaceAll("[(/>)<]", "");
						text2 = text2.replaceAll("\n", "|");
						text2 = text2.replaceAll(" ", "");
						String[] s2 = text2.split("\\|");
						ta.setConsignee(s2[0]);
						ta.setAreaname(s2[1]);
						ta.setAddress(s2[2]);
						ta.setZip(s2[3]);
						ta.setPhonenumber(s2[4]);
						tblistaddress.add(ta);
					}

					BigDecimal jototalmoney = BigDecimal.ZERO;
					BigDecimal jttotalmoney = BigDecimal.ZERO;
					BigDecimal jstotalmoney = BigDecimal.ZERO;
					int oybs = 0;
					int tybs = 0;
					int sybs = 0;
					// 过去一月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -1);
					Date m = c.getTime();
					String mon = format.format(m);
					// 过去一个月时间
					String[] mDateEnd = mon.split("-");
					int mtempDateEnd = Integer.parseInt(mDateEnd[0] + mDateEnd[1]);
					String ymonth = mDateEnd[0] + "-" + mDateEnd[1];

					// 过去2月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -2);
					Date m2 = c.getTime();
					String mon2 = format.format(m2);
					// 过去近2个月时间
					String[] mDateEnd2 = mon2.split("-");
					int mtempDateEnd2 = Integer.parseInt(mDateEnd2[0] + mDateEnd2[1]);
					String tmonth = mDateEnd2[0] + "-" + mDateEnd2[1];

					// 过去3月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -3);
					Date m3 = c.getTime();
					String mon3 = format.format(m3);
					// 过去近3个月时间
					String[] mDateEnd3 = mon3.split("-");
					int mtempDateEnd3 = Integer.parseInt(mDateEnd3[0] + mDateEnd3[1]);
					String smonth = mDateEnd3[0] + "-" + mDateEnd3[1];
					for (int i = 0; i < listorder.size(); i++) {
						String[] strDate = listorder.get(i).getOrderDate().split("-");
						int tempDate = Integer.parseInt(strDate[0] + strDate[1]);
						if (tempDate == mtempDateEnd) {
							jototalmoney = jototalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近一个月月消费总金额
							oybs = oybs + 1;
						} else if (tempDate == mtempDateEnd2) {
							jttotalmoney = jttotalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近2个月月消费总金额
							tybs = tybs + 1;
						} else if (tempDate == mtempDateEnd3) {
							jstotalmoney = jstotalmoney.add(new BigDecimal(listorder.get(i).getOrderMoney()));// 近3个月月消费总金额
							sybs = sybs + 1;
						}
					}

					// *******************将淘宝信息入库start******************************
					Taobaoinfo taobaoinfo = new Taobaoinfo();
					taobaoinfo.setTbid(tbuserid);
					taobaoinfo.setUsername(tbusername);
					taobaoinfo.setAge(tbage);
					taobaoinfo.setGender(tbgender);
					taobaoinfo.setBirthdate(tbbirthdate);
					taobaoinfo.setIdtype(tbidtype);
					taobaoinfo.setIdnumber(tbidnumber);
					taobaoinfo.setCreditscore(tbcreditscore);
					taobaoinfo.setFeedbackrate(tbfeedbackrate);
					taobaoinfo.setCreditlevel(tbcreditlevel);
					taobaoinfo.setCreditstanding(tbcreditstanding);
					taobaoinfo.setTotalamount(tbtotalamount);
					taobaoinfo.setAverageamount(tbaverageamount);
					taobaoinfo.setDisputes(tbdisputes);
					taobaoinfo.setDisputesnumber(tbdisputesnumber);
					taobaoinfo.setNearlyonemonth(ymonth);
					taobaoinfo.setNearlyoneconsumetotal(String.valueOf(oybs));
					taobaoinfo.setNearlyoneconsumemoney(jototalmoney.toString());
					taobaoinfo.setNearlytwomonth(tmonth);
					taobaoinfo.setNearlytwoconsumetotal(String.valueOf(tybs));
					taobaoinfo.setNearlytwoconsumemoney(jttotalmoney.toString());
					taobaoinfo.setNearlythreemonth(smonth);
					taobaoinfo.setNearlythreeconsumetotal(String.valueOf(sybs));
					taobaoinfo.setNearlythreeconsumemoney(jstotalmoney.toString());
					taobaoinfo.setLoaddate(new Date());
					taobaoinfoService.addtaobaoinfo(taobaoinfo);

					Taobaoaddress taobaoaddress = null;
					for (int i = 0; i < tblistaddress.size(); i++) {
						taobaoaddress = new Taobaoaddress();
						taobaoaddress.setTid("t" + i + username
								+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis())));// 规则是t+i+用户名+时间戳（确保唯一ID）
						taobaoaddress.setTbid(tbuserid);
						taobaoaddress.setConsignee(tblistaddress.get(i).getConsignee());
						taobaoaddress.setAreaname(tblistaddress.get(i).getAreaname());
						taobaoaddress.setAddress(tblistaddress.get(i).getAddress());
						taobaoaddress.setZip(tblistaddress.get(i).getZip());
						taobaoaddress.setPhonenumber(tblistaddress.get(i).getPhonenumber());
						taobaoaddress.setLoaddate(new Date());
						taobaoinfoService.addtaobaoaddress(taobaoaddress);
					}
					// *******************将淘宝信息入库end******************************
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("tbuserid", tbuserid);
		return "tz";
	}

	/**
	 * 京东
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/jdlist")
	public String jdlist(HttpServletRequest request) {
		StampToDate stampToDate = new StampToDate();
		String tbuserid = request.getParameter("tbuserid2");// 淘宝id
		String username = request.getParameter("jdusername");
		String userpwd = request.getParameter("jduserpwd");
		String jdcheckbox = request.getParameter("jdcheckbox");// 是否授权(1授权/0不授权)
		String jduserid = "";// 京东id
		if ((!"".equals(username)) && (!"".equals(userpwd))) {
			jduserid = "jd_" + username + "_" + MD5Util.string2MD5(userpwd) + "_"
					+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis()));// 京东id
			User user = new User();
			user.setUserid(jduserid);
			user.setUsername(username);
			user.setUserpwd(MD5Util.string2MD5(userpwd));
			user.setFlag(jdcheckbox);
			user.setLoaddate(new Date());
			userService.addUser(user);

			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				Map map = getParams();
				HttpPost httpPost = new HttpPost("https://passport.jd.com/uc/loginService");
				headerWrapper(httpPost);
				httpPost.setHeader("accept-Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				params.add(new BasicNameValuePair("loginname", username));
				params.add(new BasicNameValuePair("nloginpwd", userpwd));
				Iterator it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString();
					String value = map.get(key).toString();
					params.add(new BasicNameValuePair(key, value));
				}
				Thread.sleep(3000);// 等待3秒
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

				// 发送请求
				HttpResponse httpresponse = httpClient.execute(httpPost);
				HttpEntity entity = httpresponse.getEntity();
				String body = EntityUtils.toString(entity);
				System.out.println(body);
				if (body.indexOf("success") != -1) {// 判断请求是否成功，成功则继续执行
					String jdzsname = ""; // 真实姓名
					String jdage = ""; // 年龄
					String jdsex = ""; // 性别
					String jdbirthday = ""; // 出生年月
					String jdctype = ""; // 证件类型
					String jdcnumber = ""; // 证件号码
					String jdname = ""; // 会员名
					String ulevel = ""; // 会员等级
					String xylevel = ""; // 信用等级
					String ued = ""; // 白条额度
					String sxzed = ""; // 授信总额度
					String fqje = ""; // 当前可分期金额
					String yyzed = ""; // 已用总额度

					HttpGet hg1 = new HttpGet("https://home.jd.com");
					headerWrapper(hg1);
					HttpResponse httpresponse1 = httpClient.execute(hg1);
					HttpEntity entity1 = httpresponse1.getEntity();
					String body1 = EntityUtils.toString(entity1);
					Document xy = Jsoup.parse(body1);
					ulevel = xy.select("div[id=home-u-level]").get(0).attr("data-id-u-levelname");
					ued = xy.select("span[class=baitiao-limit]").get(0).html();
					// 白条额度
					HttpGet hged = new HttpGet("https://baitiao.jd.com/v3/ious/getBtPrivilege");
					headerWrapper(hged);
					HttpResponse httpresponseed = httpClient.execute(hged);
					HttpEntity entityed = httpresponseed.getEntity();
					String bodyed = EntityUtils.toString(entityed);
					JSONObject json = JSON.parseObject(bodyed);
					JSONObject jj = (JSONObject) json.get("result");
					String code = jj.getString("code");// 获取代码 00000状态成功 其余则不成功
					if ("00000".equals(code)) {
						sxzed = json.getString("creditLimit");
						yyzed = json.getString("outstandingAmount");
						fqje = json.getString("availableLimit");
					} else {
						sxzed = "无";
						fqje = "无";
						yyzed = "无";
					}

					// 个人信息
					HttpGet hguser = new HttpGet("http://i.jd.com/user/userinfo/showBaseInfo.action");
					headerWrapper(hguser);
					HttpResponse httpresponseuser = httpClient.execute(hguser);
					HttpEntity entityuser = httpresponseuser.getEntity();
					String bodyuser = EntityUtils.toString(entityuser);
					Document personInfo = Jsoup.parse(bodyuser);
					String rgex = "var originalBirthday='(.*?)'.split";
					jdbirthday = getSubUtilSimple(bodyuser, rgex);
					if ("".equals(jdbirthday)) {// 判断jdbirthday为空则年龄为空
						jdage = "";
					} else {
						if (!jdbirthday.contains("后")) {
							Calendar cal = Calendar.getInstance();
							int year = cal.get(Calendar.YEAR);
							int a = Integer.parseInt(jdbirthday.split("-")[0]);
							int ag = year - a;
							jdage = String.valueOf(ag);
						} else {
							jdage = "";
						}
					}
					if (bodyuser.contains("0==0")) {
						jdsex = "男";
					} else if (bodyuser.contains("1==1")) {
						jdsex = "女";
					} else {
						jdsex = "保密";
					}
					jdname = personInfo.select("div[id=aliasBefore]>strong").get(0).html();

					// 获取名称、身份证
					HttpGet hgjdInfo = new HttpGet("https://authpay.jd.com/auth/toAuthSuccessPage.action");
					headerWrapper(hgjdInfo);
					HttpResponse httpresponsejdInfo = httpClient.execute(hgjdInfo);
					HttpEntity entityjdInfo = httpresponsejdInfo.getEntity();
					String bodyjdInfo = EntityUtils.toString(entityjdInfo);
					Document jdInfo = Jsoup.parse(bodyjdInfo);
					String jdns = jdInfo.select("div[class=name]").get(0).html();
					jdzsname = jdns.split("（")[0];
					jdctype = "身份证";
					jdcnumber = jdns.split("（")[1];
					jdcnumber = jdcnumber.substring(0, jdcnumber.length() - 1);

					// 获取小白信用
					HttpGet hgjdxb = new HttpGet("https://i.jd.com/user/info");
					headerWrapper(hgjdxb);
					HttpResponse httpresponsejdxb = httpClient.execute(hgjdxb);
					HttpEntity entityjdxb = httpresponsejdxb.getEntity();
					String bodyjdxb = EntityUtils.toString(entityjdxb);
					Document jdxb = Jsoup.parse(bodyjdxb);
					xylevel = jdxb.select("div[clstag=pageclick|keycount|201602251|4]>a").get(0).html();

					// //声明list集合来存储用户信息
					// List<JdUser> jdlistuser = new ArrayList<JdUser>();
					// JdUser jdUser = new JdUser();
					// jdUser.setUsername(jdzsname);
					// jdUser.setAge(jdage);
					// jdUser.setGender(jdsex);
					// jdUser.setBirthDate(jdbirthday);
					// jdUser.setIdtype(jdctype);
					// jdUser.setIdnumber(jdcnumber);
					// jdUser.setJdname(jdname);
					// jdUser.setUlevel(ulevel);
					// jdlistuser.add(jdUser);
					// //用户信息
					// request.setAttribute("jdlistuser",jdlistuser);
					//
					// //声明list集合来存储白条信息
					// List<Jdbaitiao> jdlistbt = new ArrayList<Jdbaitiao>();
					// Jdbaitiao jdbaitiao = new Jdbaitiao();
					// jdbaitiao.setXylevel(xylevel);
					// jdbaitiao.setSxzed(sxzed);
					// jdbaitiao.setFqje(fqje);
					// jdbaitiao.setYyzed(yyzed);
					// jdlistbt.add(jdbaitiao);
					// //白条信息
					// request.setAttribute("jdlistbt",jdlistbt);

					// 收货地址
					HttpPost hgjdaddress = new HttpPost("https://easybuy.jd.com/address/getEasyBuyList.action");
					headerWrapper(hgjdaddress);
					hgjdaddress.setHeader("Referer", "https://order.jd.com/center/list.action");
					// 发送请求
					HttpResponse httpresponsejdaddress = httpClient.execute(hgjdaddress);
					HttpEntity entityjdaddress = httpresponsejdaddress.getEntity();
					String bodyjdaddress = EntityUtils.toString(entityjdaddress);
					Document jdaddress = Jsoup.parse(bodyjdaddress);
					Elements elemsjdaddress = jdaddress.select("div[class=item-lcol]");// 地址
					String addresstext = "";
					Jingdongaddress ta = new Jingdongaddress();
					List<Jingdongaddress> jdlistaddress = new ArrayList<Jingdongaddress>();
					for (int i = 0; i < elemsjdaddress.size(); i++) {
						ta = new Jingdongaddress();
						addresstext = elemsjdaddress.get(i).html();
						Document jd2 = Jsoup.parse(addresstext);
						Elements elemsjdaddress2 = jd2.select("div[class=item]>div[class=fl]");
						ta.setConsignee(elemsjdaddress2.get(0).html());
						ta.setAreaname(elemsjdaddress2.get(1).html());
						ta.setAddress(elemsjdaddress2.get(2).html());
						ta.setPhone(elemsjdaddress2.get(3).html());
						ta.setPhonenumber(elemsjdaddress2.get(4).html());
						ta.setEmail(elemsjdaddress2.get(5).html());
						jdlistaddress.add(ta);
					}
					// 收获地址信息
					request.setAttribute("jdlistaddress", jdlistaddress);

					// 订单
					HttpGet hgjdorder = new HttpGet("https://order.jd.com/center/list.action");
					headerWrapper(hgjdorder);
					HttpResponse httpresponsejdorder = httpClient.execute(hgjdorder);
					HttpEntity entityjdorder = httpresponsejdorder.getEntity();
					String bodyjdorder = EntityUtils.toString(entityjdorder);
					String rgex2 = "span>    <span class=\"text\">共(.*?)页<";
					String jdtotal = "";// 近三个月订单总页数
					jdtotal = getSubUtilSimple(bodyjdorder, rgex2);

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					BigDecimal omoney = BigDecimal.ZERO;
					BigDecimal tmoney = BigDecimal.ZERO;
					BigDecimal smoney = BigDecimal.ZERO;
					int oybs = 0;
					int tybs = 0;
					int sybs = 0;
					// 过去一月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -1);
					Date m = c.getTime();
					String mon = format.format(m);
					// 过去一个月时间
					String[] mDateEnd = mon.split("-");
					int mtempDateEnd = Integer.parseInt(mDateEnd[0] + mDateEnd[1]);
					String ymonth = mDateEnd[0] + "-" + mDateEnd[1];
					// 过去2月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -2);
					Date m2 = c.getTime();
					String mon2 = format.format(m2);
					// 过去近2个月时间
					String[] mDateEnd2 = mon2.split("-");
					int mtempDateEnd2 = Integer.parseInt(mDateEnd2[0] + mDateEnd2[1]);
					String tmonth = mDateEnd2[0] + "-" + mDateEnd2[1];
					// 过去3月
					c.setTime(new Date());
					c.add(Calendar.MONTH, -3);
					Date m3 = c.getTime();
					String mon3 = format.format(m3);
					// 过去近3个月时间
					String[] mDateEnd3 = mon3.split("-");
					int mtempDateEnd3 = Integer.parseInt(mDateEnd3[0] + mDateEnd3[1]);
					String smonth = mDateEnd3[0] + "-" + mDateEnd3[1];
					// 声明list集合来存储订单消费信息
					// List<JdOrder> jdlistorderconsume = new
					// ArrayList<JdOrder>();
					// JdOrder jdOrderconsume1 = new JdOrder();
					// JdOrder jdOrderconsume2 = new JdOrder();
					// JdOrder jdOrderconsume3 = new JdOrder();

					if (jdtotal != null && jdtotal.length() != 0) {
						for (int i = 0; i < Integer.parseInt(jdtotal); i++) {
							HttpGet hgjdorder2 = new HttpGet(
									"https://order.jd.com/center/list.action?search=0&d=1&s=4096&page=" + (i + 1));
							headerWrapper(hgjdorder2);
							HttpResponse httpresponsejdorder2 = httpClient.execute(hgjdorder2);
							HttpEntity entityjdorder2 = httpresponsejdorder2.getEntity();
							String bodyjdorder2 = EntityUtils.toString(entityjdorder2);
							Document jdorder = Jsoup.parse(bodyjdorder2);
							Elements elemsorders = jdorder.select("table");// 订单
							Elements links = elemsorders.select("tbody>tr>td>span>a");
							String orderxx = "";
							for (int j = 0; j < links.size(); j++) {
								orderxx = "https:" + links.get(j).attr("href");
								HttpGet hgjdorderxx = new HttpGet(orderxx);
								headerWrapper(hgjdorderxx);
								HttpResponse httpresponsejdorderxx = httpClient.execute(hgjdorderxx);
								HttpEntity entityjdorderxx = httpresponsejdorderxx.getEntity();
								String bodyjdorderxx = EntityUtils.toString(entityjdorderxx);
								Document jdorderxx = Jsoup.parse(bodyjdorderxx);
								Elements wc = jdorderxx.select("div[class=state-lcol]");
								String var = wc.select("h3").text();
								if ("完成".equals(var)) {
									Elements moneys = jdorderxx.select("span[class=txt count]");
									String money = moneys.text();
									Elements orderdate = jdorderxx.select("div[class=dd]>div[class=item]");

									Elements orderdatez = orderdate.select("div[class=info-rcol]");
									String date = orderdatez.get(5).text();

									String[] strDates = date.split("-");
									int tempDate = Integer.parseInt(strDates[0] + strDates[1]);
									if (tempDate == mtempDateEnd) {
										omoney = omoney.add(new BigDecimal(money.replace("¥", "")));// 近一个月月消费总金额
										oybs = oybs + 1;
									} else if (tempDate == mtempDateEnd2) {
										tmoney = tmoney.add(new BigDecimal(money.replace("¥", "")));// 近2个月月消费总金额
										tybs = tybs + 1;
									} else if (tempDate == mtempDateEnd3) {
										smoney = smoney.add(new BigDecimal(money.replace("¥", "")));// 近3个月月消费总金额
										sybs = sybs + 1;
									}
								}
							}
						}
					}
					// jdOrderconsume1.setMonth(ymonth);
					// jdOrderconsume1.setConsumeNum(String.valueOf(oybs));
					// jdOrderconsume1.setConsumeMoney(omoney.toString());
					//
					// jdOrderconsume2.setMonth(tmonth);
					// jdOrderconsume2.setConsumeNum(String.valueOf(tybs));
					// jdOrderconsume2.setConsumeMoney(tmoney.toString());
					//
					// jdOrderconsume3.setMonth(smonth);
					// jdOrderconsume3.setConsumeNum(String.valueOf(sybs));
					// jdOrderconsume3.setConsumeMoney(smoney.toString());
					// jdlistorderconsume.add(jdOrderconsume1);
					// jdlistorderconsume.add(jdOrderconsume2);
					// jdlistorderconsume.add(jdOrderconsume3);
					// //订单消费信息
					// request.setAttribute("jdlistorderconsume",jdlistorderconsume);

					// *******************将京东信息入库start******************************
					Jingdonginfo jingdonginfo = new Jingdonginfo();
					jingdonginfo.setJdid(jduserid);
					jingdonginfo.setUsername(jdzsname);
					jingdonginfo.setAge(jdage);
					jingdonginfo.setGender(jdsex);
					jingdonginfo.setBirthdate(jdbirthday);
					jingdonginfo.setIdtype(jdctype);
					jingdonginfo.setIdnumber(jdcnumber);
					jingdonginfo.setJdname(jdname);
					jingdonginfo.setUlevel(ulevel);
					jingdonginfo.setXylevel(xylevel);
					jingdonginfo.setSxzed(sxzed);
					jingdonginfo.setFqje(fqje);
					jingdonginfo.setYyzed(yyzed);
					jingdonginfo.setNearlyonemonth(ymonth);
					jingdonginfo.setNearlyoneconsumetotal(String.valueOf(oybs));
					jingdonginfo.setNearlyoneconsumemoney(omoney.toString());
					jingdonginfo.setNearlytwomonth(tmonth);
					jingdonginfo.setNearlytwoconsumetotal(String.valueOf(tybs));
					jingdonginfo.setNearlytwoconsumemoney(tmoney.toString());
					jingdonginfo.setNearlythreemonth(smonth);
					jingdonginfo.setNearlythreeconsumetotal(String.valueOf(sybs));
					jingdonginfo.setNearlythreeconsumemoney(smoney.toString());
					jingdonginfo.setLoaddate(new Date());
					jingdonginfoService.addjingdonginfo(jingdonginfo);

					Jingdongaddress jingdongaddress = null;
					for (int i = 0; i < jdlistaddress.size(); i++) {
						jingdongaddress = new Jingdongaddress();
						jingdongaddress.setTid("t" + i + username
								+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis())));// 规则是t+i+用户名+时间戳（确保唯一ID）
						jingdongaddress.setJdid(jduserid);
						jingdongaddress.setConsignee(jdlistaddress.get(i).getConsignee());
						jingdongaddress.setAreaname(jdlistaddress.get(i).getAreaname());
						jingdongaddress.setAddress(jdlistaddress.get(i).getAddress());
						jingdongaddress.setPhone(jdlistaddress.get(i).getPhone());
						jingdongaddress.setPhonenumber(jdlistaddress.get(i).getPhonenumber());
						jingdongaddress.setEmail(jdlistaddress.get(i).getEmail());
						jingdongaddress.setLoaddate(new Date());
						jingdonginfoService.addjingdongaddress(jingdongaddress);
					}
					// *******************将京东信息入库end******************************
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!"null".equals(tbuserid) && tbuserid.length() > 4) {// 如果用户没有淘宝，则tbuserid为空
			request.setAttribute("tbuserid", tbuserid);
		}
		request.setAttribute("jduserid", jduserid);
		return "tz2";
		// return "login";
	}

	/**
	 * 获取京东参数信息
	 * 
	 * @return
	 */
	public static Map<String, String> getParams() {
		Map<String, String> map = new HashMap<String, String>();
		String str = getText("https://passport.jd.com/uc/login");
		String strs1[] = str.split("name=\"uuid\" value=\"");
		String strs2[] = strs1[1].split("\"/>");
		String uuid = strs2[0];

		String s1[] = str.split("name=\"sa_token\" value=\"");
		String s2[] = s1[1].split("\"/>");
		String sa_token = s2[0];

		String eid = "";
		String s3[] = str.split("name=\"eid\" value=\"");
		if (s3[0].indexOf("HTML") != -1) {// 判断eid为空值
			return null;
		} else {
			eid = "";
		}

		String fp = "";
		String s5[] = str.split("name=\"fp\" value=\"");
		if (s5[0].indexOf("HTML") != -1) {// 判断fp为空值
			return null;
		} else {
			fp = "";
		}

		String s7[] = str.split("id=\"token\" value=\"");
		String s8[] = s7[1].split("\"/>");
		String s99[] = s8[0].split("\"");
		String _t = s99[0];

		String s9[] = str.split("id=\"loginType\" value=\"");
		String s10[] = s9[1].split("\"/>");
		String s98[] = s10[0].split("\"");
		String loginType = s98[0];

		String s11[] = str.split("id=\"pubKey\" value=\"");
		String s12[] = s11[1].split("\"/>");
		String pubKey = s12[0];

		String s13[] = str.split("input type=\"hidden\" name=\"");
		String s14[] = s13[6].split("\"/>");
		String s97[] = s14[0].split("\" value=\"");
		String s97key = s97[0];
		String s97value = s97[1];

		map.put("uuid", uuid);
		map.put("sa_token", sa_token);
		map.put("eid", eid);
		map.put("fp", fp);
		map.put("_t", _t);
		map.put("loginType", loginType);
		map.put("pubKey", pubKey);
		map.put(s97key, s97value);
		return map;
	}

	private static String getText(String redirectLocation) {
		HttpGet httpget = new HttpGet(redirectLocation);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = httpClient.execute(httpget, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			responseBody = null;
		} finally {
			httpget.abort();
			// httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}

	/**
	 * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
	 * 
	 * @param soap
	 * @param rgex
	 * @return
	 */
	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	public static JSONObject String2Json(String string) {
		if (string == null) {
			return null;
		}
		String jsonString = string.trim();
		if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
			try {
				return JSON.parseObject(jsonString);
			} catch (java.lang.Exception e) {
				return null;
			}
		} else if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
			try {
				return JSON.parseArray(jsonString).getJSONObject(0);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	//导出pdf
	public static void importPDF(){
		
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ZgbjyidonginfoService getZgbjyidonginfoService() {
		return zgbjyidonginfoService;
	}

	public void setZgbjyidonginfoService(ZgbjyidonginfoService zgbjyidonginfoService) {
		this.zgbjyidonginfoService = zgbjyidonginfoService;
	}

	public JingdonginfoService getJingdonginfoService() {
		return jingdonginfoService;
	}

	public void setJingdonginfoService(JingdonginfoService jingdonginfoService) {
		this.jingdonginfoService = jingdonginfoService;
	}

	public ChinaUnicomInfoService getChinaUnicomInfoService() {
		return chinaUnicomInfoService;
	}

	public void setChinaUnicomInfoService(ChinaUnicomInfoService chinaUnicomInfoService) {
		this.chinaUnicomInfoService = chinaUnicomInfoService;
	}

	public TaobaoinfoService getTaobaoinfoService() {
		return taobaoinfoService;
	}

	public void setTaobaoinfoService(TaobaoinfoService taobaoinfoService) {
		this.taobaoinfoService = taobaoinfoService;
	}

	public PerdishonestyService getPerdishonestyService() {
		return perdishonestyService;
	}

	public void setPerdishonestyService(PerdishonestyService perdishonestyService) {
		this.perdishonestyService = perdishonestyService;
	}

	public AppuserinfoService getAppuserinfoService() {
		return appuserinfoService;
	}

	public void setAppuserinfoService(AppuserinfoService appuserinfoService) {
		this.appuserinfoService = appuserinfoService;
	}

}
