<%-- 
    Document   : cuantRiesgoQry
    Created on : 01/03/2018, 10:24:59 AM
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
        <script src="../../js/cuantRiesgo.js" type="text/javascript"></script>
        <script src="../../js/mensajes.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="m_main">
            <div id="m_menu">
                <%@include file="../../WEB-INF/jspf/menuadmin.jspf" %>
            </div>

            <form class="parainfo" style="float: left">
                <button type="button" onclick="cuantRiesgoIns();"><span class="ui-icon ui-icon-circle-plus"></span> Agregar</button>
            </form>

            <form class="parainfo" style="float: left">
                <span class="ui-icon ui-icon-search"></span>
                <input type="text" id=""
                       maxlength="200" style="width: 250px" 
                       placeholder="Buscar" 
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
                            <td>ACTIVO ESPEC.</td>
                            <th class="crud">
                                <a class="qry" href="../amenaza/amenazaQry.jsp" title="Ver Amenazas"><span></span></a>
                            </th>
                            <td>AMENAZA</td>
                            <td>VALOR AMENAZA</td>
                            <td>VALOR VULNERABILIDAD</td>
                            <td>VALOR IMPACTO</td>
                            <td>CUANTIFICADOR RIESGO</td>
                            <td>CUALIFICADOR RIESGO</td>
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
                    <tbody id="cuantRiesgoQry"></tbody>
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
                    <div id="dins" title="Nuevo registro" style="width: 1200px; height: 1200px">
                        <form class="parainfo">
                            <table>
                                <tr>
                                    <td>Activo</td>
                                    <td><select id="actEspecAmenCbo" style="width: 350px"></select></td>
                                </tr>
                                <tr>
                                    <td>Amenaza</td>
                                    <td>
                                        <select id="amenaza_ins" style="width: 350px">
                                            <option value="MB">MUY BAJO</option>
                                            <option value="B">BAJO</option>
                                            <option value="M">MEDIO</option>
                                            <option value="A">ALTO</option>
                                            <option value="MA">MUY ALTO</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Vulnerabilidad</td>
                                    <td>
                                        <select id="vulnerabilidad_ins" style="width: 350px">
                                            <option value="MB">MUY BAJO</option>
                                            <option value="B">BAJO</option>
                                            <option value="M">MEDIO</option>
                                            <option value="A">ALTO</option>
                                            <option value="MA">MUY ALTO</option>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td>Impacto</td>
                                    <td>
                                        <select id="impacto_ins" style="width: 350px">
                                            <option value="MB">MUY BAJO</option>
                                            <option value="B">BAJO</option>
                                            <option value="M">MEDIO</option>
                                            <option value="A">ALTO</option>
                                            <option value="MA">MUY ALTO</option>
                                        </select></td>
                                </tr>
                            </table>
                        </form>
                        <div id="ins_error" class="msg_error ui-state-error ui-corner-all"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
