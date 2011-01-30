package fr.rtp.examples.generali.refactored;

import fr.rtp.examples.generali.IParamsPortefeuilleService;

public class DirectGestionnaireInfoHandler extends AbstractGestionnaireInfoHandler {

    public DirectGestionnaireInfoHandler(IParamsPortefeuilleService paramsPortefeuilleService, 
            GestionnaireInfoHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public String resolveEmail(String codePortefeuille, String codeCompagnie) {
        return null;
    }

}
