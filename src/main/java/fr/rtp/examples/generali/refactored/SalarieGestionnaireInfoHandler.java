package fr.rtp.examples.generali.refactored;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import fr.rtp.examples.generali.BusinessException;
import fr.rtp.examples.generali.GecTechnicalException;
import fr.rtp.examples.generali.IParamsPortefeuilleService;
import fr.rtp.examples.generali.ParamsPortefeuille;
import fr.rtp.examples.generali.TechnicalException;

public class SalarieGestionnaireInfoHandler extends AbstractGestionnaireInfoHandler {

private IParamsPortefeuilleService paramsPortefeuilleService;
    
    public SalarieGestionnaireInfoHandler(IParamsPortefeuilleService paramsPortefeuilleService, 
            GestionnaireInfoHandler nextHandler) {
        super(nextHandler);
        this.paramsPortefeuilleService = paramsPortefeuilleService;
    }

    public String resolveEmail(String codePortefeuille, String codeCompagnie) throws TechnicalException, BusinessException, GecTechnicalException {
        ParamsPortefeuille portefeuilleSalarie = paramsPortefeuilleService.getParamsPortefeuilleSalarie(codeCompagnie);
        if(portefeuilleHasNoEmail(portefeuilleSalarie)){
            return getNextHandler().resolveEmail(codePortefeuille, codeCompagnie);
        } else {
            log.info("Email gestionnaire '" + portefeuilleSalarie.getAdresseMail() 
                  + "' resolu avec SALARIE portefeuille service [codeCompagnie='" + codeCompagnie + ", codePortefeuille='" + codePortefeuille + "].");
            return portefeuilleSalarie.getAdresseMail();
        }
    }

    private boolean portefeuilleHasNoEmail(ParamsPortefeuille paramsPortefeuille) {
        return paramsPortefeuille == null || StringUtils.isBlank(paramsPortefeuille.getAdresseMail());
    }

    private static final Logger log = Logger.getLogger(SalarieGestionnaireInfoHandler.class.getName());
    
    
}
