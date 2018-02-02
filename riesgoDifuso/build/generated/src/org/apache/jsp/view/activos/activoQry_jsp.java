package org.apache.jsp.view.activos;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class activoQry_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/view/activos/../../WEB-INF/jspf/menuadmin.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"../../jq/jquery-ui.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../../parainfo/form.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../../parainfo/table.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../../parainfo/message.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../../jq/menu/menu.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../../css/main.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"../../jq/jquery-3.2.1.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../jq/jquery-ui.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../parainfo/form.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../parainfo/table.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../parainfo/message.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../jq/menu/menu.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../../js/main.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"m_main\">\n");
      out.write("            <div id=\"m_menu\">\n");
      out.write("                ");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"menu\">\n");
      out.write("    <ul class=\"menu\" style=\"width: 100%\">\n");
      out.write("        <li><a href=\"#\" class=\"parent\"><span>Mis Datos</span></a>\n");
      out.write("            <div>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"#\"><span>Cambiar Password</span></a></li>\n");
      out.write("                    <li><a href=\"../../Usuarios?accion=LOGOUT\"><span>Cerrar Sesi&oacute;n</span></a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </li>\n");
      out.write("\n");
      out.write("        <li>\n");
      out.write("            <a href=\"#\" class=\"parent\"><span>Categor&iacute;as</span></a>\n");
      out.write("            <div>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"#\" class=\"parent\"><span>Sub Item 1</span></a>\n");
      out.write("                        <div>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"#\" class=\"parent\"><span>Sub Item 1.1</span></a>\n");
      out.write("                                    <div>\n");
      out.write("                                        <ul>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 1.1.1</span></a></li>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 1.1.2</span></a></li>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 1.2</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 1.3</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 1.4</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 1.5</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 1.6</span></a></li>\n");
      out.write("                                <li><a href=\"#\" class=\"parent\"><span>Sub Item 1.7</span></a>\n");
      out.write("                                    <div>\n");
      out.write("                                        <ul>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 1.7.1</span></a></li>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 1.7.2</span></a></li>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                    <li><a href=\"#\"><span>Sub Item 2</span></a></li>\n");
      out.write("                    <li><a href=\"#\"><span>Sub Item 3</span></a></li>\n");
      out.write("\n");
      out.write("                    ");
      out.write("\n");
      out.write("                    <li><a href=\"#\" class=\"parent\"><span>Activos</span></a>\n");
      out.write("                        <div>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"../../view/activos/activoQry.jsp\" ><span>Lista de Activos</span></a></li>\n");
      out.write("                                <li><a href=\"#\" onclick=\"autoresIns();\"><span>Nuevo Tutorial</span></a></li>\n");
      out.write("                                <li><a href=\"#\" onclick=\"\"><span>Actualizar Tutorial</span></a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                    ");
      out.write("\n");
      out.write("                    <li><a href=\"#\" class=\"parent\"><span>Frases</span></a>\n");
      out.write("                        <div>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"../../view/admin/frasesQry.jsp\"><span>Lista de Frases</span></a></li>\n");
      out.write("                                <li><a href=\"#\" onclick=\"\"><span>Nuevo Frase</span></a></li>\n");
      out.write("                                <li><a href=\"#\" onclick=\"\"><span>Actualizar Frase</span></a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </li>\n");
      out.write("\n");
      out.write("        <li>\n");
      out.write("            <a href=\"#\" class=\"parent\"><span>Productos</span></a>\n");
      out.write("            <div>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"#\"><span>Sub Item 1</span></a></li>\n");
      out.write("                    <li><a href=\"#\"><span>Sub Item 2</span></a></li>\n");
      out.write("                    <li><a href=\"#\" class=\"parent\"><span>Sub Item 3</span></a>\n");
      out.write("                        <div>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"#\" class=\"parent\"><span>Sub Item 3.1</span></a>\n");
      out.write("                                    <div>\n");
      out.write("                                        <ul>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 3.1.1</span></a></li>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 3.1.2</span></a></li>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 3.2</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 3.3</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 3.4</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 3.5</span></a></li>\n");
      out.write("                                <li><a href=\"#\"><span>Sub Item 3.6</span></a></li>\n");
      out.write("                                <li><a href=\"#\" class=\"parent\"><span>Sub Item 3.7</span></a>\n");
      out.write("                                    <div>\n");
      out.write("                                        <ul>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 3.7.1</span></a></li>\n");
      out.write("                                            <li><a href=\"#\"><span>Sub Item 3.7.2</span></a></li>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </li>\n");
      out.write("\n");
      out.write("        <li class=\"last\">\n");
      out.write("            <a href=\"../../Usuarios?accion=LOGOUT\"><span>Cerrar Sesi&oacute;n</span></a>\n");
      out.write("        </li>\n");
      out.write("    </ul>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"copyright\" style=\"display: none\">\n");
      out.write("    Copyright &copy; 2012 \n");
      out.write("    <a href=\"http://apycom.com/\">\n");
      out.write("        Apycom jQuery Menus</a>\n");
      out.write("</div>\n");
      out.write("<div style=\"clear: both\"></div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <form class=\"parainfo\">\n");
      out.write("                <button type=\"button\" onclick=\"activoIns();\"><span class=\"ui-icon ui-icon-disk\"></span> Enviar Datos</button>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div id=\"m_body\">\n");
      out.write("                <table class=\"parainfo\" style=\"margin: auto;width: 500px\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Activo</td>\n");
      out.write("                            <td>Tipo de activo</td>\n");
      out.write("                            <th class=\"crud\">\n");
      out.write("                                <a class=\"upd\" href=\"#\" \n");
      out.write("                                   title=\"Actualizar Registro\"><span></span></a>\n");
      out.write("                            </th>\n");
      out.write("                            <th class=\"crud\">\n");
      out.write("                                <a class=\"del\" href=\"#\" onclick=\"activossDel();\"\n");
      out.write("                                   title=\"Retirar Registro\"><span></span></a>\n");
      out.write("                            </th>\n");
      out.write("\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody id=\"activoQry\"></tbody>\n");
      out.write("                </table>\n");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                <div id=\"msg_error\" \n");
      out.write("                     class=\"msg_error ui-state-error ui-corner-all\"></div>\n");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                <div style=\"display: none\">\n");
      out.write("                    <div id=\"dlg_message\"><p id=\"message\"></p></div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                <div style=\"display: none\">\n");
      out.write("                    <div id=\"dins\" title=\"Nuevo registro\">\n");
      out.write("                        <form class=\"parainfo\">\n");
      out.write("                            <table>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>Activo</td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" id=\"nombact_ins\" \n");
      out.write("                                               maxlength=\"200\" style=\"width: 300px\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>Tipo de Activo</td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <select id=\"tipoact_ins\" style=\"width: 310px\">\n");
      out.write("                                            <option value=\"Hardware\">Hardware</option>\n");
      out.write("                                            <option value=\"Software\">Software</option>\n");
      out.write("                                        </select>\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        </form>\n");
      out.write("                        <div id=\"ins_error\" \n");
      out.write("                             class=\"msg_error ui-state-error ui-corner-all\"></div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
