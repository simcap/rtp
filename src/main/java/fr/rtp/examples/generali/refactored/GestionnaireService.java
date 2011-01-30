package fr.rtp.examples.generali.refactored;

import fr.rtp.examples.generali.BusinessException;
import fr.rtp.examples.generali.GecBusinessException;
import fr.rtp.examples.generali.GecTechnicalException;
import fr.rtp.examples.generali.IGenericService;
import fr.rtp.examples.generali.IGestionIdentiteAgenceServiceProxy;
import fr.rtp.examples.generali.IParamsPortefeuilleService;
import fr.rtp.examples.generali.TechnicalException;

public class GestionnaireService {

    //@Autowired
    private IParamsPortefeuilleService paramsPortefeuilleService;
    //@Autowired
    private IGenericService genericService;
    //@Autowired
    private IGestionIdentiteAgenceServiceProxy vitrineService;
    
    public String getGestionnaireEmailForDeclaration(String codePortefeuille, String codeCompagnie) throws GecBusinessException, GecTechnicalException {
        try {
            GestionnaireInfoHandler mainHandler = new SalarieGestionnaireInfoHandler(paramsPortefeuilleService, 
                            new TriesteGestionnaireInfoHandler(paramsPortefeuilleService, 
                            new DirectGestionnaireInfoHandler(paramsPortefeuilleService, 
                            new GenericAndVitrineGestionnaireInfoHandler(genericService, vitrineService, 
                            new NullGestionnaireInfoHandler())))
                );
            return mainHandler.resolveEmail(codePortefeuille, codeCompagnie);
        } catch (BusinessException e) {
            throw new GecBusinessException(e.getMessage(), e, GecBusinessException.ERROR_SERVICE_BUSINESS);
        } catch (TechnicalException e) {
            throw new GecTechnicalException(e.getMessage(), e, GecTechnicalException.ERROR_SERVICE_TECHNICAL);
        } catch (GecTechnicalException e) {
            throw e;
        }
    }
}
