package beans;

import entities.Autores;
import entities.Frases;
import java.util.Collection;
import controller.AutoresFacade;
import beans.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "autoresController")
@ViewScoped
public class AutoresController extends AbstractController<Autores> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isFrasesCollectionEmpty;

    public AutoresController() {
        // Inform the Abstract parent controller of the concrete Autores Entity
        super(Autores.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsFrasesCollectionEmpty();
    }

    public boolean getIsFrasesCollectionEmpty() {
        return this.isFrasesCollectionEmpty;
    }

    private void setIsFrasesCollectionEmpty() {
        Autores selected = this.getSelected();
        if (selected != null) {
            AutoresFacade ejbFacade = (AutoresFacade) this.getFacade();
            this.isFrasesCollectionEmpty = ejbFacade.isFrasesCollectionEmpty(selected);
        } else {
            this.isFrasesCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Frases entities that are
     * retrieved from Autores and returns the navigation outcome.
     *
     * @return navigation outcome for Frases page
     */
    public String navigateFrasesCollection() {
        Autores selected = this.getSelected();
        if (selected != null) {
            AutoresFacade ejbFacade = (AutoresFacade) this.getFacade();
            Collection<Frases> selectedFrasesCollection = ejbFacade.findFrasesCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Frases_items", selectedFrasesCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/frases/index";
    }

}
