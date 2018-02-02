$(function () {
    $("form.parainfo fieldset").addClass("ui-widget-content");
    $("form.parainfo legend").addClass("ui-widget-header ui-widget-content ui-corner-all");

    // input ==> Esquinas redondeadas
    $("form.parainfo input[type=text]").addClass("ui-widget ui-widget-content ui-corner-all");
    $("form.parainfo input[type=password]").addClass("ui-widget ui-widget-content ui-corner-all");
    $("form.parainfo textarea").addClass("ui-widget ui-widget-content ui-corner-all");
    $("form.parainfo select").addClass("ui-widget ui-widget-content ui-corner-all");
    // Al Select o a los botones, sin necesidad que esté en un formulario
    $(".form-parainfo").addClass("ui-widget ui-widget-content ui-corner-all");

    // Botones
    $("form.parainfo input[type=submit]").button();
    $("form.parainfo input[type=button]").button();
    // Botón de HTML
    $("form.parainfo button").button();
});
