function initApp() {
	$(".url-rewrite").each(function() {
		var self = $(this);
		self.change(function() {
		
		$.ajax( {
			url:"/convert-name-to-url",
			data:$.extend({"name":self.val()},getData()),
			method:"POST"
		}
		).then(function(data) {
			$(".url-target").val(data.value);
		})
		})
	})
}


function getData() {
	var data = {};
	data._csrf = $("input[name='_csrf']").val();
	return data;
}