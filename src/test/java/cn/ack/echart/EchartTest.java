package cn.ack.echart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class EchartTest {

	@Test
	public void lineEchartTest() {
		Echart e=new Echart();
		List<LinkedHashMap<String, Object>> varList=new ArrayList<LinkedHashMap<String, Object>>();
		LinkedHashMap<String, Object>row=new LinkedHashMap<String, Object>();
		row.put("lineName", "折线A");
		row.put("1", 23);
		row.put("2", 31);
		row.put("3", 52);
		row.put("4", 82);
		row.put("5", 252);
		row.put("6", 152);
		LinkedHashMap<String, Object>row2=new LinkedHashMap<String, Object>();
		row2.putAll(row);
		row2.put("lineName", "折线B");
		varList.add(row);
		varList.add(row2);
		LinkedHashMap<String, Object>replacement=new LinkedHashMap<String, Object>();
		replacement.put("title", "折线图示例");
		replacement.put("stack",new String[] {"折线A","折线B"});
		replacement.put("xAxis",new String[] {"周一","周二","周三","周四","周五","周日"});
		replacement.put("stackData",e.getLineSeriesStr(varList));
		System.out.println("#replacement:"+replacement);
		System.out.println("#折线图:"+e.getOption(replacement));;
	}
}
