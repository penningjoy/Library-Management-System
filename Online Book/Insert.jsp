<HTML>
<HEAD>
<TITLE>Insert New Book</TITLE>
</HEAD>
<BODY>       
	<CENTER>
	<H1> <u>Administering Online Shopping Cart</u> </H1>
	<H2> Add Book</H2>
	</CENTER>
	<BR>
	<HR>
	<BR>
	<a href="Logout.jsp" ><font size="4"  >Click here to Logout</font></a>

	<BODY bgcolor="#CCFFFF">
	<SCRIPT language="Javascript">
  	function check()
  	{
	var ctr=0;
  	if(document.addbook.title.value=="")
  	{
  		alert("Please Enter Title");
		return;
  	}
  	if(document.addbook.author.value=="")
  	{
  		alert("Please Enter Author");
		return;
  	}
  	if(document.addbook.pub.value=="")
  	{
  		alert("Please Enter Publisher Name");
		return;
  	}
  	if(document.addbook.edition.value=="")
  	{
  		alert("Please Enter Edition");
		return;
  	}
  	if(document.addbook.price.value=="")
  	{
  		alert("Please Enter Price (in Rs.)");
		return;
  	}
  	
  	if(document.addbook.qty.value=="")
  	{
  		alert("Please Enter Quantity");
		return;
  	}
  	if(document.addbook.desc.value=="")
  	{
  		alert("Please Enter Description");
		return;
  	}
  	if(isNaN(document.addbook.price.value))
  	{
  		alert("Please Enter a numeric value for Price (in Rs.)");
  		ctr=1;
  	}
  	if(isNaN(document.addbook.qty.value))
  	{
  		alert("Please Enter a numeric value for Quantity");
		ctr=1;
  	}
  	if(ctr==0)
  	{
  		document.forms[0].action="AddBook.jsp";
  		document.forms[0].submit();
  	}
  	else
  	{
		return true;  	
	};
	  	
  	}

  	</SCRIPT>
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
	ResultSet rs1 = null;

	String query=null;
	String query1=null;
	int ctr=0, flag=0;
	try
	{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 
	

	query="Select a.*,b.categoryname from book_details a, category_details b where a.categoryid=b.categoryid";
	query1="select * from category_details";
	
	stat = con.prepareStatement(query);
	stat1 = con.prepareStatement(query1);

	rs=stat.executeQuery();

	rs1=stat1.executeQuery();

	%>
	<br><h3>List of Available Books:</h3>
	<table border=1>
	<tr>
		<td>S.No</td>
		<td>Book Id</td>
		<td>Book Name</td>
		<td>Author Name</td>
		<td>Publisher</td>
		<td>Edition</td>
		<td>Price (in Rs.)</td>
		<td>Quantity Available(Nos.)</td>
		<td>Description</td>
		<td>Category</td>
	</tr>
	<% 
	int int_BOOKID,int_CATEGORYID,int_PRICE,int_QUANTITY;
	String str_TITLE,str_AUTHOR,str_PUBLISHER,str_EDITION,str_DESCRIPTION;
	while(rs.next())
	{
		ctr=ctr+1;
		out.println("<tr><td>"+ctr);
		int_BOOKID=rs.getInt(1);
		out.println("<td>");
		out.println(int_BOOKID);
				
		int_CATEGORYID=rs.getInt(2);
					
		str_TITLE=rs.getString(3);
		out.println("<td>");
		out.println(str_TITLE);
		
		str_AUTHOR=rs.getString(4);
		out.println("<td>");
		out.println(str_AUTHOR);
			
		str_PUBLISHER=rs.getString(5);
		out.println("<td>");
		out.println(str_PUBLISHER);
		
		str_EDITION=rs.getString(6);
		out.println("<td>");
		out.println(str_EDITION);
		
		int_PRICE=rs.getInt(7);
		out.println("<td>");
		out.println(int_PRICE);
		
		int_QUANTITY=rs.getInt(8);
		out.println("<td>");
		out.println(int_QUANTITY);

		str_DESCRIPTION=rs.getString(9);
		out.println("<td>");
		out.println(str_DESCRIPTION);

		out.println("<td>");
		out.println(rs.getString(10));
			
		out.println("</tr>");
	}
	out.println("</table>");%>
	<br>
	<hr>
	<h3> Add New Book Details</h3>
	<form name="addbook">
	<table border=1>
	<tr><td>Book Title</td><td><input type="text" name="title"></td>
	<tr><td>Author Name</td><td><input type="text" name="author"></td>
	<tr><td>Publisher</td><td><input type="text" name="pub"></td>
	<tr><td>Edition</td><td><input type="text" name="edition"></td>
	<tr><td>Price (in Rs.)</td><td><input type="text" name="price"></td>
	<tr><td>Quantity Available(Nos.)</td><td><input type="text" name="qty"></td>
	<tr><td>Description</td><td><input type="text" name="desc"></td>
	<tr><td>Category</td><td><select size="1" name="cat">
	<%
		while(rs1.next())
		{%>
    		<option value="<%=rs1.getString(1)%>"><%=rs1.getString(2)%></option>
  		<%}%>
    		</select></td>
		</tr>
		<tr><td><input type="submit" value="   Add    " onClick="check();"></td>
		<td><input type="Reset" value="    Clear    "></td>
		</table>
		</form>
	<%
	}
	catch(Exception e)
	{
	out.print("Error = " + e + "<HR>");
	}
	%>
</BODY>
</HTML>
