/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.synthsys.seek.client;

import ed.synthsys.seek.dom.assay.Assay;
import ed.synthsys.seek.dom.common.ApiResponseDatum;
import ed.synthsys.seek.dom.datafile.DataFile;
import ed.synthsys.seek.dom.investigation.Investigation;
import ed.synthsys.seek.dom.study.Study;
import java.util.List;
import javax.ws.rs.ForbiddenException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tzielins
 */
public class SeekRestApiClientTest {
    
    public SeekRestApiClientTest() {
    }
    
    static String seekURI = "https://fairdomhub.org/";
    
    // don't commit real username and password
    static String userName = "test";
    static String password = "test";
    
    static int publicInvestigationId = 251;
    static int publicAssayId = 840;
    static int publicStudyId = 485;
    
    static int privateFileId = 2423;
    static int publicFileId = 2501;
    
    
    SeekRestApiClient client;
    
    @Before
    public void setUp() {
        
        client = new SeekRestApiClient(seekURI);
    }
    
    @After 
    public void tear() {
        client.close();
    }

    @Test
    public void testSetupWorks() {
        
        assertNotNull(client);
    }
    
    @Test
    public void testGetInvestigationWorks() {
        
        Investigation investigation = client.getInvestigation(publicInvestigationId);
        assertNotNull(investigation);
        assertNotNull(investigation.getData().getAttributes().getTitle());
        assertEquals("Millar, Andrew", investigation.getData().getAttributes().getTitle());
    }
    
    @Test
    public void testGetAssayWorks() {
        Assay assay = client.getAssay(publicAssayId);
        assertNotNull(assay);
        assertNotNull(assay.getData().getAttributes().getTitle());
        assertEquals("Arabidopsis_clock_P2011 - PLM_64, version 3", assay.getData().getAttributes().getTitle());
    }
    
    @Test
    public void testGetPrivateThrowsExceptionWithoutUser() {
        
        try {
            DataFile file = client.getDataFile(privateFileId);
            fail("Got private file without login");
        } catch (ForbiddenException e) {
            //as expected
        }
            
    }   
    
    @Test
    @Ignore("Cause needs valid user and password")    
    public void testGetPrivateFileWorks() {
        DataFile file;
        
        try {
            file = client.getDataFile(privateFileId);
            fail("Got private file without login");
        } catch (ForbiddenException e) {
            //as expected
        }
            
        client.login(userName, password);
        file = client.getDataFile(privateFileId);
        assertNotNull(file);
        assertNotNull(file.getData().getAttributes().getTitle());
        assertEquals("radio,PLM_1000_ 2", file.getData().getAttributes().getTitle());
    }    
    
    @Test
    public void testGetPublicFileWorks() {
        DataFile file = client.getDataFile(publicFileId);
        assertNotNull(file);
        assertNotNull(file.getData().getAttributes().getTitle());
        assertEquals("Fig. 1, outline of P2011 model, with P2010 inset,PLM_64_ 3", file.getData().getAttributes().getTitle());
    }    
    
    
    @Test
    public void testAssaysWorks() {
        List<ApiResponseDatum> assays = client.listAssays();
        assertTrue(assays.size() > 0);
        
    }     
    
    @Test
    @Ignore("Cause needs valid user and password")    
    public void testAssaysWithUserWorks() {
        List<ApiResponseDatum> assays = client.listAssays();
        assertTrue(assays.size() > 0);
        
        int pub = assays.size();
        client.login(userName, password);
        
        assays = client.listAssays();
        assertTrue(assays.size() > 0);
        assertTrue(assays.size() > pub);
    }     
    
    
    @Test
    public void testGetStudyWorks() {
        Study study = client.getStudy(publicStudyId);
        assertNotNull(study);
        assertNotNull(study.getData().getAttributes().getTitle());
        assertEquals("Arabidopsis clock model P2011.4.1 - PLM_1042", study.getData().getAttributes().getTitle());
    }    
}
