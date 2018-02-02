package web.tag;

import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Fecha extends SimpleTagSupport {
    
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato de fecha
            Date fecha = new Date(System.currentTimeMillis()); // Fecha de hoy
            // Salida
            out.print(sdf.format(fecha));
            
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Fecha tag", ex);
        }
    }
    
}
