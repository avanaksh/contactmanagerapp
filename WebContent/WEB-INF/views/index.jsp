<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Manager</title>
</head>
<body>
<h1>Contact Manager Application</h1>
<div align="center">
            <h1>Contact List</h1>
            <h3><a href="/newContact">New Contact</a></h3>
            <table border="1">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Telephone</th>
                <th>Action</th>
                 </tr>
                <c:forEach var="contact" items="${listContact}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.address}</td>
                    <td>${contact.telephone}</td>
                    <td>
                        <a href="/editContact?id=${contact.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deleteContact?id=${contact.id}">Delete</a>
                    </td>                             
                </tr>
                </c:forEach>             
            </table>
        </div>
</body>
</html>