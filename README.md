@Test
	public void lineEchartTest() {//3行关键代码(21,33,35)搞定折线图，其他都是测试数据准备（实际开发场景是service层给到的数据）
		String model="{title: {text: '#title#'},tooltip : {trigger: 'axis'},legend: {data:#stack#},toolbox: {feature: {saveAsImage: {}}},grid: {left: '3%',right: '4%',"
				+ "bottom: '3%',containLabel: true},xAxis : [{type : 'category',boundaryGap : false,data : #xAxis#}],yAxis : [{type : 'value'}],series : #stackData#}";
		//建立官方echart视图所需json数据的模板，占位符随意指定，这里使用#__#
		Echart e=new Echart(model);//.............................................................................
		List<LinkedHashMap<String, Object>> varList=new ArrayList<LinkedHashMap<String, Object>>();
		LinkedHashMap<String, Object>row=new LinkedHashMap<String, Object>();
		row.put("lineName", "折线A");	row.put("1", 23);	row.put("2", 31);	
		row.put("3", 52);	row.put("4", 82);	row.put("5", 252);	row.put("6", 152);
		LinkedHashMap<String, Object>row2=new LinkedHashMap<String, Object>();
		row2.putAll(row);	row2.put("lineName", "折线B");
		varList.add(row);		varList.add(row2);
		LinkedHashMap<String, Object>replacement=new LinkedHashMap<String, Object>();
		replacement.put("#title#", "折线图示例");
		replacement.put("#stack#",new String[] {"折线A","折线B"});
		replacement.put("#xAxis#",new String[] {"周一","周二","周三","周四","周五","周日"});
		replacement.put("#stackData#",e.getLineSeriesStr(varList));//..................................
		System.out.println("#replacement:"+e.getLineSeriesStr(varList));
		System.out.println("#折线图:"+e.getOption(replacement));;//.................................
	}
