/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.synthsys.seek.client;

import ed.synthsys.seek.dom.assay.Assay;
import ed.synthsys.seek.dom.common.ApiResponseDatum;
import ed.synthsys.seek.dom.investigation.Investigation;
import ed.synthsys.seek.dom.study.Study;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tzielins
 */
public class SeekRestApiClientTest {
    
    public SeekRestApiClientTest() {
    }
    
    static String seekURI = "https://fairdomhub.org/";
    static int publicInvestigationId = 251;
    static int publicAssayId = 840;
    static int publicStudyId = 485;
    
    static int privateAssayId = 797;
    
    
    SeekRestApiClient client;
    
    @Before
    public void setUp() {
        
        client = new SeekRestApiClient(seekURI);
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
    public void testGetPrivateAssayWorks() {
        Assay assay = client.getAssay(privateAssayId);
        assertNotNull(assay);
        assertNotNull(assay.getData().getAttributes().getTitle());
        assertEquals("CHecking if all works - PLM_1000, version 2", assay.getData().getAttributes().getTitle());
    }    
    
    @Test
    public void testAssaysWorks() {
        List<ApiResponseDatum> assays = client.listAssays();
        assertTrue(assays.size() > 0);
        
    }     
    
    @Test
    public void testGetStudyWorks() {
        Study study = client.getStudy(publicStudyId);
        assertNotNull(study);
        assertNotNull(study.getData().getAttributes().getTitle());
        assertEquals("Arabidopsis clock model P2011.4.1 - PLM_1042", study.getData().getAttributes().getTitle());
    }    
}
