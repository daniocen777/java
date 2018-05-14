<%-- 
    Document   : activos
    Created on : 13/05/2018, 09:07:29 PM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title        
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
        <script src="../../js/activo.js" type="text/javascript"></script>

    </head>
    <body>
        <div id="m_main">
            <div id="m_menu">
                <%@include file="../../WEB-INF/jspf/menuadmin.jspf" %>
            </div>

            <form class="parainfo" style="float: left">
                <button type="button"  onclick="activoIns();"><span class="ui-icon ui-icon-circle-plus"></span> Agregar</button>
            </form>

            <form class="parainfo" style="float: left">
                <span class="ui-icon ui-icon-search"></span>
                <input type="text" id="nombact_find"
                       maxlength="200" style="width: 250px" 
                       placeholder="Buscar activo" 
                       onkeyup=""/>
            </form>

            <div id="m_body">
                <table class="parainfo" style="margin: auto;width: 860px">
                    <thead>
                        <tr>
                            <td style="text-align: center">ACTIVO</td>
                            <td style="text-align: center">TIPO DE ACTIVO</td>
                            <th class="crud">
                                <a class="upd" href="#" onclick=""
                                   title="Actualizar Registro"><span></span></a>
                            </th>
                            <th class="crud">
                                <a class="del" href="#" onclick=""
                                   title="Retirar Registro"><span></span></a>
                            </th>

                        </tr>
                    </thead>
                    <tbody id="activoQry"></tbody>
                </table>

                <%-- para mensajes en JSP --%>
                <div id="msg_error" onmouseover="cerrarmensajes();" class="msg_error ui-state-error ui-corner-all"></div>

                <%-- para mensajes en dialogo --%>
                <div style="display: none">
                    <div id="dlg_message"><p id="message"></p></div>
                </div> 



                <%-- INICIO - Insertar  --%>
                <div style="display: none">
                    <div id="dins" title="Nuevo registro">
                        <form class="parainfo">                            
                            <table>
                                <tr>
                                    <td>Activo</td>
                                    <td>
                                        <input type="text" id="nombactivo_ins" 
                                               maxlength="200" style="width: 250px"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tipo de Activo</td>
                                    <td>
                                        <select id="tipo_ins" style="width: 260px">
                                            <option value="0" selected>[...Seleccione...]</option>
                                            <option value="HARDWARE">HARDWARE</option>
                                            <option value="SOFTWARE">SOFTWARE</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </form>

                        <div id="ins_error" 
                             class="msg_error ui-state-error ui-corner-all"></div>

                    </div>
                </div>
                <%-- FIN - Insertar  --%>

            </div>
        </div>
    </body>
</html>
