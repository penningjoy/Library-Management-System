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

	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
		Connection con = null;
		
		PreparedStatement stat = null;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
		

		int rs;
		int int_CATEGORYID;
		
		String query=null;
		String str_CATEGORY;

 		int_CATEGORYID=(int)(10000*Math.random()+1);
 		str_CATEGORY=request.getParameter("cat_name");
 
 	
		query="insert into CATEGORY_details values (?,?)";
		stat = con.prepareStatement(query);
		stat.setInt(1,int_CATEGORYID);
		stat.setString(2,str_CATEGORY);
 		
		rs=stat.executeUpdate();
 		if (rs==1)
 		{
			%><H3>New Category Successfully Added</H3><%
		}
		else
		{
			%><H3>Transaction Not Allowed</H3><%
		}
 	%>
 	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>
	<BR><BR>
	<a href="Admin.htm"><font size="4"  >Click here to go to administrator page</font></a>
	<BR><BR>
	<a href="Insert_Category.jsp"><font size="4"  > Click here to add more categories</font></a>
	<BR><BR>
</BODY>
</HTML>
