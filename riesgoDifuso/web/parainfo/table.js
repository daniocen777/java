//EN LA CARGA DEL JQUERY
$(function () {
    // CABECERA
    $("table.parainfo thead").addClass("ui-widget-header");

    // Ïconos (+, -, check)
    $("table.parainfo .crud .ins span").addClass("ui-icon ui-icon-plusthick");
    $("table.parainfo .crud .del span").addClass("ui-icon ui-icon-trash");
    $("table.parainfo .crud .upd span").addClass("ui-icon ui-icon-pencil");
    $("table.parainfo .crud .qry span").addClass("ui-icon ui-icon-folder-open");

    // Cuando se pasa por uno de lo íconoes, se resalta
    $("table.parainfo .crud").mouseover(function () {
        // La resalta
        $(this).addClass("ui-state-hover");
    }).mouseout(function () {
        // Le quita el resaltado
        $(this).removeClass("ui-state-hover");
    });
});

