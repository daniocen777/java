$(function () {
    actEspecAmenQry();
    activosCbo();
    amenazaCbo();
});

function actEspecAmenQry() {
    $.ajax({
        url: "../../ActEspecAmen",
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
                $("#activoEspecAmenQry").html("");

            } else {
                var body = "";

                for (var i = 1; i < data.length; ++i) {
                    var idactespecamen = data[i].idactespecamen;
                    var nombact = data[i].nombact;
                    var nombactespec = data[i].nombactespec;
                    var nombamen = data[i].nombamen;

                    body += "<tr>"
                            + "<td colspan='2'>" + nombact + "</td>"
                            + "<td colspan='2'>" + nombactespec + "</td>"
                            + "<td colspan='2'>" + nombamen + "</td>"
                            + "<th><input type='radio' name='idactespecamen_upd' value='" + idactespecamen + "'/></th>"
                            + "<th><input type='checkbox' name='idactespecamen_del' value='" + idactespecamen + "'/></th>"
                            + "</tr>";
                }

                $("#activoEspecAmenQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Llenado del combo de amenazas
function amenazaCbo() {
    $.ajax({
        url: "../../ActEspecAmen",
        dataType: "json",
        type: "post",
        data: {
            accion: "AMENCBO"
        },
        success: function (data) {
            var msg = $(data).find('msg').text();

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");

            } else {
                var option = "";

                for (var i = 1; i < data.length; ++i) {
                    var id = data[i].id;
                    var opt = data[i].opt;

                    option += "<option value=\"" + id + "\">" + opt + "</option>";
                }
                $("#amenazaCbo").html(option);
                //$("#actEspecCbo_upd").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Llenado del combo de activos
function activosCbo() {
    $.ajax({
        url: "../../ActEspecAmen",
        dataType: "json",
        type: "post",
        data: {
            accion: "ACTCBO"
        },
        success: function (data) {
            var msg = $(data).find('msg').text();

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");

            } else {
                var option = "";

                for (var i = 1; i < data.length; ++i) {
                    var id = data[i].id;
                    var opt = data[i].opt;

                    option += "<option value=\"" + id + "\">" + opt + "</option>";
                }
                $("#activosCbo").html(option);
                //$("#actEspecCbo_upd").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

function probando() {
    var activo = parseInt($("#activosCbo").val());
    var option = "";
    var cpu = "CPU";
    var moni = "MONITOR";
    var otro = "OTROS";
    if (activo === 37) {
        option += "<option value=\"" + cpu + "\">" + cpu + "</option>";
        option += "<option value=\"" + moni + "\">" + moni + "</option>";
    } else {
        option += "<option value=\"" + otro + "\">" + otro + "</option>";
    }
    $("#activosEspecCbo").html(option);

}

function activEspecCbo() {
    $.ajax({
        url: "../../ActEspecAmen",
        dataType: "json",
        type: "post",
        data: {
            accion: "ACTESPECCBO"
        },
        success: function (data) {
            var msg = $(data).find('msg').text();

            if ($.trim(msg).length !== 0) {
                $("#msg_error").text(msg);
                $("#msg_error").css("visibility", "visible");

            } else {
                var option = "";
                var activo = parseInt($("#activosCbo").val());

                for (var i = 1; i < data.length; ++i) {
                    var idactivo = data[i].idactivo;
                    var idactespec = data[i].idactespec;
                    var nombactespec = data[i].nombactespec;

                    if (idactivo === activo) {
                        option += "<option value=\"" + idactespec + "\">" + nombactespec + "</option>";
                    }


                }
                $("#activosEspecCbo").html(option);
                //$("#actEspecCbo_upd").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Insertar
function actEspecAmenIns() {
    $("#activosCbo").val("");
    $("#activosEspecCbo").val("");
    $("#amenazaCbo").val("");
    $("#ins_error").html("");
    $("#ins_error").css("visibility", "hidden");

    $("#dins").dialog({
        modal: true,
        width: 460,
        buttons: {
            "Cancelar": function () {
                $(this).dialog("close");
            },
            "Enviar Datos": function () {
                $.ajax({
                    url: "../../ActEspecAmen",
                    type: "post",
                    dataType: "json",
                    data: {
                        accion: "INS",
                        idactespec: $("#activosEspecCbo").val(),
                        idamenaza: $("#amenazaCbo").val()
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
                            var ok = "<p>" + "Se agregó el registro correctamente" + "</p>";
                            $("#msg_ok").html(ok);
                            $("#msg_ok").css("visibility", "visible");
                            actEspecAmenQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}

// Eliminar
function actEspecAmenDel() {
    var ids = [];

    $("input[name='idactespecamen_del']:checked").each(function () {
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
                        url: "../../ActEspecAmen",
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
                                var war = "<p>" + "Los registros fueron eliminados" + "</p>";
                                $("#msg_war").html(war);
                                $("#msg_war").css("visibility", "visible");
                                actEspecAmenQry();
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