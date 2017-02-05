<%@ tag language='java' pageEncoding='GBK' body-content="scriptless"%>
<%@ tag import="java.util.*" %>

<%@ attribute name="bgcolor" required="true"%>
<%@ attribute name="cellcolor" required="true"%>
<%@ attribute name="collection" required="true" type="java.util.HashMap"%>

<%@ variable name-given="end" variable-class="java.util.Date" scope="AT_END"%>

<%
	Set<?> keys=collection.keySet();
	Iterator<?> iter=keys.iterator();
%>
¿ªÊ¼µü´ú&nbsp;<%=new Date()%><br>
<jsp:doBody/>
<table bgcolor="${bgcolor}" border="1">
<%
	while(iter.hasNext()) {
		String key=(String) iter.next();
%>
	<tr bgcolor="${cellcolor}">
		<td><%=key%></td>
		<td><%=collection.get(key)%></td>
	</tr>
<%}%>
</table>
<%
	jspContext.setAttribute("end", new Date());
%>