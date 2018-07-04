$(function () {
    actEspecQry();
});

// Función para el QRY
function actEspecQry() {
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
                    var nombactivo = data[i].nombactivo;
                    var nombactespec = data[i].nombactespec;

                    body += "<tr>"
                            + "<td colspan='2'>" + nombactivo + "</td>"
                            + "<td>" + nombactespec + "</td>"
                            + "<th><input type='radio' name='idactespec_upd' value='" + idactespec + "'/></th>"
                            + "<th><input type='checkbox' name='idactespec_del' value='" + idactespec + "'/></th>"
                            + "</tr>";
                }

                $("#activoespecQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Función para llenar el combo de activos
function activoCbo() {
    $.ajax({
        url: "../../ActEspec",
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
                $("#activoEspecQry").html("");

            } else {
                var option = "";
                option += "<option value='0' selected='selected'>[..Seleccione..]</option>";

                for (var i = 1; i < data.length; ++i) {
                    var id = data[i].id;
                    var opt = data[i].opt;
                    option += "<option value=\"" + id + "\">" + opt + "</option>";
                }
                $("#activoCbo_ins").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Función para insertar 
function actEspecIns() {
    $("#nombactespec_ins").val("");
    $("#ins_error").html("");
    $("#ins_error").css("visibility", "hidden");
    activoCbo();

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
                        idactivo: $("#activoCbo_ins").val(),
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
                            alertify.success("¡CONFIRMADO! El registro fue guardado");
                            actEspecQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}

// Función para eliminar
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
                "Sí": function () {
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
                                $("#nombactespec_find").val("");
                                alertify.error("¡ATENTO! Los registros fueron eliminados");
                                actEspecQry();
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

