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
        <script src="js/eventos.js" type="text/javascript"></script>
        <script src="js/cabecera.js" type="text/javascript"></script>

    </head>
    <body>
        <%@include file="WEB-INF/jspf/cabecera.jspf" %>
        <form action="Eventos" method="post" class="parainfo" style="margin: auto;display: table">
            <input type="hidden" name="accion" value="INS"/>

            <fieldset>
                <legend>Nuevo Evento</legend>

                <table>
                    <tr>
                        <td>Tipo de Evento</td>
                        <td>
                            <select name="idtipoEvento" style="width: 318px">
                                <c:forEach var="a" items="${list}">
                                    <c:choose>
                                        <c:when test="${a[0] == eventos.idtipoevento}">
                                            <option value="${a[0]}" selected="selected">${a[1]}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${a[0]}">${a[1]}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Evento</td>
                        <td>
                            <input type="text" name="evento" value="${eventos.evento}" style="width: 300px"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Descripción de Evento</td>
                        <td>
                            <textarea style="width: 300px;height: 50px"
                                      name="descripcionevento">${eventos.descripcionevento}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Comienzo</td>
                        <td>
                            <input type="datetime-local" name="comienzo" value="${eventos.comienzo}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Fin</td>
                        <td>
                            <input type="datetime-local" name="fin" value="${eventos.fin}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Detalle</td>
                        <td>
                            <input type="text" name="detalle" value="${eventos.detalle}" style="width: 300px"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <button type="submit"><span class="ui-icon ui-icon-disk"></span> Enviar Datos</button>
                            <button type="button" onclick="eventosQry();"><span class="ui-icon ui-icon-home"></span> Cancelar</button>
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
