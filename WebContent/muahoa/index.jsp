<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="inc/header.jsp"%>
	<%
		String msg = request.getParameter("msg");
		if("1".equals(msg)){
		    out.print("<div style=\"background: yellow; color: green; font-weight: bold; padding: 4px\">Thêm loại hoa thành công!</div>");
	    }
	%>
	<div class="container_12">

		<div class="grid_main_l">
			<a href="<%=request.getContextPath() %>/mua-hoa" class="dashboard-module"> 
				<img src="<%=request.getContextPath() %>/muahoa/images/Crystal_Clear_write.gif" width="64" height="64" alt="edit" /> 
				<span>Thêm tin sản phẩm</span>
			</a> 
			<a href="<%=request.getContextPath() %>/xem-hoa" class="dashboard-module"> 
				<img src="<%=request.getContextPath() %>/muahoa/images/Crystal_Clear_files.gif" width="64" height="64" alt="edit" /> 
				<span>Xem danh sách</span>
			</a>
			<div style="clear: both"></div>
		</div>

		<div class="grid_main_r">
			<div class="module">
				<h2>
					<span>Quản trị hệ thống</span>
				</h2>
				<div class="module-body">
					<div class="p">
						<strong>Phần mềm</strong> được viết trên nền tảng PHP&MySQL <br />
						<strong>Học viên thực hiện: </strong>Trần Nguyễn Gia Huy<br /> 
						<strong>Email:</strong>huytng@vinatab.net<br /> 
						<strong>Phone: </strong>0909.123.456<br />
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="padding-bottom"></div>
		</div>
	</div>
	<div style="clear: both;"></div>
	
<%@include file="inc/footer.jsp"%>