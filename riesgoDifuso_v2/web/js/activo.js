$(function () {
    activoQry();
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
                            var ok = "<div class='alert alert-success alert-dismissable'>";
                            ok += "El registro se agregó correctamente";
                            ok += "</div>";
                            $("#ins_msg").html("");
                            $("#msg_ok").html(ok);
                            activoQry();
                            $("#dins").dialog("close");

                        }
                    }
                });
            }
        }
    });
}
