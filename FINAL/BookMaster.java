import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BookMaster extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection conn = null;
		ResultSet rs;
		Statement stmt = null;

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String query = null;

		try
		{
			String username = "root";
			String password = "WEBEL";
			String url = "jdbc:mysql://localhost/test";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			out.println("Sorry failed to connect to the Database. " + e.getMessage());
		}

		if("D".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "DELETE FROM BookMaster WHERE BookID IN(" + request.getParameter("hidSelDel") + ")";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/BookMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to delete values from the database table. " + e.getMessage());
			}
		}
		if("U".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "UPDATE BookMaster SET BookName = '" + request.getParameter("txtBookName") + "', BookAuthor = '" + request.getParameter("txtBookAuthor") + "', BookPublisher = '" + request.getParameter("txtBookPublisher") + "', Synopsis = '" + request.getParameter("txtSynopsis") + "', Subject = '" + request.getParameter("txtSubject") + "', Quantity = '" + request.getParameter("txtQuantity") + "', ISBNNumber = '" + request.getParameter("txtISBNNumber") + "' WHERE BookID = '" + request.getParameter("hidBookID")  + "'";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/BookMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}
                }
        if("S".equals(request.getParameter("hidMode")) && conn != null)
		{
			try
			{
				stmt = conn.createStatement();
				query = "SELECT BookID FROM BookMaster  (BookID = '" + request.getParameter("txtBookID") + "')";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/BookMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}

		}
		if("I".equals(request.getParameter("hidMode")) && conn != null)
		{
			String BookName = request.getParameter("txtBookName");
			String BookAuthor = request.getParameter("txtBookAuthor");
			String BookPublisher = request.getParameter("txtBookPublisher");
			String Synopsis = request.getParameter("txtSynopsis");
			String Subject = request.getParameter("txtSubject");
			String Quantity = request.getParameter("txtQuantity");
			String ISBNNumber = request.getParameter("txtISBNNumber");
			

			try
			{
				if(BookName.length() > 0 && BookAuthor.length() > 0  && BookPublisher.length() > 0  && Synopsis.length() > 0  && Subject.length() > 0 && Quantity.length() > 0  && ISBNNumber.length() > 0)
				{
					stmt = conn.createStatement();
					query = "INSERT INTO BookMaster (BookName, BookAuthor, BookPublisher, Synopsis, Subject, Quantity, ISBNNumber) VALUES ('" + BookName + "','" + BookAuthor + "','" + BookPublisher + "','" + Synopsis + "','" + Subject + "','" + Quantity + "','" + ISBNNumber + "')";
					stmt.executeUpdate(query);
					response.sendRedirect("/FINAL/MyServlets/BookMaster");
				}
				else
				{
					out.println("Book details cannot be left blank.");
				}
			}
			catch(Exception e)
			{
				out.println("Sorry failed to insert values into the Database table. " + e.getMessage());
			}
		}
		out.println("<HTML>");
			out.println("<HEAD>");
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
					out.println("function setMode() {");
					out.println("document.frmBook.txtBookName.value='';");
					out.println("document.frmBook.txtBookAuthor.value='';");
					out.println("document.frmBook.txtBookPublisher.value='';");
					out.println("document.frmBook.txtSynopsis.value='';");
					out.println("document.frmBook.txtSubject.value='';");
					out.println("document.frmBook.txtQuantity.value='';");
					out.println("document.frmBook.txtISBNNumber.value='';");
					
					out.println("}");

					out.println("function setDelMode()");
					out.println("{");
					out.println("document.frmBook.hidMode.value='D';");
					out.println("formDeleteValues('hidSelDel');");
					out.println("}");

					out.println("function formDeleteValues(hidden)");
					out.println("{");
					out.println("var selValues = '';");
					out.println("for (i=0;i<document.forms[0].elements.length;i++)");
					out.println("{");
					out.println("if(document.forms[0].elements[i].type == \"checkbox\")");
					out.println("{");
					out.println("if (document.forms[0].elements[i].checked == true) {");
					out.println("selValues = selValues + document.forms[0].elements[i].value + \",\";");
					out.println("}");
					out.println("}");
					out.println("}");
					out.println("if (selValues.length < 1)");
					out.println("{");
					out.println("alert(\"Please choose records you wish to delete.\");");
					out.println("}");
					out.println("else");
					out.println("{");
					out.println("selValues = selValues.substring(0,selValues.length-1);");
					out.println("eval(\"document.forms[0].\"+hidden+\".value = '\" +selValues+\"';\");");
					out.println("document.forms[0].submit();");
					out.println("}");
					out.println("}");

					out.println("function setEditMode(BookID, BookName, BookAuthor, BookPublisher, Synopsis, Subject, Quantity, ISBNNumber)");
					out.println("{");
					out.println("document.frmBook.hidBookID.value = BookID;");
					out.println("document.frmBook.txtBookName.value = BookName;");
					out.println("document.frmBook.txtBookAuthor.value = BookAuthor;");
					out.println("document.frmBook.txtBookPublisher.value = BookPublisher;");
					out.println("document.frmBook.txtSynopsis.value = Synopsis;");
					out.println("document.frmBook.txtSubject.value = Subject;");
					out.println("document.frmBook.txtQuantity.value = Quantity;");
					out.println("document.frmBook.txtISBNNumber.value = ISBNNumber;");
					
					out.println("document.frmBook.hidMode.value='U';");
					out.println("}");

				out.println("</SCRIPT>");
				out.println("<TITLE>Book Master Form</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY BGCOLOR='pink'>");
				out.println("<FORM ACTION='/FINAL/MyServlets/BookMaster' METHOD='post' NAME='frmBook'>");
					out.println("<INPUT NAME='hidMode' TYPE='hidden' VALUE='I'>");
					out.println("<INPUT NAME='hidSelDel' TYPE='hidden'>");
					out.println("<INPUT NAME='hidBookID' TYPE='hidden'>");
					out.println("<TABLE ALIGN='center' BGCOLOR='pink' CELLPADDING='0' CELLSPACING='0' NAME='tblouter' WIDTH='50%'>");
						out.println("<TR HEIGHT='200' VALIGN='top'>");
							out.println("<TD ALIGN='center' COLSPAN='10'>");
								out.println("<TABLE ALIGN='center' BGCOLOR='pink' BORDER='1' BORDERCOLOR='maroon' CELLPADDING='2' CELLSPACING='0' NAME='tblFirstChild' WIDTH='100%'>");
									out.println("<TR>");
										out.println("<TD ALIGN='left' COLSPAN='2' BGCOLOR='maroon'>");
											out.println("<FONT COLOR='pink'>Book Master</FONT>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Book Name</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtBookName' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Book Author</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtBookAuthor' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Book Publisher</TD>");
										out.println("<TD ALIGN='left'>");
								       		out.println("<INPUT MAXLENGTH='35' NAME='txtBookPublisher' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Synopsis</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtSynopsis' TYPE='text' SIZE='25'>");
									out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Subject</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtSubject' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Quantity</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtQuantity' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>ISBN Number</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtISBNNumber' TYPE='text' SIZE='20'>");
										out.println("</TD>");
									out.println("</TR>");
									
									out.println("<TR>");
										out.println("<TD COLSPAN='2' ALIGN='right'>");
											out.println("<INPUT NAME='cmdSubmit' TYPE='submit' VALUE='Save'>");
											out.println("<INPUT NAME='cmdCancel' onclick='setMode();' TYPE='button' VALUE='Cancel'>");
										out.println("</TD>");
									out.println("</TR>");
								out.println("</TABLE>");
							out.println("</TD>");
						out.println("</TR>");
					out.println("</TABLE>");

			if(conn != null)
			{
				try
				{
					stmt = conn.createStatement();
					query = "SELECT * FROM BookMaster";
					rs = stmt.executeQuery(query);
					out.println("<TABLE ALIGN='center' BORDER='1' WIDTH='50%' BORDERCOLOR='skyblue' CELLPADDING='0' CELLSPACING='0' NAME='tblSecondChild'>");
						out.println("<TR BGCOLOR='black'>");
							out.println("<TD WIDTH='12%' ALIGN='center'><INPUT NAME='cmdDelete' TYPE='button' VALUE='Delete' onClick='setDelMode();'></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Book Name</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Book Author</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Book Publisher</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Synopsis</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Subject</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Quantity</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>ISBN Number</FONT></TD>");
							
						out.println("</TR>");
					if(rs!=null)
					{
						while(rs.next())
						{
							out.println("<TR>");
								out.println("<TD><INPUT TYPE='checkbox' NAME='chk" + rs.getString("BookID") + "' VALUE='" + rs.getString("BookID") + "'></TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("BookName") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("BookAuthor") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("BookPublisher") + "</TD>");

								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("Synopsis") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("Subject") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("Quantity") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("BookID") + "', '" + rs.getString("BookName") + "', '" + rs.getString("BookAuthor") + "', '" + rs.getString("BookPublisher") + "', '" + rs.getString("Synopsis") + "', '" + rs.getString("Subject") + "', '" + rs.getString("Quantity") + "', '" + rs.getString("ISBNNumber") + "');\">" + rs.getString("ISBNNumber") + "</TD>");
							    

							out.println("</TR>");
						}
					}
						out.println("</TABLE>");
						conn.close();
				}
				catch(Exception e)
				{
					out.println("Sorry Failed to execute the query. " + e.getMessage());
				}
			}
			out.println("</FORM>");
		out.println("</BODY>");
	out.println("</HTML>");
	out.close();
	}
}
