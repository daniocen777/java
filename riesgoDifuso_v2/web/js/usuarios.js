//$(function () {
//    usuariosQry();
//});
//
//// Función para listar (Qry)
//function usuariosQry() {
//    $.ajax({
//        url: "../../UsuariosOperaciones",
//        dataType: "json",
//        type: "post",
//        data: {
//            accion: "QRY"
//        },
//        success: function (data) {
//            var msg = data[0].msg;
//
//            if ($.trim(msg).length !== 0) {
//                $("#msg_error").text(msg);
//                $("#msg_error").css("visibility", "visible");
//                $("#usuariosQry").html("");
//
//            } else {
//                var body = "";
//
//                for (var i = 1; i < data.length; ++i) {
//                    var idusuario = data[i].idusuario;
//                    var apellidos = data[i].apellidos;
//                    var nombres = data[i].nombres;
//                    var usuario = data[i].usuario;
//                    var autorizacion = data[i].autorizacion;
//
//                    body += "<tr>"
//                            + "<td>" + apellidos + "</td>"
//                            + "<td>" + nombres + "</td>"
//                            + "<td>" + usuario + "</td>"
//                            + "<td>" + autorizacion + "</td>"
//                            + "<th><input type='radio' name='idusuario_upd' value='" + idusuario + "'/></th>"
//                            + "<th><input type='checkbox' name='idusuario_del' value='" + idusuario + "'/></th>"
//                            + "</tr>";
//                }
//
//                $("#usuariosQry").html(body);
//                $("#msg_error").css("visibility", "hidden"); // si Ok, limpia
//            }
//        }
//    });
//}
//
//// Insertar usuario
//function usuariosIns() {
//    $("#apellidos_ins").val("");
//    $("#nombres_ins").val("");
//    $("#usuario_ins").val("");
//    $("#password_ins").val("");
//    $("#passwordConfirm_ins").val("");
//    $("#autorizacion_ins").val("");
//    $("#ins_error").html("");
//    $("#ins_error").css("visibility", "hidden");
//
//    $("#dins").dialog({
//        modal: true,
//        width: 440,
//        buttons: {
//            "Cancelar": function () {
//                $(this).dialog("close");
//            },
//            "Enviar Datos": function () {
//                $.ajax({
//                    url: "../../UsuariosOperaciones",
//                    type: "post",
//                    dataType: "json",
//                    data: {
//                        accion: "INS",
//                        apellidos: $("#apellidos_ins").val(),
//                        nombres: $("#nombres_ins").val(),
//                        usuario: $("#usuario_ins").val(),
//                        password: $("#password_ins").val(),
//                        passwordConfirm: $("#passwordConfirm_ins").val(),
//                        autorizacion: $("#autorizacion_ins").val()
//                    },
//                    success: function (error) {
//                        var msg = error[0].msg;
//
//                        if ($.trim(msg).length !== 0 && msg !== 'ok') {
//                            var ctos = error.length;
//
//                            var msg = "<ul>";
//                            for (var i = 0; i < ctos; ++i) {
//                                msg += "<li>" + error[i].msg + "</li>";
//                            }
//                            msg += "</ul>";
//
//                            $("#ins_error").html(msg);
//                            $("#ins_error").css("visibility", "visible");
//
//                        } else {
//                            $("#msg_war").html("");
//                            $("#msg_war").css("visibility", "hidden");
//                            $("#msg_alert").html("");
//                            $("#msg_alert").css("visibility", "hidden");
//                            var ok = "<p>" + "Se agregó nueva amenaza" + "</p>";
//                            $("#msg_ok").html(ok);
//                            $("#msg_ok").css("visibility", "visible");
//                            usuariosQry();
//                            $("#dins").dialog("close");
//                        }
//                    }
//                });
//            }
//        }
//    });
//}