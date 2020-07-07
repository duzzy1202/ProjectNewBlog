<%@ page import="com.Dto.java.blog.CateItem"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>

<%
	List<CateItem> cateItems = (List<CateItem>) request.getAttribute("cateItems");
%>
<div class="doWrite-box">
	<div class="con">
		<div class="doWrite-title">
			<h1>글 작성하기</h1>
		</div>
		<div class="doWrite-body">
			<form>
				<select name="select-cate" id="select-cate">
					<%
						for (CateItem cateItem : cateItems) {
							String cateItemName = cateItem.getName();
							int cateItemId = cateItem.getId();
					%>
					<option value="<%=cateItemId%>"> <%=cateItemName%> </option>
					<%
						}
					%>
				</select>
			</form>
		</div>
	</div>
</div>

<%@ include file="/jsp/part/foot.jspf"%>