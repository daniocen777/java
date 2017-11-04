function eventosIns() {
    window.location = "Eventos?accion=CBO";
}

function eventosQry() {
    window.location = "index.jsp";
}

function eventosDel() {
    var ids = [];

    $("input[name='idevento_del']:checked").each(function () {
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
                    window.location = "Eventos?accion=DEL&ids=" + ids.toString();
                }
            }
        });
    }
}

function eventosUpd() {
    var id = $("input[name='idevento_upd']:checked").val();

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");
        
    } else {
        window.location = "Eventos?accion=GET&idevento=" + id;
    }
}

//function tipoEventoQry() {
//    window.location = "TipoEventos?accion=QRY";
//}