import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AuthorMaster extends HttpServlet
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
				query = "DELETE FROM AuthorMaster WHERE AuthorID IN(" + request.getParameter("hidSelDel") + ")";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/AuthorMaster");
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
				query = "UPDATE AuthorMaster SET AuthorName = '" + request.getParameter("txtAuthorName") + "', Address = '" + request.getParameter("txtAddress") + "', Degree = '" + request.getParameter("txtDegree") + "', Designation = '" + request.getParameter("txtDesignation") + "', PhoneNumber = '" + request.getParameter("txtPhoneNumber") + "' WHERE AuthorID = '" + request.getParameter("hidAuthorID")  + "'";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/AuthorMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}
                }

   
       		if("I".equals(request.getParameter("hidMode")) && conn != null)
		{
			String AuthorName = request.getParameter("txtAuthorName");
			String Address = request.getParameter("txtAddress");
			String Degree = request.getParameter("txtDegree");
			String Designation = request.getParameter("txtDesignation");
			String PhoneNumber = request.getParameter("txtPhoneNumber");

			try
			{
				if(AuthorName.length() > 0 && Address.length() > 0 && Degree.length() > 0 && Designation.length() > 0 && PhoneNumber.length() > 0)
				{
					stmt = conn.createStatement();
					query = "INSERT INTO AuthorMaster (AuthorName, Address, Degree, Designation, PhoneNumber) VALUES ('" + AuthorName + "','" + Address + "','" + Degree + "','" + Designation + "','" + PhoneNumber + "')";
					stmt.executeUpdate(query);
					response.sendRedirect("/FINAL/MyServlets/AuthorMaster");
				}
				else
				{
					out.println("Author details cannot be left blank.");
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
					out.println("document.frmAuth.txtAuthorName.value='';");
					out.println("document.frmAuth.txtAddress.value='';");
					out.println("document.frmAuth.txtDegree.value='';");
					out.println("document.frmAuth.txtDesignation.value='';");
					out.println("document.frmAuth.txtPhoneNumber.value='';");
					out.println("}");

					out.println("function setDelMode()");
					out.println("{");
					out.println("document.frmAuth.hidMode.value='D';");
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

					out.println("function setEditMode(AuthorID, AuthorName, Address, Degree, Designation, PhoneNumber)");
					out.println("{");
					out.println("document.frmAuth.hidAuthorID.value = AuthorID;");
					out.println("document.frmAuth.txtAuthorName.value = AuthorName;");
					out.println("document.frmAuth.txtAddress.value = Address;");
					out.println("document.frmAuth.txtDegree.value = Degree;");
					out.println("document.frmAuth.txtDesignation.value = Designation;");
					out.println("document.frmAuth.txtPhoneNumber.value = PhoneNumber;");
					out.println("document.frmAuth.hidMode.value='U';");
					out.println("}");

				out.println("</SCRIPT>");
				out.println("<TITLE>Author Master Form</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY BGCOLOR='pink'>");
				out.println("<FORM ACTION='/FINAL/MyServlets/AuthorMaster' METHOD='post' NAME='frmAuth'>");
					out.println("<INPUT NAME='hidMode' TYPE='hidden' VALUE='I'>");
					out.println("<INPUT NAME='hidSelDel' TYPE='hidden'>");
					out.println("<INPUT NAME='hidAuthorID' TYPE='hidden'>");
					out.println("<TABLE ALIGN='center' BGCOLOR='pink' CELLPADDING='0' CELLSPACING='0' NAME='tblouter' WIDTH='50%'>");
						out.println("<TR HEIGHT='200' VALIGN='top'>");
							out.println("<TD ALIGN='center' COLSPAN='10'>");
								out.println("<TABLE ALIGN='center' BGCOLOR='pink' BORDER='1' BORDERCOLOR='maroon' CELLPADDING='2' CELLSPACING='0' NAME='tblFirstChild' WIDTH='100%'>");
									out.println("<TR>");
										out.println("<TD ALIGN='left' COLSPAN='2' BGCOLOR='maroon'>");
											out.println("<FONT COLOR='pink'>Author Master</FONT>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Author Name</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtAuthorName' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Address</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtAddress' TYPE='text' SIZE='25'>");
									out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Degree</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtDegree' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Designation</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtDesignation' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Pnone Number</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtPhoneNumber' TYPE='text' SIZE='20'>");
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
					query = "SELECT * FROM AuthorMaster";
					rs = stmt.executeQuery(query);
					out.println("<TABLE ALIGN='center' BORDER='1' WIDTH='50%' BORDERCOLOR='skyblue' CELLPADDING='0' CELLSPACING='0' NAME='tblSecondChild'>");
						out.println("<TR BGCOLOR='black'>");
							out.println("<TD WIDTH='12%' ALIGN='center'><INPUT NAME='cmdDelete' TYPE='button' VALUE='Delete' onClick='setDelMode();'></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Author Name</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Address</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Degree</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Designation</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Phone Number</FONT></TD>");
						out.println("</TR>");
					if(rs!=null)
					{
						while(rs.next())
						{
							out.println("<TR>");
								out.println("<TD><INPUT TYPE='checkbox' NAME='chk" + rs.getString("AuthorID") + "' VALUE='" + rs.getString("AuthorID") + "'></TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("AuthorID") + "', '" + rs.getString("AuthorName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Degree") + "', '" + rs.getString("Designation") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("AuthorName") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("AuthorID") + "', '" + rs.getString("AuthorName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Degree") + "', '" + rs.getString("Designation") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("Address") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("AuthorID") + "', '" + rs.getString("AuthorName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Degree") + "', '" + rs.getString("Designation") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("Degree") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("AuthorID") + "', '" + rs.getString("AuthorName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Degree") + "', '" + rs.getString("Designation") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("Designation") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("AuthorID") + "', '" + rs.getString("AuthorName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Degree") + "', '" + rs.getString("Designation") + "', '" + rs.getString("PhoneNumber") + "');\">" + rs.getString("PhoneNumber") + "</TD>");
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
