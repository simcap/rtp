package fr.rtp.examples.generali;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class IntermediaireServiceTest {

    private IntermediaireService service;
    @Mock private IParamsPortefeuilleService paramsPortefeuilleServiceMock;
    @Mock private IGenericService genericServiceMock;
    @Mock private IGestionIdentiteAgenceServiceProxy vitrineServiceMock;

    
    @Test @Ignore
    public void should_return_email_from_SALARIE_service_if_email_is_present() throws Exception {
        String codeCompagnie = "aze65q4g6+54", codePortefeuille = "anything";
        String salarieEmail = "notnull@addresse.email";
        ParamsPortefeuille paramPortefeuille = newPortefeuilleWithEmail(salarieEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(codeCompagnie))
            .thenReturn(paramPortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(salarieEmail, resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_email_from_TRIESTE_service_if_object_from_SALARIE_service_is_null() throws Exception {
        String codePortefeuille = "6zeg54q6g5", codeCompagnie = "aze65q4g6+54";
        String triesteEmail = "notnull@addresse.email";
        ParamsPortefeuille triestePortefeuille = newPortefeuilleWithEmail(triesteEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(codeCompagnie))
             .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triestePortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(triesteEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_TRIESTE_service_if_email_object_from_SALARIE_service_is_null() throws Exception {
        String codePortefeuille = "6zeg54q6g5", codeCompagnie = "aze65q4g6+54";
        String triesteEmail = "notnull@addresse.email";
        ParamsPortefeuille salariePortefeuille = newPortefeuilleWithEmail(null);
        ParamsPortefeuille triestePortefeuille = newPortefeuilleWithEmail(triesteEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(codeCompagnie))
            .thenReturn(salariePortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triestePortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(triesteEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_TRIESTE_service_if_email_object_from_SALARIE_service_is_empty() throws Exception {
        String codePortefeuille = "6zeg54q6g5", codeCompagnie = "aze65q4g6+54";
        String triesteEmail = "notnull@addresse.email";
        ParamsPortefeuille salariePortefeuille = newPortefeuilleWithEmail("");
        ParamsPortefeuille triestePortefeuille = newPortefeuilleWithEmail(triesteEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(codeCompagnie))
            .thenReturn(salariePortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triestePortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(triesteEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_TRIESTE_service_if_email_object_from_SALARIE_service_is_whitespace() throws Exception {
        String codePortefeuille = "6zeg54q6g5", codeCompagnie = "aze65q4g6+54";
        String triesteEmail = "notnull@addresse.email";
        ParamsPortefeuille salariePortefeuille = newPortefeuilleWithEmail("       ");
        ParamsPortefeuille triestePortefeuille = newPortefeuilleWithEmail(triesteEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(codeCompagnie))
            .thenReturn(salariePortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triestePortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(triesteEmail, resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_intermediaire_email_from_GENERIC_service_if_email_from_VITRINE_is_whitespace() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", intermediaireEmail = "generic@intermediaire.mail";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithIntermediaireEmail(intermediaireEmail);
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail("          ");
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_intermediaire_email_from_GENERIC_service_if_email_from_VITRINE_is_empty() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", intermediaireEmail = "generic@intermediaire.mail";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithIntermediaireEmail(intermediaireEmail);
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail("");
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_intermediaire_email_from_GENERIC_service_if_email_from_VITRINE_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", intermediaireEmail = "generic@intermediaire.mail";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithIntermediaireEmail(intermediaireEmail);
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail(null);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_intermediaire_email_from_GENERIC_service_if_object_from_VITRINE_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", intermediaireEmail = "generic@intermediaire.mail";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithIntermediaireEmail(intermediaireEmail);
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(null);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_bureau_email_from_VITRINE_service_if_email_from_GENERIC_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+";
        String emailFromVitrine = "email@vitrine.com";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail(null);
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail(emailFromVitrine);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(emailFromVitrine, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_bureau_email_from_VITRINE_service_if_email_from_GENERIC_is_empty() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", emailFromVitrine = "email@vitrine.com";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail("");
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail(emailFromVitrine);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(emailFromVitrine, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_bureau_email_from_VITRINE_service_if_email_from_GENERIC_is_whitespace() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+", emailFromVitrine = "email@vitrine.com";
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail("         ");
        Bureau bureauFromGeneric = newGenericBureauWithId(codeBureauFromGeneric);
        genericInfoIntermediaire.setBureau(bureauFromGeneric);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail(emailFromVitrine);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(emailFromVitrine, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_null_email_from_if_object_from_GENERIC_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String codeBureauFromGeneric = "qz3rg54se6rh354+";
        String emailFromVitrine = "email@vitrine.com";
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        Bureau bureauFromVitrine = newVitrineBureauWithEmail(emailFromVitrine);
        when(vitrineServiceMock.recupererInfosAgence(codeBureauFromGeneric))
            .thenReturn(bureauFromVitrine);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertNull(resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_bureau_email_from_GENERIC_service_if_param_object_from_DIRECT_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String intermediaireEmail = "generic@intermediaire.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail(null);
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail(intermediaireEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }
    
    @Test @Ignore
    public void should_return_bureau_email_from_GENERIC_service_if_email_from_DIRECT_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String intermediaireEmail = "generic@intermediaire.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail(null);
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail(null);
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail(intermediaireEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(directParamPortefeuille);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
            .thenReturn(genericInfoIntermediaire);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_bureau_email_from_GENERIC_service_if_email_from_DIRECT_is_empty() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String intermediaireEmail = "generic@intermediaire.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail(null);
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail("");
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail(intermediaireEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
        .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
        .thenReturn(directParamPortefeuille);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
        .thenReturn(genericInfoIntermediaire);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_bureau_email_from_GENERIC_service_if_email_from_DIRECT_is_whitespace() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String intermediaireEmail = "generic@intermediaire.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail(null);
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail("     ");
        InformationIntermediaire genericInfoIntermediaire = newIntermediaireWithBureauEmail(intermediaireEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
        .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
        .thenReturn(directParamPortefeuille);
        when(genericServiceMock.getInformationIntermediaire(codeCompagnie, codePortefeuille))
        .thenReturn(genericInfoIntermediaire);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(intermediaireEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_DIRECT_service_if_email_from_TRIESTE_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String directEmail = "direct@addresse.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail(null);
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail(directEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(directParamPortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(directEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_DIRECT_service_if_email_from_TRIESTE_is_empty() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String directEmail = "direct@addresse.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail("");
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail(directEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(directParamPortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(directEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_DIRECT_service_if_email_from_TRIESTE_is_whitespace() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String directEmail = "direct@addresse.mail";
        ParamsPortefeuille triesteParamPortefeuille = newPortefeuilleWithEmail("     ");
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail(directEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
        .thenReturn(triesteParamPortefeuille);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
        .thenReturn(directParamPortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(directEmail, resolvedEmail);
    }

    @Test @Ignore
    public void should_return_email_from_DIRECT_service_if_params_object_from_TRIESTE_is_null() throws Exception {
        String codePortefeuille = "qze6g5qr6", codeCompagnie = "qze3g21qse3h1+54";
        String directEmail = "direct@addresse.mail";
        ParamsPortefeuille directParamPortefeuille = newPortefeuilleWithEmail(directEmail);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(codeCompagnie, codePortefeuille))
            .thenReturn(null);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(codeCompagnie, codePortefeuille))
            .thenReturn(directParamPortefeuille);
        String resolvedEmail = service.getGestionnaireEmailForDeclaration(codePortefeuille, codeCompagnie);
        assertEquals(directEmail, resolvedEmail);
    }
    
    
    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
        service = new IntermediaireService();
        ReflectionTestUtils.setField(service, "paramsPortefeuilleService", paramsPortefeuilleServiceMock, IParamsPortefeuilleService.class);
        ReflectionTestUtils.setField(service, "genericService", genericServiceMock, IGenericService.class);
        ReflectionTestUtils.setField(service, "vitrineService", vitrineServiceMock, IGestionIdentiteAgenceServiceProxy.class);
    }

    private Bureau newGenericBureauWithId(String codeBureauFromGeneric) {
        Bureau bureauFromGeneric = new Bureau(); bureauFromGeneric.setId(codeBureauFromGeneric);
        return bureauFromGeneric;
    }

    private Bureau newVitrineBureauWithEmail(String emailFromVitrine) {
        Bureau bureauFromVitrine = new Bureau();
        bureauFromVitrine.setEmail(emailFromVitrine);
        return bureauFromVitrine;
    }

    private InformationIntermediaire newIntermediaireWithBureauEmail(String intermediaireEmail) {
        InformationIntermediaire infoIntermediaire = new InformationIntermediaire();
        Bureau bureau = new Bureau();
        bureau.setEmail(intermediaireEmail);
        infoIntermediaire.setBureau(bureau);
        return infoIntermediaire;
    }

    private InformationIntermediaire newIntermediaireWithIntermediaireEmail(String intermediaireEmail) {
        InformationIntermediaire infoIntermediaire = new InformationIntermediaire();
        Intermediaire intermediaire = new Intermediaire();
        intermediaire.setEmail(intermediaireEmail);
        infoIntermediaire.setIntermediaire(intermediaire);
        return infoIntermediaire;
    }

    private ParamsPortefeuille newPortefeuilleWithEmail(String email) {
        ParamsPortefeuille paramPortefeuille = new ParamsPortefeuille();
        paramPortefeuille.setAdresseMail(email);
        return paramPortefeuille;
    }
}