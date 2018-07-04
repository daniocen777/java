package beans;

import entities.Frases;
import controller.FrasesFacade;
import beans.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "frasesController")
@ViewScoped
public class FrasesController extends AbstractController<Frases> {

    @Inject
    private AutoresController idautorController;
    @Inject
    private MobilePageController mobilePageController;

    public FrasesController() {
        // Inform the Abstract parent controller of the concrete Frases Entity
        super(Frases.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idautorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Autores controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdautor(ActionEvent event) {
        Frases selected = this.getSelected();
        if (selected != null && idautorController.getSelected() == null) {
            idautorController.setSelected(selected.getIdautor());
        }
    }

}
