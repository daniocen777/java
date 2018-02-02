$(function () {
    amenazaQry();
});

function amenazaQry() {
    $.ajax({
        url: "../../Amenaza",
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
                $("#amenazaQry").html("");

            } else {
                var body = "";

                for (var i = 1; i < data.length; ++i) {
                    var idamenaza = data[i].idamenaza;
                    var nombamen = data[i].nombamen;

                    body += "<tr>"
                            + "<td>" + nombamen + "</td>"
                            + "<th><input type='radio' name='idamenaza_upd' value='" + idamenaza + "'/></th>"
                            + "<th><input type='checkbox' name='idamenaza_del' value='" + idamenaza + "'/></th>"
                            + "</tr>";
                }

                $("#amenazaQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Insertar
function amenazaIns() {
    $("#nombamen_ins").val("");
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
                    url: "../../Amenaza",
                    type: "post",
                    dataType: "json",
                    data: {
                        accion: "INS",
                        nombamen: $("#nombamen_ins").val()
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
                            var ok = "<p>" + "Se agregó nueva amenaza" + "</p>";
                            $("#msg_ok").html(ok);
                            $("#msg_ok").css("visibility", "visible");
                            amenazaQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}

// Eliminar amenaza
function amenazaDel() {
    var ids = [];

    $("input[name='idamenaza_del']:checked").each(function () {
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
                        url: "../../Amenaza",
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
                                var war = "<p>" + "Amenaza(s) eliminada(s)" + "</p>";
                                $("#msg_war").html(war);
                                $("#msg_war").css("visibility", "visible");
                                amenazaQry();
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
function amenazaUpd() {
    var id = $("input[name='idamenaza_upd']:checked").val();

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");
    } else {
        $.ajax({
            url: "../../Amenaza",
            dataType: "json",
            type: "post",
            data: {
                accion: "GET",
                idamenaza: id
            },
            success: function (data) {
                var msg = data[0].msg;
                var ok = 'Actualizado correctamente';

                if ($.trim(msg).length !== 0) {
                    $("#msg_error").text(msg);
                    $("#msg_error").css("visibility", "visible");

                } else {
                    var idamenaza = data[1].idamenaza;
                    var nombamen = data[1].nombamen;

                    $("#idamenaza_upd").val(idamenaza);
                    $("#nombamen_upd").val(nombamen);
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
                                    url: "../../Amenaza",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        accion: "UPD",
                                        idamenaza: $("#idamenaza_upd").val(),
                                        nombamen: $("#nombamen_upd").val()
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
                                            var alert = "<p>" + "Amenaza actualizada" + "</p>";
                                            $("#msg_alert").html(alert);
                                            $("#msg_alert").css("visibility", "visible");
                                            amenazaQry();
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

// Encontrar amenaza por nombre
function amenazaPorNombreQry() {
    $.ajax({
        url: "../../Amenaza",
        dataType: "json",
        type: "post",
        data: {
            accion: "FIND",
            nombamen: $("#nombamen_find").val()
        },
        success: function (data) {
            var msg = data[0].msg;

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");
                $("#amenazaQry").html("");

            } else {
                if ($("#nombamen_find").val().length !== 0) {
                    var body = "";

                    for (var i = 1; i < data.length; ++i) {
                        var idamenaza = data[i].idamenaza;
                        var nombamen = data[i].nombamen;

                        body += "<tr>"
                                + "<td>" + nombamen + "</td>"
                                + "<th><input type='radio' name='idamenaza_upd' value='" + idamenaza + "'/></th>"
                                + "<th><input type='checkbox' name='idamenaza_del' value='" + idamenaza + "'/></th>"
                                + "</tr>";
                    }

                    $("#amenazaQry").html(body);
                    $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
                } else {
                    amenazaQry();
                }

            }
        }
    });
}