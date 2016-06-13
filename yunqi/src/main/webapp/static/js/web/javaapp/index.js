$(function() {
	
	$('.action .add').click(function(){
		
		$('#javaAppWin .modal-title').text("添加监控JAVA进程");
		$('#javaAppWin').modal('show');
		var projectId = $('#projectId').val();
		$.post("server/list",{projectId:projectId},function(data){
				var list = data.result;
				var select = $('#javaAppWin #serverId');
				$.each(list, function(i,server){      
					select.append($("<option>").val(server.id).text(server.ip));
				}); 
		});
	});
	
	$('.action .delete').click(function(){
		var projectId = $('#projectId').val();
		var javaAppId = $('#javaapp-list a.active').attr('data');
		$.post("javaapp/delete",{id:javaAppId},function(data){
			$('#javaAppWin').modal('hide');
			window.location.href='project/' + projectId + '/javaapp';
		});
	});
	
	$('#javaAppWin .save').click(function(){
		$("#javaAppForm").submit();
	});
	
	   require.config({
	        paths: {
	            echarts: 'http://echarts.baidu.com/build/dist'
	        }
	    });
		
		var javaAppList = $("#javaapp-list .list-group .list-group-item");
		var chartTypes = $("button[name='chartType']");
		var chartLevels = $("button[name='chartLevel']");
		
		javaAppList.click(function(){
			
			var $this = $(this);
			javaAppList.removeClass("active");
			$this.addClass("active");
			
			var selectedType = $("button[name='chartType'].active");
			var selectedLevel = $("button[name='chartLevel'].active");
			
			var javaAppId = $this.attr("data");
			var type = selectedType.val();
			var level = selectedLevel.val();
			
			refreshChart(javaAppId,type,level);
			
		});
		
		chartTypes.click(function(){
			var $this = $(this);
			chartTypes.removeClass("active");
			$this.addClass("active");
			
			var selectedServer = $("#javaapp-list .list-group .list-group-item.active");
			var selectedLevel = $("button[name='chartLevel'].active");
			
			var javaAppId = selectedServer.attr("data");
			var type = $this.val();
			var level = selectedLevel.val();
			
			refreshChart(javaAppId,type,level);
			
		});
		
		chartLevels.click(function(){
			var $this = $(this);
			chartLevels.removeClass("active");
			$this.addClass("active");
			
			var selectedServer = $("#javaapp-list .list-group .list-group-item.active");
			var selectedType = $("button[name='chartType'].active");
			
			var javaAppId = selectedServer.attr("data");
			var type = selectedType.val();
			var level = $this.val();
			
			refreshChart(javaAppId,type,level);
			
		});
		
		javaAppList[0].click();
		
		function refreshChart(javaAppId, type, level){
			if(type=='MEMORY'){
				refreshMemoryChart(javaAppId,level);
			}else if(type=='CPU'){
				refreshCpuChart(javaAppId,level);
			}else if(type=='THREAD'){
				refreshThreadChart(javaAppId,level);
			}
		}
		
		function refreshMemoryChart(javaAppId,level){
			
			$.post('javaapp/memoryusage', {javaAppId:javaAppId,level:level}, function(data) {
				
				var javaApp = data.result.javaApp;
				
				$("#main .server-ip").text(javaApp.server.ip);
				$("#main .javaapp-name").text(javaApp.name);
				
				var xAxis = data.result.xAxis;
				var memoryUsage = data.result.memoryUsage;

				var memoryChart;
				var memoryOption =  {
						backgroundColor: '#F2F2F2',
					    tooltip : {
					        trigger: 'axis',
					        formatter: "采集时间:{b}<br/>内存用量:{c}M"
					    },
					    legend: {data:['内存用量']},
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : false,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : xAxis
					        }
					    ],
					    yAxis : [
					        {
					        	//max : 100,
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'内存用量',
					            type:'line',
					            smooth: true,
					            symbol: 'emptyCircle',     // 系列级个性化拐点图形
					            symbolSize: 2,
					            itemStyle: {
					                normal: {
					                    lineStyle: { width: 2 }
					                },
					                emphasis : { label : {show: true} }
					            },
					            data:memoryUsage
					        }
					    ]
					};
				
				require(['echarts','echarts/chart/line'],
					function (ec) {
						memoryChart = ec.init($("#chart")[0]); 
						memoryChart.setOption(memoryOption); 
					}
				);
				
			});
			
		}
		
		function refreshCpuChart(javaAppId,level){
			
			$.post('javaapp/cpuusage', {javaAppId:javaAppId,level:level}, function(data) {
				
				var javaApp = data.result.javaApp;
				
				$("#main .server-ip").text(javaApp.server.ip);
				$("#main .javaapp-name").text(javaApp.name);
				
				var xAxis = data.result.xAxis;
				var cpuUsage = data.result.cpuUsage;

				var cpuChart;
				var cpuOption =  {
						backgroundColor: '#F2F2F2',
					    tooltip : {
					        trigger: 'axis',
					        formatter: "采集时间:{b}<br/>CPU使用率:{c}%"
					    },
					    legend: {data:['CPU使用率']},
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : false,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : xAxis
					        }
					    ],
					    yAxis : [
					        {
					        	max : 100,
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'CPU使用率',
					            type:'line',
					            smooth: true,
					            symbol: 'emptyCircle',     // 系列级个性化拐点图形
					            symbolSize: 2,
					            itemStyle: {
					                normal: {
					                    lineStyle: { width: 2 }
					                },
					                emphasis : { label : {show: true} }
					            },
					            data:cpuUsage
					        }
					    ]
					};
				
				require(['echarts','echarts/chart/line'],
					function (ec) {
						cpuChart = ec.init($("#chart")[0]); 
						cpuChart.setOption(cpuOption); 
					}
				);
				
			});
			
		}
		
		function refreshThreadChart(javaAppId,level){
			
			$.post('javaapp/threadcount', {javaAppId:javaAppId,level:level}, function(data) {
				
				var javaApp = data.result.javaApp;
				
				$("#main .server-ip").text(javaApp.server.ip);
				$("#main .javaapp-name").text(javaApp.name);
				
				var xAxis = data.result.xAxis;
				var threadCount = data.result.threadCount;

				var threadChart;
				var threadOption =  {
						backgroundColor: '#F2F2F2',
					    tooltip : {
					        trigger: 'axis',
					        formatter: "采集时间:{b}<br/>线程数量:{c}个"
					    },
					    legend: {data:['线程数量']},
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : false,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : xAxis
					        }
					    ],
					    yAxis : [
					        {
					        	//max : 100,
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'线程数量',
					            type:'line',
					            smooth: true,
					            symbol: 'emptyCircle',     // 系列级个性化拐点图形
					            symbolSize: 2,
					            itemStyle: {
					                normal: {
					                    lineStyle: { width: 2 }
					                },
					                emphasis : { label : {show: true} }
					            },
					            data:threadCount
					        }
					    ]
					};
				
				require(['echarts','echarts/chart/line'],
					function (ec) {
						threadChart = ec.init($("#chart")[0]); 
						threadChart.setOption(threadOption); 
					}
				);
				
			});
			
		}

});