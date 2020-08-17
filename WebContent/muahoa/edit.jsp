<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp"%>

<div class="container_12">
	<div class="grid_12">
	<%
		String err = request.getParameter("err");
		if("0".equals(err)){
		    out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 4px\">Xảy ra lỗi trong quá trình xử lý!</div>");
	    }
		if("1".equals(err)){
		    out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 4px\">File bạn chọn không phải là file ảnh!</div>");
	    }
	%>
		<div class="module">
			<h2>
				<span>Sửa hoa</span>
			</h2>

			<div class="module-body">
				<%
					int id = 0;
					if(request.getParameter("id") != null){
						id = Integer.parseInt(request.getParameter("id"));
					}
					String tenHoa = request.getParameter("ten");
					int id_loaiHoa = 0;
					if(request.getParameter("loaihoa") != null){
						id_loaiHoa = Integer.parseInt(request.getParameter("loaihoa"));
					}
					int soLuong = 0;
					if(request.getParameter("soluong") != null){
						soLuong = Integer.parseInt(request.getParameter("soluong"));
					}
					float giaBan = 0;
					if(request.getParameter("giaban") != null){
						giaBan = Float.parseFloat(request.getParameter("giaban"));
					}
					String hinhAnh = (String) request.getAttribute("hinhanh");
					String moTa = request.getParameter("mota");
					Hoa itemHoa = (Hoa) request.getAttribute("itemHoa");
					if(itemHoa != null){
						tenHoa = itemHoa.getTenHoa();
						id_loaiHoa = itemHoa.getId_loaihoa();
						soLuong = itemHoa.getSoLuong();
						giaBan = itemHoa.getGiaBan();
						hinhAnh = itemHoa.getHinhAnh();
						moTa = itemHoa.getMoTa();
					}
				%>
				
				<form action="<%=request.getContextPath()%>/sua-hoa" method="post" enctype="multipart/form-data">
					<input type="hidden" value="<%out.print(id);%>" name="id" />
					<div>
						<label>Tên hoa</label>
						<input type="text" value="<%if(tenHoa!=null) out.print(tenHoa);%>" name="ten" class="input-medium" />
					</div>
					<div>
						<label>Loại hoa</label> 
						<select class="input-short" name="loaihoa">
							<%
								ArrayList<LoaiHoa> listCat = LoaiHoaDAO.getListCat();
									if (listCat.size() > 0) {
										for (LoaiHoa objCat : listCat) {
							%>
							<option value="<%=objCat.getId()%>" <%if(objCat.getId()==id_loaiHoa) out.print("selected");%>><%=objCat.getTenLoaiHoa()%></option>
							<%
									}
								}
							%>
						</select>
					</div>
					<div>
						<label>Số lượng</label> 
						<input type="text" value="<%if(soLuong!=0) out.print(soLuong);%>" name="soluong" class="input-medium" />
					</div>
					<div>
						<label>Giá bán</label> 
						<input type="text" value="<%if(giaBan!=0) out.print(giaBan);%>" name="giaban" class="input-medium" />
					</div>
					<div>
						<label>Hình ảnh</label> 
						<input type="file" value="" name="hinhanh" /><br />
						<img src="<%=request.getContextPath() %>/muahoa/upload/<%=hinhAnh%>" alt="<%=hinhAnh%>" class="hoa" style="width: 100px; height: 100px;" />
					</div>
					<div>
						<label>Mô tả</label>
						<textarea rows="7" cols="90" class="input-medium" name="mota"><%if(moTa!=null) out.print(moTa);%></textarea>
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
	<%@include file="inc/footer.jsp"%>