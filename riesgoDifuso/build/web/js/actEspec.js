$(function () {
    activoEspecQry();
    actEspecCbo();
});

function activoEspecQry() {
    $.ajax({
        url: "../../ActEspec",
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
                $("#activoEspecQry").html("");

            } else {
                var body = "";

                for (var i = 1; i < data.length; ++i) {
                    var idactespec = data[i].idactespec;
                    var nombact = data[i].nombact;
                    var nombactespec = data[i].nombactespec;

                    body += "<tr>"
                            + "<td colspan='2'>" + nombact + "</td>"
                            + "<td>" + nombactespec + "</td>"
                            + "<th><input type='radio' name='idactespec_upd' value='" + idactespec + "'/></th>"
                            + "<th><input type='checkbox' name='idactespec_del' value='" + idactespec + "'/></th>"
                            + "</tr>";
                }

                $("#activoEspecQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Poblar el combo
function actEspecCbo() {
    $.ajax({
        url: "../../ActEspec",
        dataType: "json",
        type: "post",
        data: {
            accion: "CBO"
        },
        success: function (data) {
            var msg = $(data).find('msg').text();

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");
                $("#activoEspecQry").html("");

            } else {
                var option = "";
//                $(data).find('op').each(function () {
//                    var text = $(this).text();
//                    var value = $(this).attr('id');
//
//                    option += "<option value=\"" + value + "\">" + text + "</option>";
//                });
                for (var i = 1; i < data.length; ++i) {
                    var id = data[i].id;
                    var opt = data[i].opt;

                    option += "<option value=\"" + id + "\">" + opt + "</option>";
                }
                $("#actEspecCbo").html(option);
                $("#actEspecCbo_upd").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Insertar 
function actEspecIns() {
    $("#actEspecCbo").val("");
    $("#nombactespec_ins").val("");
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
                    url: "../../ActEspec",
                    type: "post",
                    dataType: "json",
                    data: {
                        accion: "INS",
                        idactivo: $("#actEspecCbo").val(),
                        nombactespec: $("#nombactespec_ins").val()
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
                            var ok = "<p>" + "Se agregó nuevo activo específico" + "</p>";
                            $("#msg_ok").html(ok);
                            $("#msg_ok").css("visibility", "visible");
                            activoEspecQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}

// Eliminar activo específico
function actEspecDel() {
    var ids = [];

    $("input[name='idactespec_del']:checked").each(function () {
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
                        url: "../../ActEspec",
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
                                var war = "<p>" + "Activo(s) Específico(s) eliminada(s)" + "</p>";
                                $("#msg_war").html(war);
                                $("#msg_war").css("visibility", "visible");
                                activoEspecQry();
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
function actEspecUpd() {
    var id = $("input[name='idactespec_upd']:checked").val();

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");
    } else {
        $.ajax({
            url: "../../ActEspec",
            dataType: "json",
            type: "post",
            data: {
                accion: "GET",
                idactespec: id
            },
            success: function (data) {
                var msg = data[0].msg;

                if ($.trim(msg).length !== 0) {
                    $("#msg_error").text(msg);
                    $("#msg_error").css("visibility", "visible");

                } else {
                    var idactespec = data[1].idactespec;
                    var nombact = data[1].nombact;
                    var nombactespec = data[1].nombactespec;

                    $("#idactespec_upd").val(idactespec);
                    $("#actEspecCbo_upd").val(nombact);
                    $("#nombactespec_upd").val(nombactespec);
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
                                    url: "../../ActEspec",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        accion: "UPD",
                                        idactespec: $("#idactespec_upd").val(),
                                        idactivo: $("#actEspecCbo_upd").val(),
                                        nombactespec: $("#nombactespec_upd").val()
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
                                            var alert = "<p>" + "Activo Específico actualizado" + "</p>";
                                            $("#msg_alert").html(alert);
                                            $("#msg_alert").css("visibility", "visible");
                                            activoEspecQry();
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

// Encontrar activo específico por nombre
function actEspecPorNombreQry() {
    $.ajax({
        url: "../../ActEspec",
        dataType: "json",
        type: "post",
        data: {
            accion: "FIND",
            nombactespec: $("#nombactespec_find").val()
        },
        success: function (data) {
            var msg = data[0].msg;

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");
                $("#activoEspecQry").html("");

            } else {
                if ($("#nombactespec_find").val().length !== 0) {
                    var body = "";

                    for (var i = 1; i < data.length; ++i) {
                        var idactespec = data[i].idactespec;
                        var nombact = data[i].nombact;
                        var nombactespec = data[i].nombactespec;

                        body += "<tr>"
                                + "<td colspan='2'>" + nombact + "</td>"
                                + "<td>" + nombactespec + "</td>"
                                + "<th><input type='radio' name='idactespec_upd' value='" + idactespec + "'/></th>"
                                + "<th><input type='checkbox' name='idactespec_del' value='" + idactespec + "'/></th>"
                                + "</tr>";
                    }

                    $("#activoEspecQry").html(body);
                    $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
                } else {
                    activoEspecQry();
                }

            }
        }
    });
}