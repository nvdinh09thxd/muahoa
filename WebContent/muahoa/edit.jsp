<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp"%>
<script
	src="<%=request.getContextPath()%>/muahoa/js/jquery-3.3.1.min.js"></script>
<div class="container_12">
	<div class="grid_12">

		<div class="module">
			<h2>
				<span>Mua hoa</span>
			</h2>

			<div class="module-body">
				<%
					int id = 1;// = Integer.parseInt(request.getParameter("id"));
					String tenHoa = request.getParameter("ten");
					int id_loaiHoa=2;// = Integer.parseInt(request.getParameter("loaihoa"));
					int soLuong=2;// = Integer.parseInt(request.getParameter("soluong"));
					String hinhAnh = request.getParameter("hinhanh");
					String moTa = request.getParameter("mota");
					Hoa itemHoa = (Hoa) request.getAttribute("itemHoa");
					if(itemHoa!=null){
						id = itemHoa.getId();
						tenHoa = itemHoa.getTenHoa();
						id_loaiHoa = itemHoa.getId_loaihoa();
						soLuong = itemHoa.getSoLuong();
						hinhAnh = itemHoa.getHinhAnh();
						moTa = itemHoa.getMoTa();
					}
				%>
				
				<form action="javascript: void(0)" id="form">
					<input type="hidden" value="<%out.print(id);%>" name="id" />
					<div>
						<label>Tên hoa</label>
						<input type="text" value="<%if(tenHoa!=null) out.print(tenHoa);%>" id="ten" name="ten" class="input-medium" />
					</div>
					<div>
						<label>Loại hoa</label> 
						<select class="input-short" id="loaihoa" name="loaihoa">
							<%
								ArrayList<LoaiHoa> listCat = LoaiHoaDAO.getListCat();
									if (listCat != null) {
										for (LoaiHoa objCat : listCat) {
											if (objCat.getId()==(id_loaiHoa)) {
							%>
							<option value="<%=objCat.getId()%>" selected="selected"><%=objCat.getTenLoaiHoa()%></option>
							<%
								} else {
							%>
							<option value="<%=objCat.getId()%>"><%=objCat.getTenLoaiHoa()%></option>
							<%
								}
									}
								}
							%>
						</select>
					</div>
					<div>
						<label>Số lượng</label> 
						<input type="text" value="<%if(soLuong!=0) out.print(soLuong);%>" id="soluong" name="soluong" class="input-medium" />
					</div>
					<div>
						<label>Hình ảnh</label> 
						<input type="file" value="" id="hinhanh" name="hinhanh" />
					</div>
					<div>
						<label>Mô tả</label>
						<textarea rows="7" cols="90" id="mota" class="input-medium" name="mota"><%if(moTa!=null) out.print(moTa);%></textarea>
					</div>
					<fieldset>
						<input class="submit-green" type="submit" value="Sửa" />
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
				var fd = new FormData(document.getElementById("form"));
				$.ajax({
					url: '<%=request.getContextPath()%>/sua-hoa',
							type : 'POST',
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