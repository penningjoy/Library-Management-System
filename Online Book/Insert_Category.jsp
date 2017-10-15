<HTML>

<HEAD>
<TITLE>Insert New Category</TITLE>
</HEAD>
<BODY bgcolor="#CCFFFF">
	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2> Add Category</H2>
	</CENTER>
	<BR>
	<HR>
	<BR>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>

	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
		Connection con = null;
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		String query=null;
		int ctr=0, flag=0;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
		
		
		query="select * from category_details";
		stat = con.prepareStatement(query);
		
		rs=stat.executeQuery();
		%>
		<br><h3>Add New Category:</h3>
		<form name="addbook" action="AddCategory.jsp">
		<table border=1>
		<tr>
		<td>Categories Available</td>
		<td><select size="1" name="cat">
			<%
			while(rs.next())
			{%>
    			<option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
  			<%}%>
    			</select></td>
		</tr>
		<tr>
		<td>Category to be added</td>
		<td><input type="text" name="cat_name"></td></tr>
		<tr><td><input type="submit" value="   Add    "></td><td><input type="Reset" value="   Clear    "></td>
		</table></form>
	<%
		}
		catch(Exception e)
		{
		out.print("Error = " + e + "<HR>");
		}
		finally
		{
		rs.close();
		con.close();
		}
	%>
</BODY>
</HTML>
