package fr.rtp.examples.generali.refactored;

import fr.rtp.examples.generali.BusinessException;
import fr.rtp.examples.generali.GecTechnicalException;
import fr.rtp.examples.generali.TechnicalException;

public interface GestionnaireInfoHandler {

    public String resolveEmail(String codePortefeuille, String codeCompagnie)
        throws TechnicalException, BusinessException, GecTechnicalException;

}
