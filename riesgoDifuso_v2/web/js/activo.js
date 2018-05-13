$(function () {

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
