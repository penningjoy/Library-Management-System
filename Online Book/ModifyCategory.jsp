<HTML>

<HEAD>
<TITLE>Modify Category</TITLE>
</HEAD>

<BODY bgcolor="#CCFFFF">
	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	</CENTER>
	<BR>
	<HR>
	<BR>
	<BR>

	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
		Connection con = null;

		PreparedStatement stat = null;
		int rs = 0;
		String query=null;
		try
		{
		String new_cat="";
		int cat_id=0;
		
		new_cat=request.getParameter("vr_cat");
		cat_id=Integer.parseInt(request.getParameter("cat_val"));

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
		
		
		query="UPDATE category_details SET CATEGORYNAME=? WHERE CATEGORYID=?";
		
		stat = con.prepareStatement(query);
		stat.setString(1,new_cat);
		stat.setInt(2,cat_id);
		
		rs=stat.executeUpdate();
		
		if (rs==1)
		{
			out.println("Category Successfully Modified");
		}
		else
		{
			out.println("Transaction Not Allowed");
		}
		}
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
		finally
		{
			con.close();
		}
	%>
		<BR>
		<BR>
		<a href="Logout.jsp">Click here to logout</a>
		<BR><BR>
		<a href="Admin.htm">Click here to go to administrator page</a>
		<BR><BR>
		<a href="Update_Category.jsp"> Click here to modify more categories</a>
</BODY>
</HTML>
