<HTML>
<HEAD>
<TITLE>Book Details Modified</TITLE>
</HEAD>

<BODY bgcolor="#CCFFFF">
	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2>Book Details Modified</H2>
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
		//Statement stat = null;
		PreparedStatement stat = null;
		//Statement stat1 = null;
		PreparedStatement stat1 = null;
		ResultSet rs = null;

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
		
		
		int rs_int;
		int int_BOOKID,int_CATEGORYID,int_PRICE,int_QUANTITY;
		
		String query=null;
		String str_TITLE,str_AUTHOR,str_PUBLISHER,str_EDITION,str_DESCRIPTION;

 		int_BOOKID=Integer.parseInt(request.getParameter("bookid"));
 		int_CATEGORYID=Integer.parseInt(request.getParameter("categoryid"));
 		str_TITLE=request.getParameter("btitle");
 		str_AUTHOR=request.getParameter("bauthor");
 		str_PUBLISHER=request.getParameter("bpub");
 		str_EDITION=request.getParameter("bedition");
 		int_PRICE=Integer.parseInt(request.getParameter("bprice"));
 		int_QUANTITY=Integer.parseInt(request.getParameter("bqty"));
 		str_DESCRIPTION=request.getParameter("bdesc");
 
 		
		query="update book_details set categoryid=?,title=?,author=?,publisher=?,edition=?,price=?,quantity=?,description=? where bookid=?";
		stat = con.prepareStatement(query);
		stat.setInt(1,int_CATEGORYID);
		stat.setString(2,str_TITLE);
		stat.setString(3,str_AUTHOR);
		stat.setString(4,str_PUBLISHER);
		stat.setString(5,str_EDITION);
		stat.setInt(6,int_PRICE);
		stat.setInt(7,int_QUANTITY);
		stat.setString(8,str_DESCRIPTION);
		stat.setInt(9,int_BOOKID);
		
                rs_int=stat.executeUpdate();

		if (rs_int==1)
		{
			out.println("Book details for Book Id "+int_BOOKID+"  successfully modified.");	
		}
		else
		{
			out.println("Sorry Transaction not Completed");
		}
 	%>
 	<BR><BR>
	<a href="Logout.jsp">Click here to logout</a>
	<BR><BR>
	<a href="Admin.htm">Click here to go to administrator page</a>
	<BR><BR>
	<a href="Modify.jsp"> Click here to modify more books</a>
</BODY>
</HTML>
