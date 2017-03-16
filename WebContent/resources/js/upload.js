var fileVar;

$(document).on("click","#chouseFile",function(e){
	$("#file").click();
});
$(document).on("dragover", "#filedrag",function(e){

	e.stopPropagation();
	e.preventDefault();

	$(this).css('border',"2px solid #16a085");
});
$(document).on("dragleave", "#filedrag",function(e){

	e.stopPropagation();
	e.preventDefault();

	$(this).css('border',"2px dashed #3498db");
});
$(document).on("drop", "#filedrag",function(e){

	e.stopPropagation();
	e.preventDefault();

	$(this).css('border',"2px solid #16a085");

	var files;

	if(e.type === "drop" && e.originalEvent.dataTransfer ){
		files = e.originalEvent.dataTransfer.files;
	}

	if( !files || !files.length){
		return;
	}



	fileVar = files[0];

	effectOnFileLoad();
});

$(document).on("click","#buttonSendFile, #buttonSendLink",function(e){
	var courseId = $('#uploadFile').find('input[name="course_id"]').val();
	var userId = $('#uploadFile').find('input[name="user_id"]').val();
		var typeVar = "file";
		var descVar = $("#textDesc").val();
		var fd = new FormData();
		fd.append("course_id", courseId);
		fd.append("user_id", userId);
		fd.append("type", typeVar);
		fd.append("desc", descVar);
		fd.append("file", fileVar);
		$("#progresUpload").css('display' , "inline-block");
		$( "#messages" ).css('display' , "none");
		$( "#buttonSendFile" ).css('display' , "none");
		$( "#textDesc").css('display' , "none");
		$('.progresbarpercent').css('display' , "inline-block");
	
	
	
	
	$.ajax({
		url: "/majora/report",
		type: "POST",
		data: fd,
		processData: false,
		contentType: false,
		xhr: function() {
			var xhr = $.ajaxSettings.xhr();
			if (xhr.upload) {
				xhr.upload.addEventListener('progress', function(evt) {
					var percent = ((evt.loaded / evt.total) * 100).toFixed(0);
					$('#progresUploadBar').width(percent + '%');
					$('.progresbarpercent').html(percent + '%');
				}, false);
			}
			return xhr;
		},
		success: function(response) {
			console.log("#"+response);
			$(this).delay(2000);
			window.location.href = response;

			
		},

		error: function(jqXHR, textStatus, errorMessage) {
			console.log("this is the error " + errorMessage);
			window.location.href = "/majora/error";
		}
	});
});

$(document).on("blur", ".name, .link_Link",function(e){
	if($(".name").val().length>0 && $(".link_Link").val().length>0) {
		console.log("false");
		$(".commandButtonLink").removeClass("disabled");
	} else {
		console.log("true");
		$(".commandButtonLink").addClass( "disabled" );
	}
});


$(document).on("change", "#file",function(e){

	fileVar = $('input[type=file]')[0].files[0];
	effectOnFileLoad();
});

function effectOnFileLoad(){
	$("#inputToToggle").toggle("slow");
	$( "#messages" ).css('display' , "inline-block");
	$( "#messages" ).html("<h3>" + fileVar.name + "</h3>");
	$( "#buttonSendFile" ).prop('disabled', false);
	$( "#textDesc").css('display' , "inline-block");
}