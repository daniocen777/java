package util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class Filtro implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = pe.getFacesContext();
        String currentPage = context.getViewRoot().getViewId(); // Página actual
        // Si es la de login
        boolean isPageLogin = currentPage.lastIndexOf("login.xhtml") > -1 ? true : false;
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        // Recuperar el objeto
        Object usuario = session.getAttribute("usuario");
        
        if (!isPageLogin && usuario == null) {
            NavigationHandler handler = context.getApplication().getNavigationHandler();
            handler.handleNavigation(context, null, "/login.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
