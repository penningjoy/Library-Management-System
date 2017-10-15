<HTML>
<HEAD>
<TITLE>Insert New Book</TITLE>
</HEAD>

<BODY bgcolor="#CCFFFF">
	<CENTER>
	<H1> Administering Online Shopping Cart </H1>
	<H2>Add Book</H2>
	</CENTER>
	<BR>
	<HR>

	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
	Connection con = null;
	
	PreparedStatement stat = null;
	
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
	

	ResultSet rs1 = null;

	int rs;
	String query=null;
	String query1=null;
	String TITLE;
	TITLE=request.getParameter("title");
	query1="Select a.* from book_details a where a.title=?";
	PreparedStatement stat1= con.prepareStatement(query1);
	stat1.setString(1,TITLE);
	rs1=stat1.executeQuery();

	if(rs1.next())
	{
	%>
		<SCRIPT language="JavaScript">
		alert("This book already exists.");
		location.href="Insert.jsp";
		</SCRIPT>
	<%
	}
	else
	{
 		int int_BOOKID,int_CATEGORYID,int_PRICE,int_QUANTITY;
 		String str_TITLE,str_AUTHOR,str_PUBLISHER,str_EDITION,str_DESCRIPTION;
	
 		int_BOOKID=(int)(10000*Math.random()+1);
 		int_CATEGORYID=Integer.parseInt(request.getParameter("cat"));
 		str_TITLE=request.getParameter("title");
 		str_AUTHOR=request.getParameter("author");
 		str_PUBLISHER=request.getParameter("pub");
 		str_EDITION=request.getParameter("edition");
 		int_PRICE=Integer.parseInt(request.getParameter("price"));
 		int_QUANTITY=Integer.parseInt(request.getParameter("qty"));
 		str_DESCRIPTION=request.getParameter("desc");
 

		query="insert into book_details values (?,?,?,?,?,?,?,?,?)";
 		stat = con.prepareStatement(query);
		stat.setInt(1,int_BOOKID);
		stat.setInt(2,int_CATEGORYID);
		stat.setString(3,str_TITLE);
		stat.setString(4,str_AUTHOR);		
		stat.setString(5,str_PUBLISHER);
		stat.setString(6,str_EDITION);
		stat.setInt(7,int_PRICE);
		stat.setInt(8,int_QUANTITY);
		stat.setString(9,str_DESCRIPTION);
		
		rs=stat.executeUpdate();
 		if(rs==1)
 		{%>
  		<BR>
 		<H3>Successfully inserted the data.</H3>
 		<% }
 		else
  		{%>
  			<BR>
 			<H3>Could not add the book</H3>
 		<%}
 	}%>

 	<BR>
	<a href="Logout.jsp">Click here to logout</a>
	<BR><BR>
	<a href="Admin.htm">Click here to go to administrator page</a>
	<BR><BR>
	<a href="Insert.jsp"> Click here to add more books</a>

 </BODY>
 </HTML>
