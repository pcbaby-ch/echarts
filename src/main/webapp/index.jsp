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
</body>
</html>