<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="js/parainfo/table.css" rel="stylesheet" type="text/css"/>
        <link href="js/parainfo/message.css" rel="stylesheet" type="text/css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="css/cabecera.css" rel="stylesheet" type="text/css"/>

        <script src="jq/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="js/parainfo/table.js" type="text/javascript"></script>
        <script src="js/parainfo/message.js" type="text/javascript"></script>
        <script src="js/tipoEventos.js" type="text/javascript"></script>
        <script src="js/cabecera.js" type="text/javascript"></script>

    </head>
    <body>
        <%@include file="WEB-INF/jspf/cabecera.jspf" %>
        <%-- tipoEventosQry --%>
        <table class="parainfo" style="width: 550px;margin: auto">
            <thead>
                <tr align="center">
                   
                    <td>Tipo de Evento</td>
                    <td>Descripción de Evento</td>
                    <th class="crud">
                        <a class="ins" href="#" onclick="tipoEventosIns();"><span></span></a>
                    </th>
                    <th class="crud">
                        <a class="del" href="#" onclick="tipoEventosDel();"><span></span></a>
                    </th>
                    <th class="crud">
                        <a class="upd" href="#" onclick="tipoEventosUpd();"><span></span></a>
                    </th>
                </tr>
            </thead>

            <tbody>
                <c:if test="${list != null}">
                    <c:forEach var="a" items="${list}">
                        <tr >
                            <td align="center">${a.tipoevento}</td>
                            <td colspan="2" align="justify">${a.descripciontipo}</td>
                            <th>
                                <input type="checkbox" name="idtipoEvento_del" value="${a.idtipoevento}"/>
                            </th>
                            <th>
                                <input type="radio" name="idtipoEvento_upd" value="${a.idtipoevento}"/>
                            </th>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>

        <%-- mensajes del servidor --%>
        <c:if test="${message != null}">
            <div style="margin: auto;display: table;margin-top: 30px;padding-right: 20px"
                 class="ui-state-error ui-corner-all">
                <ul>
                    <c:forEach var="m" items="${message}">
                        <li>
                            ${m}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <%-- mensajes del cliente --%>
        <div style="display: none">
            <div id="dlg_message">
                <p id="message"></p>
            </div>
        </div>
    </body>
</html>
