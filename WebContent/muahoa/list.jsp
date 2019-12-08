<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp"%>

<div class="container_12">
	<div class="bottom-spacing">
		<div class="float-left">
			<a href="<%=request.getContextPath()%>/muahoa/add.jsp" class="button"> 
				<span>Thêm sản phẩm mới 
					<img src="<%=request.getContextPath()%>/muahoa/images/plus-small.gif" alt="Thêm tin mới">
				</span>
			</a>
		</div>
		<div class="clear"></div>
	</div>
	<%
		String msg = request.getParameter("msg");
		if("1".equals(msg)){
			out.print("<div style=\"background: yellow; color: green; font-weight: bold; padding: 4px\">Xóa thành công!</div>");
		}
		if("0".equals(msg)){
		    out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 4px\">Xóa thất bại!</div>");
	    }
	%>
	<div class="grid_12">
		<div class="module">
			<h2>
				<span>Danh sách hoa</span>
			</h2>
			<div class="module-table-body">
				<table id="myTable" class="tablesorter">
					<thead>
						<tr>
							<th style="width: 4%; text-align: center;">ID</th>
							<th>Tên hoa</th>
							<th style="width: 20%">Loại hoa</th>
							<th style="width: 20%">Số lượng</th>
							<th style="width: 16%; text-align: center;">Hình ảnh</th>
							<th style="width: 11%; text-align: center;">Chức năng</th>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<Hoa> listHoa = HoaDAO.getListHoa();
							if (listHoa != null) {
								for (Hoa objHoa : listHoa) {
						%>
						<tr>
							<td class="align-center"><%=objHoa.getId()%></td>
							<td><a href=""><%=objHoa.getTenHoa()%></a></td>
							<td><%=LoaiHoaDAO.getItemCat(objHoa.getId()).getTenLoaiHoa()%></td>
							<td><%=objHoa.getSoLuong()%></td>
							<td align="center">
								<img src="<%=request.getContextPath() %>/files/<%=objHoa.getHinhAnh()%>" alt="<%=objHoa.getHinhAnh()%>" class="hoa" style="width: 100px; height: 100px;" />
							</td>
							<td align="center">
								<a href="<%=request.getContextPath()%>/sua-hoa?id=<%=objHoa.getId()%>">Sửa
									<img src="<%=request.getContextPath()%>/muahoa/images/pencil.gif" alt="edit" />
								</a> 
								<a href="<%=request.getContextPath()%>/xoa-hoa?id=<%=objHoa.getId()%>">Xóa
									<img src="<%=request.getContextPath()%>/muahoa/images/bin.gif" width="16" height="16" alt="delete" />
								</a>
							</td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>

		<div class="pagination">
			<div class="numbers">
				<span>Page:</span> 
				<a href="">1</a> <span>|</span> 
				<a href="">2</a> <span>|</span> 
				<span class="current">3</span> <span>|</span> 
				<a href="">4</a> <span>|</span> 
				<a href="">5</a> <span>|</span> 
				<a href="">6</a> <span>|</span> 
				<a href="">7</a> <span>|</span> 
				<span>...</span> <span>|</span> 
				<a href="">99</a>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
</div>
<div style="clear: both;"></div>
<%@include file="inc/footer.jsp"%>