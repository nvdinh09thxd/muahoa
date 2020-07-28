<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.LoaiHoa"%>
<%@ page import="model.dao.LoaiHoaDAO"%>

<%@include file="inc/header.jsp"%>
<div class="container_12">
	<div class="grid_12">
	<p style="color: red;">${err}</p>
		<div class="module">
			<h2>
				<span>Mua hoa</span>
			</h2>

			<div class="module-body">
			<form action="<%=request.getContextPath()%>/them-hoa" method="post" enctype="multipart/form-data">
					<div>
						<label>ID hoa</label> 
						<input type="text" name="id" value="${idStr}" class="input-medium" />
					</div>
					<div>
						<label>Tên hoa</label> 
						<input type="text" name="ten" value="${tenHoa}" class="input-medium" />
					</div>
					<div>
						<label>Loại hoa</label> 
						<select class="input-short" name="loaihoa">
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
						<input type="text" name="soluong" value="${soLuongStr}" class="input-medium" />
					</div>
					<div>
						<label>Giá bán</label> 
						<input type="text" name="giaban" value="${giaBanStr}" class="input-medium" />
					</div>
					<div>
						<label>Hình ảnh</label> 
						<input type="file" name="hinhanh" />
					</div>
					<div>
						<label>Mô tả</label>
						<textarea rows="7" cols="90" class="input-medium" name="mota">${moTa}</textarea>
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

<%@include file="inc/footer.jsp"%>