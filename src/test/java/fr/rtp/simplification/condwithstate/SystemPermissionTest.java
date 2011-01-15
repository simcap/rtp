package fr.rtp.simplification.condwithstate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class SystemPermissionTest {

    private SystemPermission permission;
    
    
    @Test
    public void grantedBy() throws Exception {
        SystemAdmin systemAdmin = new SystemAdmin();
        permission.grantedBy(systemAdmin);
        assertEquals("requested", permission.REQUESTED, permission.state());
        assertEquals("not granted", false, permission.isGranted());
        permission.claimedBy(systemAdmin);
        permission.grantedBy(systemAdmin);        
        assertEquals("granted", permission.GRANTED, permission.state());
        assertEquals("granted", true, permission.isGranted());
    }

    @Test
    public void deniedBy() throws Exception {
        SystemAdmin systemAdmin = new SystemAdmin();
        permission.deniedBy(systemAdmin);
        assertEquals("requested", permission.REQUESTED, permission.state());
        assertEquals("not granted", false, permission.isGranted());
        permission.claimedBy(systemAdmin);
        permission.deniedBy(systemAdmin);        
        assertEquals("denied", permission.DENIED, permission.state());
        assertEquals("denied", false, permission.isGranted());
    }
    
    @Before
    public void initBeforeTest() throws Exception {
        SystemUser user = new SystemUser();
        SystemProfile profile = new SystemProfile();
        permission = new SystemPermission(user, profile);
    }
}
