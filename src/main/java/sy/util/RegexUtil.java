/**  
 *@Description:     正则解析类
 */
package sy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * @param regex
	 *            正则表达式
	 * @param content
	 *            内容
	 * @param group
	 *            序列号
	 * @return 正则匹配的内容，如果没有匹配的就返回null
	 * @Author:ydh
	 * @Description:根据正则匹配出匹配的内容
	 */
	public static String regerMatch(String regex, String content, int group) {
		Pattern p = Pattern.compile(regex, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher m = null;
		try {
			m = p.matcher(content);
		} catch (Exception e) {
			return null;
		}
		if (m.find()) {
			return m.group(group);
		}
		return null;
	}

	/**
	 * 
	 * @param regex
	 *            正则
	 * @param content
	 *            内容
	 * @param group
	 *            序列号
	 * @return 匹配到的所有结果集
	 * @Author:ydh
	 * @Description: 根据正则和内容，匹配需要的数据
	 */
	public static List<String> regerMatchList(String regex, String content, int group) {
		List<String> list = new ArrayList<String>();

		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);

		while (matcher.find()) {
			String reg = matcher.group(group);
			if (!list.contains(reg)) {
				list.add(reg);
			}
		}

		return list;
	}

	/***************** 之后用到再修改 ****************************/
	public static String getValueByReg(String content, String sign, String regex) {
		String keyValue = getValueByReg(content, sign + regex).replaceAll(" +", "").replace("\n", "").replace("</label>", "").replace("\r", "")
				.replace("\t", "").replace("&nbsp", "").replace(" ", "").replace("<span>", "").replace("</span>", "").trim();
		System.out.println(sign + ":" + keyValue);
		return keyValue;

	}
	// 判断一个字符串是否含有数字

	@SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
	public static boolean hasDigit(String content) {

		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");

		Matcher m = p.matcher(content);

		if (m.matches()) {
            flag = true;
        }

		return flag;

	}

	/**
	 * 判断是否整型或浮点
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIntegerOrDecimal(String str) {
		if (str == null || "".equals(str.trim())) {
            return false;
        }

		if (str.trim().matches("\\d+") || str.trim().matches("\\d+\\.\\d+")) {
            return true;
        }

		return false;
	}

	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * 
	 * @param content
	 *            html的文档内容
	 * @param reg
	 *            正则表达式
	 * @return
	 */
	public static String getValueByReg1(String content, String reg) {
		String value = "";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(content);
		if (m.find()) {
			if (m.groupCount() >= 1) {
				value = m.group(1);
			} else {
				value = m.group();
			}
		}
		return value;
	}

	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * 
	 * @param content
	 *            html的文档内容
	 * @param reg
	 *            正则表达式
	 * @return
	 */
	public static String getValueByReg(String content, String reg) {
		String value = "";
		if (null != content && !"".equals(content) && null != reg && !"".equals(reg)) {
			Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(content);
			if (m.find()) {
				if (m.groupCount() >= 1) {
					value = m.group(1);
				} else {
					value = m.group();
				}
			}
		}
		return value;
	}

	/**
	 * 通过正则表达式获取数据，返回一个String的值
	 * 
	 * @param content
	 *            html的文档内容
	 * @param reg
	 *            正则表达式
	 * @return
	 */
	public static List<String> getValuesByReg(String content, String reg) {
		List<String> values = new ArrayList<String>();
		String value = "";
		Pattern p = Pattern.compile(reg.substring(1), Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(content);
		while (m.find()) {
			if (m.groupCount() >= 1) {
				value = m.group(1);
			} else {
				value = m.group();
			}
			values.add(value);
		}
		return values;
	}

	public static List<String> getList(String content, String regex) {
		List<String> lists = new ArrayList<String>();
		Matcher mt = Pattern.compile(regex).matcher(content);
		String str = null;
		while (mt.find()) {
			str = mt.group();
			lists.add(str);

		}
		return lists;
	}

	public static void main(String[] args) {

	}
}
