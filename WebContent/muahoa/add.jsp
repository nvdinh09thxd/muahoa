<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.LoaiHoa"%>
<%@ page import="model.dao.LoaiHoaDAO"%>

<%@include file="inc/header.jsp"%>
<script	src="<%=request.getContextPath()%>/muahoa/js/jquery-3.3.1.min.js"></script>
<div class="container_12">
	<div class="grid_12">

		<div class="module">
			<h2>
				<span>Mua hoa</span>
			</h2>

			<div class="module-body">
				<form action="javascript: void(0)" id="form">
					<div>
						<label>ID hoa</label> 
						<input type="text" id="id" name="id" class="input-medium" />
					</div>
					<div>
						<label>Tên hoa</label> 
						<input type="text" id="ten" name="ten" class="input-medium" />
					</div>
					<div>
						<label>Loại hoa</label> 
						<select class="input-short" id="loaihoa" name="loaihoa">
							<%
								ArrayList<LoaiHoa> listCat = LoaiHoaDAO.getListCat();
									if (listCat != null) {
										for (LoaiHoa objCat : listCat) {
							%>
							<option value="<%=objCat.getId()%>"><%=objCat.getTenLoaiHoa()%></option>
							<%
										}
									}
							%>
						</select>
					</div>
					<div>
						<label>Số lượng</label> 
						<input type="text" id="soluong" name="soluong" class="input-medium" />
					</div>
					<div>
						<label>Hình ảnh</label> 
						<input type="file" id="hinhanh" name="hinhanh" />
					</div>
					<div>
						<label>Mô tả</label>
						<textarea rows="7" cols="90" id="mota" class="input-medium" name="mota"></textarea>
					</div>
					<fieldset>
						<input class="submit-green" type="submit" value="Mua hoa" /> 
						<input class="submit-gray" type="reset" value="Nhập lại" />
					</fieldset>
				</form>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
<div style="clear: both;"></div>

<script type="text/javascript">
	$('form').submit(function() {
		var id = $("#id").val();
		var ten = $("#ten").val();
		var loaihoa = $("#loaihoa").val();
		var soluong = $("#soluong").val();
		var hinhanh = $("#hinhanh").val();
		var mota = $("#mota").val();
		if(id==""||ten==""||loaihoa==""||soluong==""||hinhanh==""||mota=="")
			alert("Vui lòng nhập đầy đủ thông tin!");
		else{
			var formData = document.getElementById("form");
			var fd = new FormData(formData);
			$.ajax({
				url: '<%=request.getContextPath()%>/them-hoa',
				type : 'post',
				cache : false,
				data : fd,
				dataType : 'text',
				processData : false,
		        contentType : false,
				success : function(data) {
					alert(data);
				},
				error : function() {
					alert('Có lỗi xảy ra');
				}
			});
		}
	})
</script>
<%@include file="inc/footer.jsp"%>