<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="inc/header.jsp"%>
<div class="container_12">
	<div class="grid_12">
		<div class="module">
			<p style="background: yellow; color: red; font-weight: bold">${err}</p>
			<h2>
				<span>Thêm loại hoa</span>
			</h2>
			<div class="module-body">
				
				<form action="<%=request.getContextPath()%>/them-loai-hoa" method="post">
					<div>
						<label>Tên loại hoa</label>
						<input type="text" name="loaihoa" value="${loaiHoa}" />
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
<%@include file="inc/footer.jsp"%>