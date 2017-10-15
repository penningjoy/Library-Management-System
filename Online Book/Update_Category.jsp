<HTML>
<HEAD>
<TITLE>Category Modified</TITLE>
</HEAD>
<BODY bgcolor="#CCFFFF">
	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2>Category Modified</H2>
	</CENTER>
	<BR>
	<HR>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>
	<BR><BR>

	<script language="Javascript">
	function sel_click()
	{
		var f = document.modcat;
		var i=f.cat.selectedIndex;
		var str=f.cat.options[i].text;
		document.modcat.vr_cat.value=str;
		document.modcat.cat_val.value=document.modcat.elements[0].value;
	}
	</script>

	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
		Connection con = null;
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		String query=null;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
	
		query="select * from category_details";
		stat = con.prepareStatement(query);
	
		rs=stat.executeQuery();
	%>
		<br><h2>Modify Category:--</h2>
		<form name="modcat" action="ModifyCategory.jsp">
		<table border=1>
		<tr>
		<td>Category</td>
		<td><select size="1" name="cat" onChange="sel_click();">
			<%
			while(rs.next())
			{%>
    			<option value="<%=rs.getString(1)%>">
			<%=rs.getString(2)%></option>
  			<%}%>
    			</select></td>
		</tr>
		<tr><td colspan=2><input type="text" name="vr_cat"><input type="text" name="cat_val"></td>
		<tr><td><input type="submit" value="   Modify  "></td><td><input type="Reset" value="   Clear    "></td>
		</table></form>
	<%
		}
		catch(Exception e)
		{
		//	out.print("Error = " + e + "<HR>");
		}
		finally
		{
			rs.close();
			con.close();	
		}
	%>
</BODY>
</HTML>
