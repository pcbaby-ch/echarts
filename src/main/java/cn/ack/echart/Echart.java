package cn.ack.echart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author ack (chenzhao)  @DATE: Aug 23, 2017 
* Copyright ©  pcbaby-ch@qq.com All Rights Reserved
 */
public class Echart {

	/**
	 * 
	 */
	private  String chartString="";

	/**
	 * 默认初始构造折线图模板
	 */
	public Echart(){
		chartString="{title: {text: '#title#'},tooltip : {trigger: 'axis'},legend: {data:#stack#},toolbox: {feature: {saveAsImage: {}}},grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},xAxis : [{type : 'category',boundaryGap : false,data : #xAxis#}],yAxis : [{type : 'value'}],series : #stackData#}";
	}
	/**
	 * 构造自定义图形模板（根据echart3官网示例
	 * @param charS
	 */
	public Echart(String charS){
		chartString=charS;
	}
	/**
	 * replacement的value为String类型时，直接根据key替换，否则转为Json》String后再替换
	 * @author ack (chenzhao)  @DATE: Aug 23, 2017 
	 * @param replacement
	 * @return 返回生成option字符串
	 */
	public  String getOption(LinkedHashMap<String, Object> replacement){
		Set<Entry<String, Object>> entrySet=replacement.entrySet();
		Iterator<Entry<String, Object>> iterator=entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object>entry=(Entry<String, Object>) iterator.next();
			//如果value非String，则将value转为json再转为String
			String value=entry.getValue()+"";
			Object objValue=entry.getValue() ;
			if(!(objValue instanceof String)){
				if(objValue instanceof List||objValue instanceof Object[]){//如果是数组或集合
					JSONArray jsonArray=JSONArray.fromObject(objValue);
					value=jsonArray.toString();
				}else{
					JSONObject jsonObjecct=JSONObject.fromObject(entry.getValue());
					value=jsonObjecct.toString();
				}
			}
			//根据key替换Option字符串里的字符
			chartString=chartString.replace(entry.getKey()+"", value);
		}
		return chartString;
	}
	
	/**
	 *  分析组装echart折线图需要的series部分String
	 * @author ack (chenzhao)  @DATE: Aug 23, 2017 
	 * @param varList 提示：list记录中首字段必须是lineName
	 * @return
	 */
	public static String getLineSeriesStr(List<LinkedHashMap<String, Object>> varList) {
		StringBuffer seriesStr=new StringBuffer("[");
		for (int i = 0; i < varList.size(); i++) {
			LinkedHashMap<String, Object> row=varList.get(i);
			Set<Entry<String, Object>> entrySet=row.entrySet();
			Iterator<Entry<String, Object>> iter=entrySet.iterator();
			String lineStr="";
			ArrayList<Object> list=new ArrayList<Object>();
			String lineName="";
			for (int j = 0;iter.hasNext(); j++) {//组装一条折线数据
				Entry<String, Object> entry=iter.next();
				if(j==0){//map中一行记录的首值
					lineName="name:'"+entry.getValue();
				}else{
					list.add(entry.getValue());
				}
				String data=JSONArray.fromObject(list).toString();
				lineStr="{"+lineName+"',type:'line',data:"+data+",areaStyle: {normal: {}}"+"}";
			}
			if(i==varList.size()-1)
				seriesStr.append(lineStr+"]");
			else
				seriesStr.append(lineStr+",");
		}
		return seriesStr.toString();
	}
	
	public static String getInerPieSeriesStr(ArrayList<HashMap<String,Object>> inerList,ArrayList<HashMap<String,Object>>outList) {
		String seriesStr="";
		String innerRing="{         name:'访问来源',         type:'pie',         selectedMode: 'single',         radius: [0, '30%'],         label: {             normal: {                 position: 'inner'             }         },         labelLine: {             normal: {                 show: false             }         },         data:[             {value:335, name:'直达', selected:true},             {value:679, name:'营销广告'},             {value:1548, name:'搜索引擎'}         ]     }";
		innerRing="{         name:'访问来源',         type:'pie',         selectedMode: 'single',         radius: [0, '30%'],         label: {             normal: {                 position: 'inner'             }         },         labelLine: {             normal: {                 show: false             }         },         data:#innerData#     }";
		//填充内环数据
		innerRing=innerRing.replace("#innerData#", JSONArray.fromObject(inerList)+"");
		String outRing="{         name:'访问来源',         type:'pie',         radius: ['40%', '55%'],         label: {             normal: {                 formatter: '{a|{a}}{abg|}\\n{hr|}\\n  {b|{b}：}{c}  {per|{d}%}  ',                 backgroundColor: '#eee',                 borderColor: '#aaa',                 borderWidth: 1,                 borderRadius: 4,                                  rich: {                     a: {                         color: '#999',                         lineHeight: 22,                         align: 'center'                     },                                          hr: {                         borderColor: '#aaa',                         width: '100%',                         borderWidth: 0.5,                         height: 0                     },                     b: {                         fontSize: 16,                         lineHeight: 33                     },                     per: {                         color: '#eee',                         backgroundColor: '#334455',                         padding: [2, 4],                         borderRadius: 2                     }                 }             }         },         data:[             {value:335, name:'直达'},             {value:310, name:'邮件营销'},             {value:234, name:'联盟广告'},             {value:135, name:'视频广告'},             {value:1048, name:'百度'},             {value:251, name:'谷歌'},             {value:147, name:'必应'},             {value:102, name:'其他'}         ]     }";
		outRing="{         name:'访问来源',         type:'pie',         radius: ['40%', '55%'],         label: {             normal: {                 formatter: '{a|{a}}{abg|}\\n{hr|}\\n  {b|{b}：}{c}  {per|{d}%}  ',                 backgroundColor: '#eee',                 borderColor: '#aaa',                 borderWidth: 1,                 borderRadius: 4,                                  rich: {                     a: {                         color: '#999',                         lineHeight: 22,                         align: 'center'                     },                                          hr: {                         borderColor: '#aaa',                         width: '100%',                         borderWidth: 0.5,                         height: 0                     },                     b: {                         fontSize: 16,                         lineHeight: 33                     },                     per: {                         color: '#eee',                         backgroundColor: '#334455',                         padding: [2, 4],                         borderRadius: 2                     }                 }             }         },         data:#outData#     }";
		//填充外环数据
		outRing=outRing.replaceAll("#outData#", JSONArray.fromObject(outList)+"");
		seriesStr="["+innerRing+","+outRing+"]";
		return seriesStr;
	}
	
	/**
	 * 分析组装echart饼图需要的series部分String
	 * @author ack (chenzhao)  @DATE: Aug 23, 2017 
	 * @param varList 提示：list记录中首字段必须是lineName
	 * @return
	 */
	public static StringBuffer getPieSeriesStr(List<LinkedHashMap<String, Object>> varList) {
		StringBuffer seriesStr=new StringBuffer("[");
		for (int i = 0; i < varList.size(); i++) {
			LinkedHashMap<String, Object> row=varList.get(i);
			Set<Entry<String, Object>> entrySet=row.entrySet();
			Iterator<Entry<String, Object>> iter=entrySet.iterator();
			String lineStr="";
			String lineName="";
			String lineValue="";
			for (int j = 0;iter.hasNext(); j++) {//组装一条折线数据
				Entry<String, Object> entry=iter.next();
				if(j==0){//map中一行记录的首值
					lineName="name:'"+entry.getValue();
				}else{
					lineValue=entry.getValue()+"";
				}
				lineStr="{"+lineName+"',value:"+lineValue+"}";
			}
			if(i==varList.size()-1)
				seriesStr.append(lineStr+"]");
			else
				seriesStr.append(lineStr+",");
		}
		return seriesStr;
	}
	
	public static void main(String[] args) {
		ArrayList<HashMap<String,Object>>outList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object>map=new HashMap<String, Object>();
		map.put("name", "直达");		map.put("value", "356");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "营销广告");		map.put("value", "656");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "搜索引擎");		map.put("value", "1656");		outList.add(map);
		ArrayList<HashMap<String,Object>>innerLst=new ArrayList<HashMap<String,Object>>();innerLst.addAll(outList);
		map=new HashMap<String, Object>();		map.put("name", "邮件营销");		map.put("value", "315");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "联盟广告");		map.put("value", "234");		outList.add(map);
		System.out.println(Echart.getInerPieSeriesStr(innerLst, outList));
	}
}
