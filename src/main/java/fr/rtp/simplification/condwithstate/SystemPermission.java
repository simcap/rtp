package fr.rtp.simplification.condwithstate;

/**
 * Chapter 7 - Simplification
 * Replace state-altering conditionals with state 
 * 
 * http://www.industriallogic.com/xp/refactoring/alteringConditionalsWithState.html
 * http://www.informit.com/articles/article.aspx?p=1398607&seqNum=4
 */
public class SystemPermission {
    
    private SystemProfile profile;
    private SystemUser requestor;
    private SystemAdmin admin;
    private boolean isGranted;
    private String state;
    
    public final static String REQUESTED = "REQUESTED";
    public final static String CLAIMED = "REQUESTED";
    public final static String GRANTED = "GRANTED";
    public final static String DENIED = "DENIED";
    
    public SystemPermission(SystemUser requestor, SystemProfile profile) {
        this.requestor = requestor;
        this.profile = profile;
        state = REQUESTED;
        isGranted = false;
        notifyAdminOfPermissionRequest();
    }

    public void claimedBy(SystemAdmin admin){
        if(!state.equals(REQUESTED)){
            return;
        }
        willBeHandledBy(admin);
        state = CLAIMED;
    }

    public void deniedBy(SystemAdmin admin){
        if(!state.equals(CLAIMED)){
            return;
        }
        if(!admin.equals(this.admin)){
            return;
        }
        isGranted = false;
        state = DENIED;
        notifyUserOfPermissionRequestResult();
    }

    public void grantedBy(SystemAdmin admin){
        if(!state.equals(CLAIMED)){
            return;
        }
        if(!admin.equals(this.admin)){
            return;
        }
        state = GRANTED;
        isGranted = true;
        notifyUserOfPermissionRequestResult();
    }

    private void willBeHandledBy(SystemAdmin admin) {
        this.admin = admin;
    }
    
    private void notifyUserOfPermissionRequestResult() {
    }


    private void notifyAdminOfPermissionRequest() {
    }

    public String state() {
        return state;
    }

    public boolean isGranted() {
        return isGranted;
    }
    
}
