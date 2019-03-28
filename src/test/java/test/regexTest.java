package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sy.model.SbUser;

import java.io.*;
import java.util.*;

/**

/**
 * @program: zxshow
 *
 * @description: 正则测试类
 *
 * @author: ZhangChi
 *
 * @create: 2019-03-12 11:05
 **/
/*@Slf4j*/
public class regexTest {
    @Test
    public void test1() throws IOException {
        String str = "<!-- <font color=\"red\"></font> -->\n" +
                "<form id=\"printForm\" name=\"printForm\" action=\"IndUserNewInfoAction!userInfoSearch1.action\" target=\"_blank\" method=\"post\">\n" +
                "\t<input type=\"hidden\" name=\"idCode\" value=\"\" id=\"idCode\"/>\n" +
                "\t<input type=\"hidden\" name=\"name\" value=\"\" id=\"name\"/>\n" +
                "\t\n" +
                "\t<input type=\"hidden\" name=\"type\" value=\"20\" id=\"type\"/>\n" +
                "<table width=\"93%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#cccccc\">\n" +
                "  <tr align=\"left\" bgcolor=\"#FFFFFF\">\n" +
                "    <td height=\"25\" colspan=\"10\">\n" +
                "    \t\t\t单位名称： 北京思想天下教育科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t\t\t\t统一社会信用代码（组织机构代码）：91110116074183436K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \n" +
                "    \t\t\t社会保险登记号：91110116074183436K &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \n" +
                "    \t\t\t所属区县：\t\t怀柔区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n" +
                "  \n" +
                "  </tr>\n" +
                "  </table>\n" +
                "  <table width=\"93%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#cccccc\">\n" +
                "  \n" +
                "    <tr align=\"center\">\n" +
                "    <td width=\"25%\" height=\"25\" bgcolor=\"#FFFFFF\">*参加险种</td>\n" +
                "  <td colspan=\"4\" bgcolor=\"#FFFFFF\" align=\"left\">\n" +
                "  \t\n" +
                "  \t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t\t\n" +
                "  \t\t[养老缴费\n" +
                "  \t\t (正常)缴费]<br/>\n" +
                "  \t\n" +
                "  \t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t\t\n" +
                "  \t\t[失业缴费\n" +
                "  \t\t (正常)缴费]<br/>\n" +
                "  \t\n" +
                "  \t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t\t\n" +
                "  \t\t[工伤缴费\n" +
                "  \t\t (正常)缴费]<br/>\n" +
                "  \t\n" +
                "  \t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t\t\n" +
                "  \t\t[生育缴费\n" +
                "  \t\t (正常)缴费]<br/>\n" +
                "  \t\n" +
                "  \t \n" +
                "  \t \t  \t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "  \t \t\n" +
                "  \t \t[医保缴费 (正常)缴费]\n" +
                "  \t \n" +
                "  \t \n" +
                "  </td>\n" +
                "  <td width=\"25%\" height=\"25\" bgcolor=\"#FFFFFF\" id=\"photo\">\n" +
                "  \t<img src=\"/csibiz/indinfo/search/ind/indNewInfoSearchAction!showPhoto\"\n" +
                "  \twidth=\"130\" height=\"160\" onerror=\"document.getElementById('photo').innerHTML='暂无照片'\"/>\n" +
                "  </td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*姓 名 </td>\n" +
                "  \t<td colspan=\"3\" bgcolor=\"#FFFFFF\">赵腾</td>\n" +
                "    <td width=\"22%\" bgcolor=\"#FFFFFF\">*公民身份号码<br>（社会保障号码）</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">130429199103108458</td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*性 别 </td>\n" +
                "  \t<td colspan=\"3\" bgcolor=\"#FFFFFF\">男</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*出生日期 </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">19910310  </td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*民 族 </td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\">汉族</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*国家/地区 </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">中国</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*个人身份 </td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\">工人</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*参加工作日期 </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">20181101</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">户口所在区县街乡</td>\n" +
                "  \t<td colspan=\"3\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*户口性质</td> <td colspan=\"1\" bgcolor=\"#FFFFFF\">农村（农业户口）</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*户口所在地地址 </td>\n" +
                "  \t<td colspan=\"3\" bgcolor=\"#FFFFFF\">河北省邯郸市永年县临洺关镇河北铺村10区100号</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*户口所在地邮政编码</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">057150</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*居住地(联系)地址 </td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\">北京市朝阳去崔各庄镇奶东村1排16号</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*居住地（联系）邮政编码 </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">100012</td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">选择邮寄社会保险对账单地址</td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    <td bgcolor=\"#FFFFFF\">对账单邮政编码</td>\n" +
                "    <td bgcolor=\"#FFFFFF\"></td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*获取对账单方式 </td>\n" +
                "    <td width=\"8%\" bgcolor=\"#FFFFFF\">网上查询</td>\n" +
                "    <td width=\"12%\" bgcolor=\"#FFFFFF\">电子邮件地址 </td>\n" +
                "    <td width=\"11%\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*文化程度</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">大学</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*参保人电话 </td>\n" +
                "    <td width=\"8%\" bgcolor=\"#FFFFFF\">17736189175</td>\n" +
                "    <td width=\"12%\" bgcolor=\"#FFFFFF\">参保人手机 </td>\n" +
                "    <td width=\"11%\" bgcolor=\"#FFFFFF\">17736189175</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*申报月均工资收入（元） </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">7000</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*证件类型 </td>\n" +
                "  \t<td colspan=\"3\"  bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*证件号码 </td>\n" +
                "    <td bgcolor=\"#FFFFFF\">  </td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*委托代发银行名称 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> 建设银行</td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*委托代发银行账号 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> 6217000010132174841</td>\n" +
                "  </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*缴费人员类别 </td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\">外埠农村劳动力</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">*医疗参保人员类别</td>\n" +
                "    <td bgcolor=\"#FFFFFF\">在职职工</td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">离退休类别 </td>\n" +
                "  <td colspan=\"3\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    <td bgcolor=\"#FFFFFF\">离退休日期</td>\n" +
                "    <td bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">定点医疗机构1</td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\">望京医院 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">定点医疗机构2</td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">解放军309医院 </td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">定点医疗机构3</td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">定点医疗机构4</td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "  <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">定点医疗机构5 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">*是否患有特殊病 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> 无特殊病</td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\" bgcolor=\"#FFFFFF\">\n" +
                "    \t<td colspan=\"6\" height=\"25\" bgcolor=\"#FFFFFF\"><b>外籍人员信息 </b></td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">护照号码 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">外国人居留证号码 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">外国人证件类型 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">外国人证件号码 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <!-- --\n" +
                "    <tr align=\"center\" bgcolor=\"#FFFFFF\">\n" +
                "    \t<td colspan=\"6\" height=\"25\" bgcolor=\"#FFFFFF\"><b>附属信息 </b></td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">联系人姓名 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">联系人电话 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> 17736189175</td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">《北京市工作居住证》编码 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">有效截止日期 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">委托代发基金银行名称 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> 建设银行</td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">委托代发基金银行账号 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> 建设银行</td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">工种 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">农转非类别 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">批准征地日期 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">农转工补缴单位名称 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">申报报销单位社保号 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">申报报销单位名称 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">社会保险补助开始时间 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">社会保险补助截止时间 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">手工报销街道 </td>\n" +
                "    <td height=\"25\" colspan=\"5\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"left\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">&nbsp;<b>工种补充资料</b> </td>\n" +
                "    <td height=\"25\" colspan=\"5\" bgcolor=\"#FFFFFF\"></td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">特殊工种 </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\">工作起始时间 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">所在单位名称 </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\">组织机构代码 </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\">\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" colspan=\"3\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    <td height=\"25\" bgcolor=\"#FFFFFF\"> </td>\n" +
                "    </tr> -->\n" +
                "</table>   \n" +
                "</form>";
        Map strMap = new HashMap();
        str = str.replaceAll("<(\\S*?)[^>;]*>.*?|<.*? />","")
                .replaceAll("&nbsp;" , "")
                .replaceAll("-->" , "")
                .replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1")
                .replaceAll("^((\r\n)|\n)", "");
        List<String> list = Arrays.asList(str);
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("user.txt"),true));
        for (int i = 0 ; i < list.size() ; i++){
            writer.write(list.get(i));
            writer.write("\n");
            writer.flush();
        }
        BufferedReader reader = new BufferedReader(new FileReader(new File("user.txt")));
        int count = 0;
        String str1 = null;
            while ((str1 = reader.readLine()) != null){
                strMap.put(count , str1.trim());
                count++;

        }
       // System.out.println(strMap.toString());
        SbUser sbUser = new SbUser();
        String setUserName =(String) strMap.get(15);
        sbUser.setUserName(setUserName);
        String setComPany_Name = (String) strMap.get(0);
        sbUser.setComPany_Name(setComPany_Name.substring(setComPany_Name.indexOf("：")+2,setComPany_Name.length()));
        String Unified_Social_Credit_Code = (String) strMap.get(1);
        sbUser.setUnified_Social_Credit_Code(Unified_Social_Credit_Code.substring(17));
        String setSocial_Security_Registration_Number = (String) strMap.get(2);
        sbUser.setSocial_Security_Registration_Number(setSocial_Security_Registration_Number.substring(8));
        String setCounty = (String) strMap.get(3);
        sbUser.setCounty(setCounty.substring(7));
        sbUser.setId_Number((String) strMap.get(17));
        sbUser.setSex((String) strMap.get(19));
        sbUser.setDate((String) strMap.get(21));
        sbUser.setNation((String) strMap.get(23));
        sbUser.setState((String) strMap.get(25));
        sbUser.setDate_Of_Employment((String) strMap.get(29));
        String setAccount_Properties = (String) strMap.get(31);
        sbUser.setAccount_Properties(setAccount_Properties.substring(6));
        sbUser.setRegistered_Permanent_Residence((String) strMap.get(33));
        sbUser.setPlace_Of_Abode((String) strMap.get(37));
        sbUser.setPhone((String) strMap.get(48));
        sbUser.setSalary((String) strMap.get(52));
        sbUser.setBank_Account((String) strMap.get(58));
        sbUser.setPin((String) strMap.get(27));
        System.out.println(sbUser.toString());
    }

}
