<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="js/parainfo/form.css" rel="stylesheet" type="text/css"/>
        <link href="js/parainfo/a.css" rel="stylesheet" type="text/css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="css/cabecera.css" rel="stylesheet" type="text/css"/>

        <script src="jq/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="js/parainfo/form.js" type="text/javascript"></script>
        <script src="js/tipoEventos.js" type="text/javascript"></script>
        <script src="js/cabecera.js" type="text/javascript"></script>

    </head>
    <body>
        <%@include file="WEB-INF/jspf/cabecera.jspf" %>
        <form action="TipoEventos" method="post" class="parainfo" style="margin: auto;display: table">
            <input type="hidden" name="accion" value="UPD"/>
            <input type="hidden" name="idtipoevento" value="${tipoEventos.idtipoevento}"/>

            <fieldset>
                <legend>Nuevo Tipo de Evento</legend>

                <table>
                    <tr>
                        <td align="right">Tipo de Evento</td>
                        <td>
                            <input type="text" name="tipoevento" value="${tipoEventos.tipoevento}" style="width: 300px"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Descripción del Tipo de Evento</td>
                        <td>
                            <input type="text" name="descripciontipo" value="${tipoEventos.descripciontipo}" style="width: 300px"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button type="submit"><span class="ui-icon ui-icon-disk"></span> Enviar Datos</button>
                            <button type="button" onclick="tipoEventosQry();"><span class="ui-icon ui-icon-home"></span> Cancelar</button>
                        </th>
                    </tr>
                </table>
            </fieldset>
        </form>

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
    </body>
</html>
