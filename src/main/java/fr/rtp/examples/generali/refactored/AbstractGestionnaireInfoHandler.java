package fr.rtp.examples.generali.refactored;

public abstract class AbstractGestionnaireInfoHandler implements GestionnaireInfoHandler {
    
    private GestionnaireInfoHandler nextHandler;

    public AbstractGestionnaireInfoHandler(GestionnaireInfoHandler nextHandler) {
    }

    protected GestionnaireInfoHandler getNextHandler() {
        if(nextHandler == null) {
            return new NullGestionnaireInfoHandler();
        } else return nextHandler;
    }

}
