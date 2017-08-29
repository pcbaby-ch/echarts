<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="cn.ack.echart.Echart" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>hello echart</title>
	<script src="http://echarts.baidu.com/dist/echarts.common.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        <%
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
		row2.put("lineName", "折线B");
		row2.put("1", 31);
		row2.put("2", 23);
		row2.put("3", 82);
		row2.put("4", 52);
		row2.put("5", 152);
		row2.put("6", 252);
		varList.add(row);
		varList.add(row2);
		LinkedHashMap<String, Object>replacement=new LinkedHashMap<String, Object>();
		replacement.put("#title#", "折线图示例");
		replacement.put("#stack#",new String[] {"折线A","折线B"});
		replacement.put("#xAxis#",new String[] {"周一","周二","周三","周四","周五","周日"});
		replacement.put("#stackData#",Echart.getLineSeriesStr(varList)+"");
		System.out.println("#折线图:"+e.getOption(replacement));;
        %>
        var option =<%=e.getOption(replacement)%>;

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="pie" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var pie = echarts.init(document.getElementById('pie'));

        // 指定图表的配置项和数据
        <%
        e=new Echart( " { tooltip: {     trigger: 'item',     formatter: '{a} <br/>{b}: {c} ({d}%)' }, legend: {     orient: 'vertical',     x: 'left',     data:#stack }, series: #series}");
		varList=new ArrayList<LinkedHashMap<String, Object>>();
		row=new LinkedHashMap<String, Object>();
		row.put("lineName", "折线A");
		row.put("1", 23);
		row.put("2", 31);
		row.put("3", 52);
		row.put("4", 82);
		row.put("5", 252);
		row.put("6", 152);
		row2=new LinkedHashMap<String, Object>();
		row2.put("lineName", "折线B");
		row2.put("1", 31);
		row2.put("2", 23);
		row2.put("3", 82);
		row2.put("4", 52);
		row2.put("5", 152);
		row2.put("6", 252);
		varList.add(row);
		varList.add(row2);
		replacement=new LinkedHashMap<String, Object>();
		replacement.put("#stack",new String[]{"直达","营销广告","搜索引擎","邮件营销","联盟广告","视频广告","百度","谷歌","必应","其他"});
		ArrayList<HashMap<String,Object>>outList=new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object>map=new HashMap<String, Object>();		map.put("name", "直达");		map.put("value", "335");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "营销广告");		map.put("value", "679");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "搜索引擎");		map.put("value", "1548");		outList.add(map);
		ArrayList<HashMap<String,Object>>innerLst=new ArrayList<HashMap<String,Object>>();innerLst.addAll(outList);
		map=new HashMap<String, Object>();		map.put("name", "邮件营销");		map.put("value", "310");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "联盟广告");		map.put("value", "234");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "谷歌");		map.put("value", "315");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "必应");		map.put("value", "147");		outList.add(map);
		map=new HashMap<String, Object>();		map.put("name", "其他");		map.put("value", "102");		outList.add(map);
		replacement.put("#series",Echart.getInerPieSeriesStr(innerLst,outList));
		System.out.println("#折线图:"+e.getOption(replacement));;
        %>
        option=<%=e.getOption(replacement)%>
        // 使用刚指定的配置项和数据显示图表。
        pie.setOption(option);
    </script>
</body>
</html>