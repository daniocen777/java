function tipoEventosIns() {
    window.location = "tipoEventosIns.jsp";
}

function tipoEventosQry() {
    //window.location = "index.jsp";
    window.location = "TipoEventos?accion=QRY";
}

function tipoEventosDel() {
    var ids = [];

    $("input[name='idtipoEvento_del']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        message("Advertencia", "Seleccione fila(s) a Retirar");

    } else {
        $("#message").html("¿Retirar registro(s)?");
        $("#dlg_message").dialog({
            modal: true,
            title: "Advertencia",
            width: 340,
            buttons: {
                "No": function () {
                    $(this).dialog("close");
                },
                "Si": function () {
                    $(this).dialog("close");
                    window.location = "TipoEventos?accion=DEL&ids=" + ids.toString();
                }
            }
        });
    }
}

function tipoEventosUpd() {
    var id = $("input[name='idtipoEvento_upd']:checked").val();

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");

    } else {
        window.location = "TipoEventos?accion=GET&idtipoEvento=" + id;
    }
}

//function eventoQry() {
//    window.location = "Eventos?accion=QRY";
//}