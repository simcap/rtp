package fr.rtp.examples.generali;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

public class IntermediaireService {

    //@Autowired
    private IParamsPortefeuilleService paramsPortefeuilleService;
    //@Autowired
    private IGenericService genericService;
    //@Autowired
    private IGestionIdentiteAgenceServiceProxy vitrineService;
    
    public String getGestionnaireEmailForDeclaration(String codePortefeuille, String codeCompagnie) throws GecBusinessException, GecTechnicalException {
        try {
            ParamsPortefeuille paramsPortefeuille = paramsPortefeuilleService.getParamsPortefeuilleSalarie(codeCompagnie);
            if(portefeuilleHasNoEmail(paramsPortefeuille)){
                paramsPortefeuille = paramsPortefeuilleService.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille);
                if(portefeuilleHasNoEmail(paramsPortefeuille)){
                    paramsPortefeuille = paramsPortefeuilleService.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille);
                    if(portefeuilleHasNoEmail(paramsPortefeuille)){
                        return emailFromGenericAndVitrineService(codePortefeuille, codeCompagnie);
                    } else {
                        logEmailResolutionInfo(paramsPortefeuille.getAdresseMail(), "portefeuille DIRECT", codePortefeuille, codeCompagnie);
                        return paramsPortefeuille.getAdresseMail();
                    }
                } else {
                    logEmailResolutionInfo(paramsPortefeuille.getAdresseMail(), "portefeuille TRIESTE", codePortefeuille, codeCompagnie);
                    return paramsPortefeuille.getAdresseMail();
                }
            } else {
                logEmailResolutionInfo(paramsPortefeuille.getAdresseMail(), "portefeuille SALARIE", codePortefeuille, codeCompagnie);
                return paramsPortefeuille.getAdresseMail();
            }
        } catch (BusinessException e) {
            throw new GecBusinessException(e.getMessage(), e, GecBusinessException.ERROR_SERVICE_BUSINESS);
        } catch (TechnicalException e) {
            throw new GecTechnicalException(e.getMessage(), e, GecTechnicalException.ERROR_SERVICE_TECHNICAL);
        } catch (GecTechnicalException e) {
            throw e;
        }
    }

    private String emailFromGenericAndVitrineService(String codePortefeuille, String codeCompagnie) throws GecTechnicalException, IdentiteAgenceNonTrouveProxyBusinessException {
        InformationIntermediaire genericIntermediaire = genericService.getInformationIntermediaire(codeCompagnie, codePortefeuille);
        if(genericIntermediaire == null){
            log.severe("ERREUR - Email gestionnaire NON resolu. Generic service retourne info intermediaire null pour [codePortefeuille='" + codePortefeuille + "', [codeCompagnie='" + codeCompagnie + "'].");
            return null;
        }
        if(StringUtils.isBlank(genericIntermediaire.getBureau().getEmail())){
            Bureau bureauFromVitrine = vitrineService.recupererInfosAgence(genericIntermediaire.getBureau().getId());
            if(vitrineBureauHasNoEmail(bureauFromVitrine)){
                logEmailResolutionInfo(genericIntermediaire.getIntermediaire().getEmail(), "mail intermediaire GENERIC", codePortefeuille, codeCompagnie);
                return genericIntermediaire.getIntermediaire().getEmail();
            } else {
                logEmailResolutionInfo(bureauFromVitrine.getEmail(), "mail bureau VITRINE", codePortefeuille, codeCompagnie);
                return bureauFromVitrine.getEmail();
            }
        } else {
            logEmailResolutionInfo(genericIntermediaire.getBureau().getEmail(), "mail bureau GENERIC", codePortefeuille, codeCompagnie);
            return genericIntermediaire.getBureau().getEmail();
        }
    }

    private boolean vitrineBureauHasNoEmail(Bureau bureauFromVitrine) {
        return bureauFromVitrine == null || StringUtils.isBlank(bureauFromVitrine.getEmail());
    }

    private boolean portefeuilleHasNoEmail(ParamsPortefeuille paramsPortefeuille) {
        return paramsPortefeuille == null || StringUtils.isBlank(paramsPortefeuille.getAdresseMail());
    }

    private void logEmailResolutionInfo(String adresseMail, String serviceName, String codePortefeuille, String codeCompagnie) {
        StringBuilder info = new StringBuilder();
        info.append("Email gestionnaire '"); 
        info.append(adresseMail);
        info.append("' resolu avec "); 
        info.append(serviceName);
        info.append(" service [codePortefeuille='");
        info.append(codePortefeuille);
        info.append("', codeCompagnie='");
        info.append(codeCompagnie);
        info.append("'].");
        log.info(info.toString());
    }

    private static final Logger log = Logger.getLogger(IntermediaireService.class.getName());
}
