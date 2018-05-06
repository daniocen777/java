$(function () {
    cuantRiesgoQry();
    cR_actEspecCbo();
});

function cuantRiesgoQry() {
    $.ajax({
        url: "../../CuantRiesgo",
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
                $("#cuantRiesgoQry").html("");

            } else {
                var body = "";

                for (var i = 1; i < data.length; ++i) {
                    var idcuantriesgo = data[i].idcuantriesgo;
                    var nombact = data[i].nombact;
                    var nombactespec = data[i].nombactespec;
                    var nombamen = data[i].nombamen;
                    var amenaza = data[i].amenaza;
                    if (amenaza === "MB")
                        amenaza = "MUY BAJO";
                    if (amenaza === "B")
                        amenaza = "BAJO";
                    if (amenaza === "M")
                        amenaza = "MEDIO";
                    if (amenaza === "A")
                        amenaza = "ALTO";
                    if (amenaza === "MA")
                        amenaza = "MUY ALTO";
                    var vulnerabilidad = data[i].vulnerabilidad;
                    if (vulnerabilidad === "MB")
                        vulnerabilidad = "MUY BAJO";
                    if (vulnerabilidad === "B")
                        vulnerabilidad = "BAJO";
                    if (vulnerabilidad === "M")
                        vulnerabilidad = "MEDIO";
                    if (vulnerabilidad === "A")
                        vulnerabilidad = "ALTO";
                    if (vulnerabilidad === "MA")
                        vulnerabilidad = "MUY ALTO";
                    var impacto = data[i].impacto;
                    if (impacto === "MB")
                        impacto = "MUY BAJO";
                    if (impacto === "B")
                        impacto = "BAJO";
                    if (impacto === "M")
                        impacto = "MEDIO";
                    if (impacto === "A")
                        impacto = "ALTO";
                    if (impacto === "MA")
                        impacto = "MUY ALTO";
                    var defu = data[i].defu;
                    var riesgo = data[i].riesgo;

                    body += "<tr>"
                            + "<td colspan='2'>" + nombact + "</td>"
                            + "<td colspan='2'>" + nombactespec + "</td>"
                            + "<td colspan='2'>" + nombamen + "</td>"
                            + "<td>" + amenaza + "</td>"
                            + "<td>" + vulnerabilidad + "</td>"
                            + "<td>" + impacto + "</td>"
                            + "<td>" + defu + "</td>"
                            + "<td>" + riesgo + "</td>"
                            + "<th><input type='radio' name='idcuantriesgo_upd' value='" + idcuantriesgo + "'/></th>"
                            + "<th><input type='checkbox' name='idcuantriesgo_del' value='" + idcuantriesgo + "'/></th>"
                            + "</tr>";
                }

                $("#cuantRiesgoQry").html(body);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Método para llenar el combo
function cR_actEspecCbo() {
    $.ajax({
        url: "../../CuantRiesgo",
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

            } else {
                var option = "";

                for (var i = 1; i < data.length; ++i) {
                    var idactespecamen = data[i].idactespecamen;
                    var nombact = data[i].nombact;
                    var nombactespec = data[i].nombactespec;
                    var nombamen = data[i].nombamen;

                    option += "<option value=\"" + idactespecamen + "\">" +
                            nombact + " - " +
                            nombactespec + " - " +
                            nombamen +
                            "</option>";
                }
                $("#actEspecAmenCbo").html(option);
                //$("#actEspecCbo_upd").html(option);
                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
            }
        }
    });
}

// Isertar 
function cuantRiesgoIns() {
    $("#actEspecAmenCbo").val("");
    $("#amenaza_ins").val("[...Seleccione...]");
    $("#vulnerabilidad_ins").val("");
    $("#impacto_ins").val("");
    $("#ins_error").html("");
    $("#ins_error").css("visibility", "hidden");

    $("#dins").dialog({
        modal: true,
        width: 490,
        buttons: {
            "Cancelar": function () {
                $(this).dialog("close");
            },
            "Enviar Datos": function () {
                $.ajax({
                    url: "../../CuantRiesgo",
                    type: "post",
                    dataType: "json",
                    data: {
                        accion: "INS",
                        idactespecamen: $("#actEspecAmenCbo").val(),
                        amenaza: $("#amenaza_ins").val(),
                        vulnerabilidad: $("#vulnerabilidad_ins").val(),
                        impacto: $("#impacto_ins").val()
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
                            cuantRiesgoQry();
                            $("#dins").dialog("close");
                        }
                    }
                });
            }
        }
    });
}