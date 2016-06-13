$(function() {

	$('.action .add').click(function(){
		$('#projectWin .modal-title').text("添加项目");
		$('#projectWin').modal('show');
	});
	
	$('.action .delete').click(function(){
		var projectId = $('#projectId').val();
		$.post("project/delete",{id:projectId},function(data){
			window.location.href='project/index';
		});
	});
	
	$('#projectWin .save').click(function(){
		$("#projectForm").submit();
	});
	
});