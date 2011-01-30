package fr.rtp.examples.generali.refactored;

import fr.rtp.examples.generali.BusinessException;
import fr.rtp.examples.generali.GecTechnicalException;
import fr.rtp.examples.generali.IParamsPortefeuilleService;
import fr.rtp.examples.generali.TechnicalException;

public class TriesteGestionnaireInfoHandler extends AbstractGestionnaireInfoHandler{

    public TriesteGestionnaireInfoHandler(IParamsPortefeuilleService paramsPortefeuilleService,
            GestionnaireInfoHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public String resolveEmail(String codePortefeuille, String codeCompagnie) throws TechnicalException,
            BusinessException, GecTechnicalException {
        return null;
    }

}
