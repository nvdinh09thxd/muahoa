<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.LoaiHoa"%>
<%@ page import="model.dao.LoaiHoaDAO"%>

<%@include file="inc/header.jsp"%>
<script src="<%=request.getContextPath()%>/muahoa/js/jquery-3.3.1.min.js"></script>
<div class="container_12">
	<div class="grid_12">
		<div class="module">
			<p style="background: yellow; color: green; font-weight: bold"></p>
			<h2>
				<span>Thêm loại hoa</span>
			</h2>
			<div class="module-body">
				<form action="javascript: void(0)" id="form">
					<div>
						<label>Tên loại hoa</label> 
						<input type="text" id="loaihoa" name="loaihoa" />
					</div>
					<div>
						<input type="submit" value="Thêm" /> 
						<input type="reset" value="Nhập lại" />
					</div>
				</form>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
<div style="clear: both;"></div>
<script type="text/javascript">
	$('form').submit(function() {
		var loaihoa = $("#loaihoa").val();
		if(loaihoa=="")
			alert("Vui lòng nhập lại!");
		else{
			var fd = new FormData(document.getElementById("form"));
			$.ajax({
				url: '<%=request.getContextPath()%>/them-loai-hoa',
				type : 'post',
				data : fd,
				dataType : 'text',
				processData : false,
		        contentType : false,
				success : function(data) {
					$("p").html(data);
				},
				error : function() {
					alert('Có lỗi xảy ra');
				}
			});
		}
	})
</script>
<%@include file="inc/footer.jsp"%>