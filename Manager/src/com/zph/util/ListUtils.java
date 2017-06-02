package com.zph.util;

import java.util.ArrayList;
import java.util.List;

import com.zph.pojo.User;

public class ListUtils {
	
	

	
	 /* String转换List
	 * 
	 * @param listText
	 *            :需要转换的文本
	 * @return List<?>
	 */
	private static final String SEP1 = "#";

	public static List<Object> StringToList(String listText) {
		if (listText == null || listText.equals("")) {
			return null;
		}
		listText = listText.substring(1);

		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split(SEP1);
		for (String str : text) {
			 if (str.charAt(0) == 'L') {
				List<?> lists = StringToList(str);
				list.add(lists);
			} else {
				list.add(str);
			}
		}
		return list;
	}

}
