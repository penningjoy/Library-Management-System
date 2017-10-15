<html>

<head>
<title>Search Items</title>
</head>

<body bgcolor="#CCFFFF">
	<CENTER>
	<H1> <u>Online Shopping Cart</u> </H1>
	</CENTER>

	<SCRIPT language="JavaScript">
  
  	function startSearch()
  	{
	 	if(document.frm.R1[1].checked)
  		{
  			if(document.frm.BookTitle.value=="")
			{
				alert("You must enter a book title");
				return;
			}
  		}
    		
  		if(document.frm.R1[2].checked)
  		{
	  		if(document.frm.BookAuthor.value=="")
			{
				alert("You must enter an author name");
			return;
			}
  		}	
  	
  		if(document.frm.R1[3].checked)
  		{
  			if(document.frm.BookPublisher.value=="")
			{
			alert("You must enter a publisher name");
			return;
			}
  		}
  	
  		document.frm.action="search.jsp";
  		document.frm.submit();
 	}

  	</SCRIPT>
  
  	<%@ page errorPage="errorpage.jsp" import="java.net.*" %>
	<%@ page import="java.io.*" %>
	<%@ page import="java.sql.*" %>
	<%
	Connection con = null;

	PreparedStatement stat = null;
	ResultSet rs = null;
	int ctr=0, flag=0;
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:Data","scott","tiger"); 

		stat = con.prepareStatement("select * from category_details");

                rs=stat.executeQuery();
	%>


	<form name ="frm" method="POST">
	<center><font size="5">Search Books</font></p></center>
    	<BR><BR>
  
  	<table border="0" width="100%" height="196">
    	<tr>
      		<td width="17%" align="right" height="25"><input type="radio" value="Category" checked name="R1"></td>
      		<td width="32%" height="25">Search by Category</td>
      		<td width="51%" height="25">
      			<select size="1" name="BookCategory">
      			<option selected value="Select">Select Category</option>
				<%
				while(rs.next())
				{
				String category=rs.getString(2);%>
	    			<option value="<%=category%>"><%=category%></option>
  				<%}%>
    			</select>
    	      	</td>
    	</tr>
    	<tr>
      		<td width="17%" align="right" height="25"><input type="radio" name="R1" value="Title"></td>
      		<td width="32%" height="25">Search by Book Title</td>
      		<td width="51%" height="25"><input type="text" name="BookTitle" size="25"></td>
    	</tr>
    	<tr>
    		<td width="17%" align="right" height="25"><input type="radio" name="R1" value="Author"></td>
      		<td width="32%" height="25">Search by Book Author</td>
      		<td width="51%" height="25"><input type="text" name="BookAuthor" size="25"></td>
    	</tr>
    	<tr>
      		<td width="17%" align="right" height="25"><input type="radio" name="R1" value="Publisher"></td>
      		<td width="32%" height="25">Search by Book Publisher</td>
      		<td width="51%" height="25"><input type="text" name="BookPublisher" size="25"></td>
    	</tr>
    	<tr>      
   	</tr>
    	<tr>
      		<td width="17%" align="right" height="21"></td>
      		<td width="32%" height="21">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="    Search    " name="Search" onClick="startSearch();"></td>
      		<td width="51%" height="21"><a href="Logout.jsp">Click here to logout</a></td>
    	</tr>
  	</table>
 
	</form>
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
</body>
</html>
