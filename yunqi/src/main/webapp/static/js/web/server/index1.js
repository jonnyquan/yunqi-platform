$(function() {
	
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    
	var cpuChart;
	var cpuOption = {
	    tooltip : {
	        formatter: "{b} : {c}%"
		},
		toolbox: {
		    show : true,
		    feature : {
		        mark : {show: true},
		        restore : {show: true},
		        saveAsImage : {show: true}
		    }
		},
		series : [{
	        name:'',
	        type:'gauge',
	        detail : {formatter:'{value}%'},
	        data:[{value: 0, name: 'CPU'}],
	        axisLine:{
	            show: true,
	            lineStyle: {width: 15}
	        } 
	    }]
	};
	
	require(['echarts','echarts/chart/gauge'],
		function (ec) {
			cpuChart = ec.init($("#cpu")[0]); 
			cpuChart.setOption(cpuOption); 
		}
	);
	
	var memoryChart;
	var memoryOption = {
	    tooltip : {
	        formatter: "{b} : {c}%"
		},
		toolbox: {
		    show : true,
		    feature : {
		        mark : {show: true},
		        restore : {show: true},
		        saveAsImage : {show: true}
		    }
		},
		series : [{
	        name:'',
	        type:'gauge',
	        detail : {formatter:'{value}%'},
	        data:[{value: 0, name: 'memory'}],
	        axisLine:{
	            show: true,
	            lineStyle: {width: 15}
	        } 
	    }]
	};
	
	require(['echarts','echarts/chart/gauge'],
		function (ec) {
			memoryChart = ec.init($("#memory")[0]); 
			memoryChart.setOption(memoryOption); 
		}
	);
	
	var networkChart;
	var networkOption = {
	    tooltip : {
	        formatter: "{b} : {c}%"
		},
		toolbox: {
		    show : true,
		    feature : {
		        mark : {show: true},
		        restore : {show: true},
		        saveAsImage : {show: true}
		    }
		},
		series : [{
	        name:'',
	        type:'gauge',
	        detail : {formatter:'{value}%'},
	        data:[{value: 0, name: 'network'}],
	        axisLine:{
	            show: true,
	            lineStyle: {width: 15}
	        }
	    }]
	};
	
	require(['echarts','echarts/chart/gauge'],
		function (ec) {
			networkChart = ec.init($("#network")[0]); 
			networkChart.setOption(networkOption); 
		}
	);
	
	var diskChart;
	var diskOption = {
	    tooltip : {
	        formatter: "{b} : {c}%"
		},
		toolbox: {
		    show : true,
		    feature : {
		        mark : {show: true},
		        restore : {show: true},
		        saveAsImage : {show: true}
		    }
		},
		series : [{
	        name:'',
	        type:'gauge',
	        detail : {formatter:'{value}%'},
	        data:[{value: 0, name: 'disk'}],
	        axisLine:{
	            show: true,
	            lineStyle: {width: 15}
	        }
	    }]
	};
	
	require(['echarts','echarts/chart/gauge'],
		function (ec) {
			diskChart = ec.init($("#disk")[0]); 
			diskChart.setOption(diskOption); 
		}
	);
    
	var serversList = $("#server-list .list-group .list-group-item");
	
	serversList.click(function(){
		var $this = $(this);
		serversList.removeClass("active");
		$this.addClass("active");
		refreshChart();
	});
	
	serversList[0].click();
	
	var timeTicket;
	if(timeTicket){
		clearInterval(timeTicket);
	}
	timeTicket = setInterval(refreshChart,10000);
	
	function refreshChart(){
		
		var serverId = $("#server-list .list-group .list-group-item.active").attr("data");
		
		$.post('server/status', {serverId:serverId}, function(data) {
			
			var server = data.result.server;
			var cpu = data.result.cpu;
			var memory = data.result.memory;
			var network = data.result.network;
			var disk = data.result.disk;
			
			$("#main .server-ip").text(server.ip);
			$("#main .server-cpu").text(server.cpu);
			$("#main .server-memory").text(server.memory+"G");
			
			cpuOption.series[0].data[0].value = cpu;
		    cpuChart.setOption(cpuOption, true);

		    memoryOption.series[0].data[0].value = memory;
		    memoryChart.setOption(memoryOption, true);
		    
		    networkOption.series[0].data[0].value = network;
		    networkChart.setOption(networkOption, true);
		    
		    diskOption.series[0].data[0].value = disk;
		    diskChart.setOption(diskOption, true);
			
		});
	}

});