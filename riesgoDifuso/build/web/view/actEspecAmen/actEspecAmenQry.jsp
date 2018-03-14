<%-- 
    Document   : actEspecAmenQry
    Created on : 14/02/2018, 08:01:00 PM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activos VS Amenazas</title>
        <link href="../../jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/form.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/table.css" rel="stylesheet" type="text/css"/>
        <link href="../../parainfo/message.css" rel="stylesheet" type="text/css"/>
        <link href="../../jq/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>

        <script src="../../jq/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="../../jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../../parainfo/form.js" type="text/javascript"></script>
        <script src="../../parainfo/table.js" type="text/javascript"></script>
        <script src="../../parainfo/message.js" type="text/javascript"></script>
        <script src="../../jq/menu/menu.js" type="text/javascript"></script>
        <script src="../../js/actEspecAmen.js" type="text/javascript"></script>
        <script src="../../js/mensajes.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="m_main">
            <div id="m_menu">
                <%@include file="../../WEB-INF/jspf/menuadmin.jspf" %>
            </div>

            <form class="parainfo" style="float: left">
                <button type="button" onclick="actEspecAmenIns();"><span class="ui-icon ui-icon-circle-plus"></span> Agregar</button>
            </form>

            <form class="parainfo" style="float: left">
                <span class="ui-icon ui-icon-search"></span>
                <input type="text" id="nombact_find"
                       maxlength="200" style="width: 250px" 
                       placeholder="Buscar activo específico" 
                       onkeyup=""/>
            </form>

            <div id="m_body">
                <table class="parainfo" style="margin: auto;width: 860px">
                    <thead>
                        <tr>
                            <th class="crud">
                                <a class="qry" href="../activos/activoQry.jsp" title="Ver Activos"><span></span></a>
                            </th>                            
                            <td>ACTIVO</td>
                            <th class="crud">
                                <a class="qry" href="../actEspec/actEspecQry.jsp" title="Ver Activos Espec."><span></span></a>
                            </th>
                            <td>ACTIVO ESPECÍFICO</td>
                            <th class="crud">
                                <a class="qry" href="../amenaza/amenazaQry.jsp" title="Ver Amenazas"><span></span></a>
                            </th>
                            <td>AMENAZA</td>
                            <th class="crud">
                                <a class="upd" href="#" onclick=""
                                   title="Actualizar Registro"><span></span></a>
                            </th>
                            <th class="crud">
                                <a class="del" href="#" onclick="actEspecAmenDel();"
                                   title="Retirar Registro"><span></span></a>
                            </th>

                        </tr>
                    </thead>
                    <tbody id="activoEspecAmenQry"></tbody>
                </table>

                <%-- para mensajes en JSP --%>
                <div id="msg_error" 
                     class="msg_error ui-state-error ui-corner-all"></div>

                <%-- para mensajes en dialogo --%>
                <div style="display: none">
                    <div id="dlg_message"><p id="message"></p></div>
                </div>

                <div id="msg_ok" onmouseover="cerrarmensajes();" class="msg_ok ui-state-checked ui-corner-all"></div>
                <div id="msg_war" onmouseover="cerrarmensajes();" class="msg_war ui-state-highlight ui-corner-all"></div>
                <div id="msg_alert" onmouseover="cerrarmensajes();" class="msg_alert ui-state-default ui-corner-all"></div>

                <%-- INICIO - Insertar  --%>
                <div style="display: none">
                    <div id="dins" title="Nuevo registro" style="width: 900px; height: 900px">
                        <form class="parainfo">
                            <table>
                                <tr>
                                    <td>Activo</td>
                                    <td><select id="activosCbo" onchange="activEspecCbo();" style="width: 350px"></select></td>
                                </tr>
                                <tr>
                                    <td>Activo Espec.</td>
                                    <td><select id="activosEspecCbo" style="width: 350px"></select></td>
                                </tr>
                                <tr>
                                    <td>Amenaza</td>
                                    <td><select id="amenazaCbo" style="width: 350px"></select></td>
                                </tr>
                            </table>
                        </form>
                        <div id="ins_error" class="msg_error ui-state-error ui-corner-all"></div>
                    </div>
                </div>
                <%-- FIN - Insertar  --%>

            </div>
        </div>
    </body>
</html>
