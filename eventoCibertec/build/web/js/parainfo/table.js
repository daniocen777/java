$(function() {
    $("table.parainfo thead").addClass("ui-widget-header");

    $("table.parainfo .crud .ins span").addClass("ui-icon ui-icon-plusthick");
    $("table.parainfo .crud .del span").addClass("ui-icon ui-icon-trash");
    $("table.parainfo .crud .upd span").addClass("ui-icon ui-icon-pencil");
    $("table.parainfo .crud .qry span").addClass("ui-icon ui-icon-folder-open");

    $("table.parainfo .crud").mouseover(function() {
        $(this).addClass("ui-state-hover");
    }).mouseout(function() {
        $(this).removeClass("ui-state-hover");
    });
});

