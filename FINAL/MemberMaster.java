import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MemberMaster extends HttpServlet
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
				query = "DELETE FROM MemberMaster WHERE MemberID IN(" + request.getParameter("hidSelDel") + ")";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/MemberMaster");
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
				query = "UPDATE MemberMaster SET MemberName = '" + request.getParameter("txtMemberName") + "', Address = '" + request.getParameter("txtAddress") + "', Qualification = '" + request.getParameter("txtQualification") + "', Email = '" + request.getParameter("txtEmail") + "', Age = '" + request.getParameter("txtAge") + "', Sex = '" + request.getParameter("txtSex") + "', PhoneNumber = '" + request.getParameter("txtPhoneNumber") + "', DateofIssue = '" + request.getParameter("txtDateofIssue") + "', DateofReturn = '" + request.getParameter("txtDateofReturn") + "' WHERE MemberID = '" + request.getParameter("hidMemberID")  + "'";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/MemberMaster");
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
				query = "SELECT MemberID FROM MemberMaster  (MemberID = '" + request.getParameter("txtMemberID") + "')";
				stmt.executeUpdate(query);
				response.sendRedirect("/FINAL/MyServlets/MemberMaster");
			}
			catch(Exception e)
			{
				out.println("Sorry failed to update values from the database table. " + e.getMessage());
			}

		}
		if("I".equals(request.getParameter("hidMode")) && conn != null)
		{
			String MemberName = request.getParameter("txtMemberName");
			String Address = request.getParameter("txtAddress");
			String Qualification = request.getParameter("txtQualification");
			String Email = request.getParameter("txtEmail");
			String Age = request.getParameter("txtAge");
			String Sex = request.getParameter("txtSex");
			String PhoneNumber = request.getParameter("txtPhoneNumber");
                        String DateofIssue = request.getParameter("txtDateofIssue");
                        String DateofReturn = request.getParameter("txtDateofReturn");



			try
			{
				if(MemberName.length() > 0  && Address.length() > 0  && Qualification.length() > 0  && Email.length() > 0 && Age.length() > 0 && Sex.length() > 0 && PhoneNumber.length() > 0 && DateofIssue.length() > 0 && DateofReturn.length() > 0)
				{
					stmt = conn.createStatement();
					query = "INSERT INTO MemberMaster (MemberName, Address, Qualification, Email, Age, Sex, PhoneNumber, DateofIssue, DateofReturn) VALUES ('" + MemberName + "','" + Address + "','" + Qualification + "','" + Email + "','" + Age + "','" + Sex + "','" + PhoneNumber + "','" + DateofIssue + "','" + DateofReturn + "')";
					stmt.executeUpdate(query);
					response.sendRedirect("/FINAL/MyServlets/MemberMaster");
				}
				else
				{
					out.println("Member details cannot be left blank.");
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
					out.println("document.frmMember.txtMemberName.value='';");
					out.println("document.frmMember.txtAddress.value='';");
					out.println("document.frmMember.txtQualification.value='';");
					out.println("document.frmMember.txtEmail.value='';");
					out.println("document.frmMember.txtAge.value='';");
					out.println("document.frmMember.txtSex.value='';");
					out.println("document.frmMember.txtPhoneNumber.value='';");
                                        out.println("document.frmMember.txtDateofIssue.value='';");
                                        out.println("document.frmMember.txtDateofReturn.value='';");

					out.println("}");

					out.println("function setDelMode()");
					out.println("{");
					out.println("document.frmMember.hidMode.value='D';");
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

					out.println("function setEditMode(MemberID, MemberName, Address, Qualification, Email, Age, Sex, PhoneNumber)");
					out.println("{");
					out.println("document.frmMember.hidMemberID.value = MemberID;");
					out.println("document.frmMember.txtMemberName.value = MemberName;");
					out.println("document.frmMember.txtAddress.value = Address;");
					out.println("document.frmMember.txtQualification.value = Qualification;");
					out.println("document.frmMember.txtEmail.value = Email;");
					out.println("document.frmMember.txtAge.value = Age;");
					out.println("document.frmMember.txtSex.value = Sex;");
					out.println("document.frmMember.txtPhoneNumber.value = PhoneNumber;");
                                        out.println("document.frmMember.txtDateofIssue.value = DateofIssue;");
                                        out.println("document.frmMember.txtDateofReturn.value = DateofReturn;");

					out.println("document.frmMember.hidMode.value='U';");
					out.println("}");

				out.println("</SCRIPT>");
				out.println("<TITLE>Member Master Form</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY BGCOLOR='pink'>");
				out.println("<FORM ACTION='/FINAL/MyServlets/MemberMaster' METHOD='post' NAME='frmMember'>");
					out.println("<INPUT NAME='hidMode' TYPE='hidden' VALUE='I'>");
					out.println("<INPUT NAME='hidSelDel' TYPE='hidden'>");
					out.println("<INPUT NAME='hidMemberID' TYPE='hidden'>");
					out.println("<TABLE ALIGN='center' BGCOLOR='pink' CELLPADDING='0' CELLSPACING='0' NAME='tblouter' WIDTH='50%'>");
						out.println("<TR HEIGHT='200' VALIGN='top'>");
							out.println("<TD ALIGN='center' COLSPAN='10'>");
								out.println("<TABLE ALIGN='center' BGCOLOR='pink' BORDER='1' BORDERCOLOR='maroon' CELLPADDING='2' CELLSPACING='0' NAME='tblFirstChild' WIDTH='100%'>");
									out.println("<TR>");
										out.println("<TD ALIGN='left' COLSPAN='2' BGCOLOR='maroon'>");
											out.println("<FONT COLOR='pink'>Member Master</FONT>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Member Name</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtMemberName' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Address</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtAddress' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Qualification</TD>");
										out.println("<TD ALIGN='left'>");
								       		out.println("<INPUT MAXLENGTH='35' NAME='txtQualification' TYPE='text' SIZE='25'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Email</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='35' NAME='txtEmail' TYPE='text' SIZE='25'>");
									out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Age</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtAge' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Sex</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtSex' TYPE='text' SIZE='42'>");
										out.println("</TD>");
									out.println("</TR>");
									out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Phone Number</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtPhoneNumber' TYPE='text' SIZE='20'>");
										out.println("</TD>");
									out.println("</TR>");
                                                                        out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Date of Issue</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtDateofIssue' TYPE='text' SIZE='20'>");
										out.println("</TD>");
									out.println("</TR>");
                                                                        out.println("<TR>");
										out.println("<TD ALIGN='right' WIDTH='25%'>Date of Return</TD>");
										out.println("<TD ALIGN='left'>");
											out.println("<INPUT MAXLENGTH='255' NAME='txtDateofReturn' TYPE='text' SIZE='20'>");
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
					query = "SELECT * FROM MemberMaster";
					rs = stmt.executeQuery(query);
					out.println("<TABLE ALIGN='center' BORDER='1' WIDTH='50%' BORDERCOLOR='skyblue' CELLPADDING='0' CELLSPACING='0' NAME='tblSecondChild'>");
						out.println("<TR BGCOLOR='black'>");
							out.println("<TD WIDTH='12%' ALIGN='center'><INPUT NAME='cmdDelete' TYPE='button' VALUE='Delete' onClick='setDelMode();'></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Member Name</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Address</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Qualification</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Email</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Age</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Sex</FONT></TD>");
							out.println("<TD><FONT COLOR='#FFFFFF'>Phone Number</FONT></TD>");
                                                        out.println("<TD><FONT COLOR='#FFFFFF'>Date of Issue</FONT></TD>");
                                                        out.println("<TD><FONT COLOR='#FFFFFF'>Date of Return</FONT></TD>");

						out.println("</TR>");
					if(rs!=null)
					{
						while(rs.next())
						{
							out.println("<TR>");
								out.println("<TD><INPUT TYPE='checkbox' NAME='chk" + rs.getString("MemberID") + "' VALUE='" + rs.getString("MemberID") + "'></TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("MemberName") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("Address") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("Qualification") + "</TD>");

								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("Email") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("Age") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("Sex") + "</TD>");
								out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("PhoneNumber") + "</TD>");
                                                                out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("DateofIssue") + "</TD>");
                                                                out.println("<TD STYLE=\"cursor:pointer\" onMouseDown=\"setEditMode('" + rs.getString("MemberID") + "', '" + rs.getString("MemberName") + "', '" + rs.getString("Address") + "', '" + rs.getString("Qualification") + "', '" + rs.getString("Email") + "', '" + rs.getString("Age") + "', '" + rs.getString("Sex") + "', '" + rs.getString("PhoneNumber") + "', '" + rs.getString("DateofIssue") + "', '" + rs.getString("DateofReturn") + "');\">" + rs.getString("DateofReturn") + "</TD>");
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
