$(function () {
    //activos();
    activoQry();
    //activosPags();
});

function activoQry() {
    $.ajax({
        url: "../../Activo",
        dataType: "json",
        type: "post",
        data: {
            accion: "QRY"
        },
        success: function (data) {
            var msg = data[0].msg;

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");
                $("#activoQry").html("");

            } else {
                var body = "";

                for (var i = 1; i < data.length; ++i) {
                    var idactivo = data[i].idactivo;
                    var nombact = data[i].nombact;
                    var tipoact = data[i].tipoact;
                    //var prueba = data[i].prueba;

                    body += "<tr>"
                            + "<td>" + nombact + "</td>"
                            + "<td>" + tipoact + "</td>"
                            //+ "<td style='color: #B94A48; font-weight: bold;' >" + prueba + "</td>"
                            + "<th><input type='radio' name='idactivo_upd' value='" + idactivo + "'/></th>"
                            + "<th><input type='checkbox' name='idactivo_del' value='" + idactivo + "'/></th>"
                            + "</tr>";
                }

                $("#activoQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Insertar
function activoIns() {
    $("#nombact_ins").val("");
    $("#tipoact_ins").val("[Seleccione]");
    $("#ins_error").html("");
    $("#ins_error").css("visibility", "hidden");

    $("#dins").dialog({
        modal: true,
        width: 420,
        buttons: {
            "Cancelar": function () {
                $(this).dialog("close");
            },
            "Enviar Datos": function () {
                $.ajax({
                    url: "../../Activo",
                    type: "post",
                    dataType: "json",
                    data: {
                        accion: "INS",
                        nombact: $("#nombact_ins").val(),
                        tipoact: $("#tipoact_ins").val()
                    },
                    success: function (error) {
                        var msg = error[0].msg;

                        if ($.trim(msg).length !== 0 && msg !== 'ok') {
                            var ctos = error.length;

                            var msg = "<ul>";
                            for (var i = 0; i < ctos; ++i) {
                                msg += "<li>" + error[i].msg + "</li>";
                            }
                            msg += "</ul>";

                            $("#ins_error").html(msg);
                            $("#ins_error").css("visibility", "visible");

                        } else {
                            $("#msg_war").html("");
                            $("#msg_war").css("visibility", "hidden");
                            $("#msg_alert").html("");
                            $("#msg_alert").css("visibility", "hidden");
                            var ok = "<p>" + "Se agregó nuevo activo" + "</p>";
                            $("#msg_ok").html(ok);
                            $("#msg_ok").css("visibility", "visible");

                            activoQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}

// Eliminar activo
function activossDel() {
    var ids = [];

    $("input[name='idactivo_del']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        message("Advertencia", "Seleccione fila(s) a Retirar");
    } else {
        $("#message").html("¿Retirar registro(s)?");
        $("#dlg_message").dialog({
            modal: true,
            width: 440,
            buttons: {
                "No": function () {
                    $(this).dialog("close");
                },
                "Si": function () {
                    $(this).dialog("close");

                    $.ajax({
                        url: "../../Activo",
                        type: "post",
                        dataType: "json",
                        data: {
                            accion: "DEL",
                            ids: ids.toString()
                        },
                        success: function (error) {
                            var msg = error[0].msg;

                            if ($.trim(msg).length !== 0 && msg !== 'ok') {
                                $("#msg_error").text(msg);
                                $("#msg_error").css("visibility", "visible");

                            } else {
                                $("#msg_ok").html("");
                                $("#msg_ok").css("visibility", "hidden");
                                $("#msg_alert").html("");
                                $("#msg_alert").css("visibility", "hidden");
                                var war = "<p>" + "Activo(s) eliminado(s)" + "</p>";
                                $("#msg_war").html(war);
                                $("#msg_war").css("visibility", "visible");
                                activoQry();
                                $("#msg_error").css("visibility", "hidden");
                            }

                            $("#dlg_message").dialog("close");
                        }
                    });
                }
            }
        });
    }
}

// ACTUALIZAR
function activoUpd() {
    var id = $("input[name='idactivo_upd']:checked").val();
    //alert(id);

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");
    } else {
        $.ajax({
            url: "../../Activo",
            dataType: "json",
            type: "post",
            data: {
                accion: "GET",
                idactivo: id
            },
            success: function (data) {
                var msg = data[0].msg;

                if ($.trim(msg).length !== 0) {
                    $("#msg_error").text(msg);
                    $("#msg_error").css("visibility", "visible");

                } else {
                    var idactivo = data[1].idactivo;
                    var nombact = data[1].nombact;
                    var tipoact = data[1].tipoact;

                    $("#idactivo_upd").val(idactivo);
                    $("#nombact_upd").val(nombact);
                    $("#tipoact_upd").val(tipoact);
                    $("#upd_error").html("");
                    $("#upd_error").css("visibility", "hidden");

                    $("#dupd").dialog({
                        modal: true,
                        width: 440,
                        buttons: {
                            "Cancelar": function () {
                                $(this).dialog("close");
                            },
                            "Enviar Datos": function () {
                                $.ajax({
                                    url: "../../Activo",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        accion: "UPD",
                                        idactivo: $("#idactivo_upd").val(),
                                        nombact: $("#nombact_upd").val(),
                                        tipoact: $("#tipoact_upd").val()
                                    },
                                    success: function (error) {
                                        var msg = error[0].msg;

                                        if ($.trim(msg).length !== 0 && msg !== 'ok') {
                                            var ctos = error.length;

                                            var msg = "<ul>";
                                            for (var i = 0; i < ctos; ++i) {
                                                msg += "<li>" + error[i].msg + "</li>";
                                            }
                                            msg += "</ul>";

                                            $("#upd_error").html(msg);
                                            $("#upd_error").css("visibility", "visible");

                                        } else {
                                            $("#msg_ok").html("");
                                            $("#msg_ok").css("visibility", "hidden");
                                            $("#msg_war").html("");
                                            $("#msg_war").css("visibility", "hidden");
                                            var alert = "<p>" + "Activo actualizado" + "</p>";
                                            $("#msg_alert").html(alert);
                                            $("#msg_alert").css("visibility", "visible");
                                            activoQry();
                                            $("#dupd").dialog("close");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}

// Encontrar activo por nombre
function activoPorNombreQry() {
    $.ajax({
        url: "../../Activo",
        dataType: "json",
        type: "post",
        data: {
            accion: "FIND",
            nombact: $("#nombact_find").val()
        },
        success: function (data) {
            var msg = data[0].msg;

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");
                $("#activoQry").html("");

            } else {
                if ($("#nombact_find").val().length !== 0) {
                    var body = "";

                    for (var i = 1; i < data.length; ++i) {
                        var idactivo = data[i].idactivo;
                        var nombact = data[i].nombact;
                        var tipoact = data[i].tipoact;

                        body += "<tr>"
                                + "<td>" + nombact + "</td>"
                                + "<td>" + tipoact + "</td>"
                                + "<th><input type='radio' name='idactivo_upd' value='" + idactivo + "'/></th>"
                                + "<th><input type='checkbox' name='idactivo_del' value='" + idactivo + "'/></th>"
                                + "</tr>";
                    }

                    $("#activoQry").html(body);
                    $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
                } else {
                    activoQry();
                }

            }
        }
    });
}

//function activosPags() {
//    $.ajax({
//        url: "../../Activo",
//        dataType: "json",
//        type: "post",
//        data: {
//            accion: "CBO"
//        },
//        success: function (data) {
//            var msg = data[0].msg;
//            var numero = parseInt(msg);
//            var html = "";
//
//            if ($.trim(numero).length === 0) {
//                alert("No hay nada");
//
//            } else {
//                for (var i = 0; i < numero; ++i) {
//                    html += "<option value=\"" + i + "\">" + (i + 1) + "</option>";
//                }
//            }
//            $("#cbo").html(html);
//        }
//    });
//}
//
//// Nuevo listado con paginadores 
//function activos() {
//    var num = parseInt($("#cbo").val());
//    //alert(num);
//    $.ajax({
//        url: "../../Activo",
//        dataType: "json",
//        type: "post",
//        data: {
//            accion: "QRY",
//            numPag: num
//        },
//        success: function (data) {
//            var msg = data[0].msg;
//
//            if ($.trim(msg).length !== 0) {
//                $("#msg_error").text(msg);
//                $("#msg_error").css("visibility", "visible");
//                $("#activoQry").html("");
//
//            } else {
//                var body = "";
//
//                for (var i = 1; i < data.length; ++i) {
//                    var idactivo = data[i].idactivo;
//                    var nombact = data[i].nombact;
//                    var tipoact = data[i].tipoact;
//
//                    body += "<tr>"
//                            + "<td>" + nombact + "</td>"
//                            + "<td>" + tipoact + "</td>"
//                            + "<th><input type='radio' name='idactivo_upd' value='" + idactivo + "'/></th>"
//                            + "<th><input type='checkbox' name='idactivo_del' value='" + idactivo + "'/></th>"
//                            + "</tr>";
//                }
//
//                $("#activoQry").html(body);
//                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
//            }
//        }
//    });
//}



