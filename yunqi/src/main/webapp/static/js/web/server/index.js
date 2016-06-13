$(function() {
	
	
	$('.action .add').click(function(){
		$('#serverWin .modal-title').text("添加监控服务器");
		$('#serverWin').modal('show');
	});
	
	$('.action .delete').click(function(){
		var projectId = $('#projectId').val();
		var serverId = $('#server-list a.active').attr('data');
		$.post("server/delete",{id:serverId},function(data){
			window.location.href='project/' + projectId + '/server';
		});
	});
	
	$('#serverWin .save').click(function(){
		$("#serverForm").submit();
	});
	
	
	
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
	
	var serversList = $("#server-list .list-group .list-group-item");
	var chartTypes = $("button[name='chartType']");
	var chartLevels = $("button[name='chartLevel']");
	
	serversList.click(function(){
		
		var $this = $(this);
		serversList.removeClass("active");
		$this.addClass("active");
		
		var selectedType = $("button[name='chartType'].active");
		var selectedLevel = $("button[name='chartLevel'].active");
		
		var serverId = $this.attr("data");
		var type = selectedType.val();
		var level = selectedLevel.val();
		
		refreshChart(serverId,type,level);
		
	});
	
	chartTypes.click(function(){
		var $this = $(this);
		chartTypes.removeClass("active");
		$this.addClass("active");
		
		var selectedServer = $("#server-list .list-group .list-group-item.active");
		var selectedLevel = $("button[name='chartLevel'].active");
		
		var serverId = selectedServer.attr("data");
		var type = $this.val();
		var level = selectedLevel.val();
		
		refreshChart(serverId,type,level);
		
	});
	
	chartLevels.click(function(){
		var $this = $(this);
		chartLevels.removeClass("active");
		$this.addClass("active");
		
		var selectedServer = $("#server-list .list-group .list-group-item.active");
		var selectedType = $("button[name='chartType'].active");
		
		var serverId = selectedServer.attr("data");
		var type = selectedType.val();
		var level = $this.val();
		
		refreshChart(serverId,type,level);
		
	});
	
	serversList[0].click();
	
	function refreshChart(serverId, type, level){
		
		$.post('server/info', {serverId:serverId,type:type,level:level}, function(data) {
			
			var server = data.result.server;
//			var nodes = data.result.nodes;
			
			$("#main .server-ip").text(server.ip);
			$("#main .server-cpu").text(server.cpu);
			$("#main .server-memory").text(server.memory+"G");
			
			var chartParms = {
					symbolSize : 2,
					emphasis : true
			};

			if(level=='1'){
				chartParms.symbolSize = 2;
				chartParms.emphasis = false;
			}else if(level=='2'){
				chartParms.symbolSize = 1;
				chartParms.emphasis = false;
			}else if(level=='3'){
				chartParms.symbolSize = 1;
				chartParms.emphasis = false;
			}else if(level=='4'){
				chartParms.symbolSize = 1;
				chartParms.emphasis = false;
			}
			
			if(type=='MEMORY'){
				createMemoryChart(chartParms, data.result.xAxis, data.result.memory);
			}else if(type=='CPU'){
				createCpuChart(chartParms, data.result.xAxis, data.result.cpu);
			}else if(type=='NETWORK'){
				createNetworkChart(chartParms, data.result.xAxis, data.result.netIn, data.result.netOut);
			}else if(type=='DISK'){
				createDiskChart(chartParms, data.result.xAxis, data.result.diskRead, data.result.diskWrite);
			}
			
		});
	}
	
	function createMemoryChart(chartParms, xAxis,memory){

		var memoryChart;
		var memoryOption =  {
				backgroundColor: '#F2F2F2',
			    tooltip : {
			        trigger: 'axis',
			        formatter: "采集时间:{b}<br/>内存使用率:{c}%"
			    },
			    legend: {data:['内存使用率']},
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
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
			            name:'内存使用率',
			            type:'line',
			            smooth: true,
			            symbol: 'emptyCircle',     // 系列级个性化拐点图形
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:memory
			        }
			    ]
			};
		
		require(['echarts','echarts/chart/line'],
			function (ec) {
				memoryChart = ec.init($("#chart")[0]); 
				memoryChart.setOption(memoryOption); 
			}
		);
		
	}
	
	function createCpuChart(chartParms, xAxis,cpu){

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
			    calculable : true,
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
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:cpu
			        }
			    ]
			};
		
		require(['echarts','echarts/chart/line'],
			function (ec) {
				cpuChart = ec.init($("#chart")[0]); 
				cpuChart.setOption(cpuOption); 
			}
		);
		
	}
	
	function createNetworkChart(chartParms, xAxis, netIn, netOut){

		var networkChart;
		var networkOption =  {
				backgroundColor: '#F2F2F2',
			    tooltip : {
			        trigger: 'axis',
			        formatter: "时间：{b}<br/>出网:{c}M<br/>入网：{c1}M"
			    },
			    legend: {data:['流量（出）','流量（入）']},
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : xAxis
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'流量（出）',
			            type:'line',
			            smooth: true,
			            symbol: 'emptyCircle',     // 系列级个性化拐点图形
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:netOut
			        },
			        {
			            name:'流量（入）',
			            type:'line',
			            smooth: true,
			            symbol:'emptyCircle',
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:netIn
			        }
			    ]
			};
		
		require(['echarts','echarts/chart/line'],
			function (ec) {
				networkChart = ec.init($("#chart")[0]); 
				networkChart.setOption(networkOption); 
			}
		);
		
	}
	
	function createDiskChart(chartParms, xAxis, diskRead, diskWrite){

		var diskChart;
		var diskOption =  {
				backgroundColor: '#F2F2F2',
			    tooltip : {
			        trigger: 'axis',
			        formatter: "时间：{b}<br/>读取:{c}M<br/>写入：{c1}M"
			    },
			    legend: {data:['IO（读）','IO（写）']},
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : xAxis
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'IO（读）',
			            type:'line',
			            smooth: true,
			            symbol: 'emptyCircle',     // 系列级个性化拐点图形
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:diskRead
			        },
			        {
			            name:'IO（写）',
			            type:'line',
			            smooth: true,
			            symbol:'emptyCircle',
			            symbolSize: chartParms.symbolSize,
			            itemStyle: {
			                normal: {
			                    lineStyle: {
			                        width: 2
			                    }
			                },
			                emphasis : {
			                    label : {show: chartParms.emphasis}
			                }
			            },
			            data:diskWrite
			        }
			    ]
			};
		
		require(['echarts','echarts/chart/line'],
			function (ec) {
				diskChart = ec.init($("#chart")[0]); 
				diskChart.setOption(diskOption); 
			}
		);
		
	}

});