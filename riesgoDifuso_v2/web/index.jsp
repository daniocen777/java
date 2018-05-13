<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="WEB-INF/jspf/browser.jspf" %>
<%    session.invalidate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>parainformaticos.com</title>
        <link href="jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="parainfo/message.css" rel="stylesheet" type="text/css"/>
        <link href="parainfo/table.css" rel="stylesheet" type="text/css"/>
        <link href="parainfo/form.css" type="text/css" rel="stylesheet"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>

        <script src="jq/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="parainfo/message.js" type="text/javascript"></script>
        <script src="parainfo/table.js" type="text/javascript"></script>
        <script src="parainfo/form.js" type="text/javascript"></script>


    </head>
    <body>
        <form class="parainfo" action="Usuarios" method="post"
              style="margin: auto;display: table;margin-top: 50px">
            <input type="hidden" name="accion" value="LOGIN"/>

            <fieldset class="ui-corner-all">
                <legend>Formulario de Autenticación</legend>

                <table class="tabla">
                    <tr>
                        <th rowspan="4">
                            <img src="images/lock.png"/>
                        </th>
                        <td><label for="usuario">Usuario</label></td>
                        <td>
                            <input type="text" name="usuario" maxlength="50"
                                   value="${usuarios.usuario}" id="usuario"
                                   style="width: 200px"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="password">Password</label></td>
                        <td>
                            <input type="password" name="password" 
                                   maxlength="50" id="password"
                                   value="${usuarios.password}" 
                                   style="width: 200px"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right">
                            <button type="submit" 
                                    class="submit">Autenticarse</button>
                            <br/>
                            <br/>
                            <span style="color: #777">
                                <img src="images/i-parainformaticos.gif" 
                                     style="vertical-align: middle"/>
                                danicode.com<br/>
                            </span>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>

        <%-- para errores --%>
        <c:if test="${msg.size() > 0}">
            <ul class="msg_error ui-state-error ui-corner-all">
                <c:forEach var="m" items="${msg}"><li>${m}</li>
                    </c:forEach>
            </ul>
        </c:if>
    </body>
</html>

