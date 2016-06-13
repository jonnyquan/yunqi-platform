$(function() {
	
	$('.action .add').click(function(){
		
		$('#sysProcessWin .modal-title').text("添加监控进程");
		$('#sysProcessWin').modal('show');
		var projectId = $('#projectId').val();
		$.post("server/list",{projectId:projectId},function(data){
				var list = data.result;
				var select = $('#sysProcessWin #serverId');
				$.each(list, function(i,server){      
					select.append($("<option>").val(server.id).text(server.ip));
				}); 
		});
	});
	
	$('.action .delete').click(function(){
		var projectId = $('#projectId').val();
		var sysProcessId = $('#sysprocess-list a.active').attr('data');
		$.post("sysprocess/delete",{id:sysProcessId},function(data){
			$('#sysProcessWin').modal('hide');
			window.location.href='project/' + projectId + '/sysprocess';
		});
	});
	
	$('#sysProcessWin .save').click(function(){
		$("#sysProcessForm").submit();
	});

	
	   require.config({
	        paths: {
	            echarts: 'http://echarts.baidu.com/build/dist'
	        }
	    });
		
		var sysProcessList = $("#sysprocess-list .list-group .list-group-item");
		var chartTypes = $("button[name='chartType']");
		var chartLevels = $("button[name='chartLevel']");
		
		sysProcessList.click(function(){
			
			var $this = $(this);
			sysProcessList.removeClass("active");
			$this.addClass("active");
			
			var selectedType = $("button[name='chartType'].active");
			var selectedLevel = $("button[name='chartLevel'].active");
			
			var sysProcessId = $this.attr("data");
			var type = selectedType.val();
			var level = selectedLevel.val();
			
			refreshChart(sysProcessId,type,level);
			
		});
		
		chartTypes.click(function(){
			var $this = $(this);
			chartTypes.removeClass("active");
			$this.addClass("active");
			
			var selectedServer = $("#sysprocess-list .list-group .list-group-item.active");
			var selectedLevel = $("button[name='chartLevel'].active");
			
			var sysProcessId = selectedServer.attr("data");
			var type = $this.val();
			var level = selectedLevel.val();
			
			refreshChart(sysProcessId,type,level);
			
		});
		
		chartLevels.click(function(){
			var $this = $(this);
			chartLevels.removeClass("active");
			$this.addClass("active");
			
			var selectedServer = $("#sysprocess-list .list-group .list-group-item.active");
			var selectedType = $("button[name='chartType'].active");
			
			var sysProcessId = selectedServer.attr("data");
			var type = selectedType.val();
			var level = $this.val();
			
			refreshChart(sysProcessId,type,level);
			
		});
		
		sysProcessList[0].click();
		
		function refreshChart(sysProcessId, type, level){
			if(type=='MEMORY'){
				refreshMemoryChart(sysProcessId,level);
			}else if(type=='CPU'){
				refreshCpuChart(sysProcessId,level);
			}
		}
		
		function refreshMemoryChart(sysProcessId,level){
			
			$.post('sysprocess/memoryusage', {sysProcessId:sysProcessId,level:level}, function(data) {
				
				var sysProcess = data.result.sysProcess;
				
				$("#main .server-ip").text(sysProcess.server.ip);
				$("#main .sysprocess-name").text(sysProcess.name);
				
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
		
		function refreshCpuChart(sysProcessId,level){
			
			$.post('sysprocess/cpuusage', {sysProcessId:sysProcessId,level:level}, function(data) {
				
				var sysProcess = data.result.sysProcess;
				
				$("#main .server-ip").text(sysProcess.server.ip);
				$("#main .sysprocess-name").text(sysProcess.name);
				
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

});