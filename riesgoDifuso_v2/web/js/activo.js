$(function () {
    activoQry();
    $("#msg_ok").css("visibility", "hidden");
});

// Función para listar los activos
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
                    var nombactivo = data[i].nombactivo;
                    var tipo = data[i].tipo;
                    //var prueba = data[i].prueba;

                    body += "<tr>"
                            + "<td>" + nombactivo + "</td>"
                            + "<td>" + tipo + "</td>"
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

// Función para insertar activo
function activoIns() {
    $("#nombactivo_ins").val("");
    $("#tipo_ins").val(0);
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
                        nombactivo: $("#nombactivo_ins").val(),
                        tipo: $("#tipo_ins").val()
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
                            alertify.success("¡CORRECTO! El registro fue guardado");
                            activoQry();
                            $("#dins").dialog("close");

                        }
                    }
                });
            }
        }
    });
}

function activoDel() {
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
                                $("#nombact_find").val("");
                                alertify.error("¡ATENTO! Los registros fueron eliminados");
                                activoQry();
                            }

                            $("#dlg_message").dialog("close");
                        }
                    });
                }
            }
        });
    }
}

// Función para actualizar
function activoUpd() {
    var id = $("input[name='idactivo_upd']:checked").val();

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
                    var nombactivo = data[1].nombactivo;
                    var tipo = data[1].tipo;

                    $("#idactivo_upd").val(idactivo);
                    $("#nombactivo_upd").val(nombactivo);
                    $("#tipo_upd").val(tipo);
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
                                        nombactivo: $("#nombactivo_upd").val(),
                                        tipo: $("#tipo_upd").val()
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
                                            $("#nombact_find").val("");
                                            alertify.log("¡CUIDADO! El registro cuyo ID es " + idactivo + " fue actualizado");
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
            nombactivo: $("#nombact_find").val()
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
                        var nombactivo = data[i].nombactivo;
                        var tipo = data[i].tipo;

                        body += "<tr>"
                                + "<td>" + nombactivo + "</td>"
                                + "<td>" + tipo + "</td>"
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
