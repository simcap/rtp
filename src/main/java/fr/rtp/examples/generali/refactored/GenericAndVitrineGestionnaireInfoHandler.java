package fr.rtp.examples.generali.refactored;

import fr.rtp.examples.generali.IGenericService;
import fr.rtp.examples.generali.IGestionIdentiteAgenceServiceProxy;

public class GenericAndVitrineGestionnaireInfoHandler extends AbstractGestionnaireInfoHandler{


    public GenericAndVitrineGestionnaireInfoHandler(IGenericService genericService,
            IGestionIdentiteAgenceServiceProxy vitrineService, GestionnaireInfoHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public String resolveEmail(String codePortefeuille, String codeCompagnie) {
        return null;
    }

}
