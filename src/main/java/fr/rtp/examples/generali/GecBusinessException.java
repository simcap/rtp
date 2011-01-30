package fr.rtp.examples.generali;

public class GecBusinessException extends Exception {

    private static final long serialVersionUID = -2314917763515023491L;

    public GecBusinessException(String message, BusinessException e, String errorServiceBusiness) {
    }

    public static final String ERROR_SERVICE_BUSINESS = null;

    public Object getKey() {
        return null;
    }

}
