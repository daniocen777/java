<%-- 
    Document   : amenazaQry
    Created on : 22/01/2018, 09:57:22 AM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <script src="../../js/amenaza.js" type="text/javascript"></script>
        <script src="../../js/mensajes.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="m_main">
            <div id="m_menu">
                <%@include file="../../WEB-INF/jspf/menuadmin.jspf" %>
            </div>


            <form class="parainfo">
                <button type="button" onclick="amenazaIns();"><span class="ui-icon ui-icon-circle-plus"></span> Agregar</button>
            </form>

            <!-- Buscar por nombre de amenaza -->
            <form class="parainfo">
                <span class="ui-icon ui-icon-search"></span>
                <input type="text" id="nombamen_find"
                       maxlength="200" style="width: 250px" 
                       placeholder="Buscar amenaza" 
                       onkeyup="amenazaPorNombreQry();"/>
            </form>

            <div id="m_body">
                <table class="parainfo" style="margin: auto;width: 860px">
                    <thead>
                        <tr>
                            <td>Amenaza</td>
                            <th class="crud">
                                <a class="upd" href="#" onclick="amenazaUpd();"
                                   title="Actualizar Registro"><span></span></a>
                            </th>
                            <th class="crud">
                                <a class="del" href="#" onclick="amenazaDel();"
                                   title="Retirar Registro"><span></span></a>
                            </th>

                        </tr>
                    </thead>
                    <tbody id="amenazaQry"></tbody>
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
                    <div id="dins" title="Nuevo registro">
                        <form class="parainfo">
                            <table>
                                <tr>
                                    <td>Amenaza</td>
                                    <td>
                                        <input type="text" id="nombamen_ins" 
                                               maxlength="200" style="width: 250px"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <div id="ins_error" 
                             class="msg_error ui-state-error ui-corner-all"></div>
                    </div>
                </div>
                <%-- FIN - Insertar  --%>

                <%-- INICIO - Actualizar  --%>
                <div style="display: none">
                    <div id="dupd" title="Actualizar registro">
                        <form class="parainfo">
                            <input type="hidden" id="idamenaza_upd"/>
                            <table>
                                <tr>
                                    <td>Amenaza</td>
                                    <td>
                                        <input type="text" id="nombamen_upd" 
                                               maxlength="200" style="width: 250px"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <div id="upd_error" class="msg_error ui-state-error ui-corner-all"></div>
                    </div>
                </div>
                <%-- FIN - Actualizar  --%>
            </div>
        </div>
    </body>
</html>
