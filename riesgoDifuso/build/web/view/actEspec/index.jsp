<%-- 
    Document   : index
    Created on : 18/01/2018, 09:58:24 AM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%    session.invalidate();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activo Específico</title>
        <link href="../../jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/form.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/table.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/message.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../jq/menu/menu.css" rel="stylesheet" type="text/css"/>

        <script src="../../jq/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="../../jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../../parainfo/form.js" type="text/javascript"></script>
        <script src="../../parainfo/table.js" type="text/javascript"></script>
        <script src="../../parainfo/message.js" type="text/javascript"></script>
        <script src="../../jq/menu/menu.js" type="text/javascript"></script>
        <script src="../../js/main.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="m_main">
            <div id="m_menu">
                <%@include file="../../WEB-INF/jspf/menuadmin.jspf" %>
            </div>

            <div id="m_top">
                Usuario: ${usuarios.nombres} ${usuarios.apellidos}
            </div>

            <div id="m_body">

            </div>
        </div>
    </body>
</html>
