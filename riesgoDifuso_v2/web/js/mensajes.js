$(function () {
    $("#msg_ok").html("");
    $("#msg_ok").css("visibility", "hidden");
    $("#msg_war").html("");
    $("#msg_war").css("visibility", "hidden");
    $("#msg_alert").html("");
    $("#msg_alert").css("visibility", "hidden");
});

// Cerrar mensajes
function cerrarmensajes() {
    $("#msg_ok").html("");
    $("#msg_ok").css("visibility", "hidden");
    $("#msg_war").html("");
    $("#msg_war").css("visibility", "hidden");
    $("#msg_alert").html("");
    $("#msg_alert").css("visibility", "hidden");

    $("#msg_error").html("");
    $("#msg_error").css("visibility", "hidden");
}