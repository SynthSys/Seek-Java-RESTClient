/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.synthsys.seek.migration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import ed.synthsys.seek.dom.common.ApiResponseDatum;
import ed.synthsys.seek.dom.common.SeekRestApiCollectionResponse;
import ed.synthsys.seek.dom.assay.Assay;
import ed.synthsys.seek.dom.datafile.DataFile;
import ed.synthsys.seek.dom.investigation.Investigation;
import ed.synthsys.seek.dom.modelfile.ModelFile;
import ed.synthsys.seek.dom.person.Person;
import ed.synthsys.seek.dom.project.Project;
import ed.synthsys.seek.dom.study.Study;

/**
 *
 * @author Johnny Hay
 */
public class SeekRestApiClient {
    
    public SeekRestApiClient() {
        new SeekRestApiClient("https://fairdomhub.org/");
    }

    public SeekRestApiClient(String baseSeekURI) {
        SeekRestApiClient.BASE_REST_URI = baseSeekURI;
        SeekRestApiClient.PEOPLE_REST_URI = baseSeekURI + "people";
        SeekRestApiClient.ASSAYS_REST_URI = baseSeekURI + "assays";
        SeekRestApiClient.PROJECTS_REST_URI = baseSeekURI + "projects";
        SeekRestApiClient.INVESTIGATIONS_REST_URI = baseSeekURI + "investigations";
        SeekRestApiClient.STUDIES_REST_URI = baseSeekURI + "studies";
        SeekRestApiClient.DATAFILES_REST_URI = baseSeekURI + "data_files";
        SeekRestApiClient.MODEL_FILES_REST_URI = baseSeekURI + "models";
    }
    
    private static final String SEEK_USERNAME = "";
    private static final String SEEK_PASSWORD = "";
    
    public static String BASE_REST_URI;
    public static String PEOPLE_REST_URI;
    public static String ASSAYS_REST_URI;
    public static String PROJECTS_REST_URI;
    public static String INVESTIGATIONS_REST_URI;
    public static String STUDIES_REST_URI;
    public static String DATAFILES_REST_URI;
    public static String MODEL_FILES_REST_URI;
  
    private static ClientConfig clientConfig;
    private static final Client CLIENT;
    private static final long REQUEST_DELAY = 200;

    private static final RetryPolicy RETRY_POLICY = new RetryPolicy()
      .retryIf((Response response) -> response.getStatus() != 200)
      .withMaxRetries(3).withBackoff(1, 8, TimeUnit.SECONDS)
      .withMaxDuration(60, TimeUnit.SECONDS);
    
    private static class ShutDownHook extends Thread {  
        public void run() {
            System.out.println("Shutdown Hook is running !"); 
            CLIENT.close();
        }  
    }
    
    static {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
//            .nonPreemptive()
            .credentials(SEEK_USERNAME, SEEK_PASSWORD)
            .build();
        
        clientConfig = new ClientConfig();
        clientConfig.register(feature) ;
        // configure client logging
        clientConfig.register(new LoggingFeature(Logger.getLogger(
                LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, 
                LoggingFeature.Verbosity.PAYLOAD_ANY, 4096));
        
        clientConfig.property(ClientProperties.USE_ENCODING, "UTF-8");
        
        CLIENT = ClientBuilder.newClient(clientConfig);
        CLIENT.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

        ShutDownHook jvmShutdownHook = new ShutDownHook(); 
        Runtime.getRuntime().addShutdownHook(jvmShutdownHook);
    }
    
    private static final String USERNAME_PASSWORD = SEEK_USERNAME + ":" + SEEK_PASSWORD;
    private static final String AUTH_HEADER_VALUE = "Basic " + java.util.Base64.getEncoder().encodeToString( USERNAME_PASSWORD.getBytes() );
    
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
 
    public Response createPerson(Person person) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> System.out.println(String.format("Connection attempt failed %s", failure)))     
            .get(() -> CLIENT
                .target(PEOPLE_REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", AUTH_HEADER_VALUE)
                .method("PATCH", Entity.entity(person, MediaType.APPLICATION_JSON)));
//            .post(Entity.entity(person, MediaType.APPLICATION_JSON));
    }

    public Person getPerson(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(PEOPLE_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(Person.class);
    }
    
    public Response updatePerson(String id, Person person) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(PEOPLE_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(person, MediaType.APPLICATION_JSON));
    }
    
    public Response listPeople() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        Response response = CLIENT
            .target(PEOPLE_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(Response.class);
        
//        List<ApiResponseDatum> people = response.getData();
        return response;
    }

    public Response createProject(Project project) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> System.out.println(String.format("Connection attempt failed %s", failure)))     
            .get(() -> CLIENT
            .target(PROJECTS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(project, MediaType.APPLICATION_JSON)));
//            .post(Entity.entity(project, MediaType.APPLICATION_JSON), Project.class);
    }

    public Project getProject(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(PROJECTS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Project.class);
    }
    
    public List<ApiResponseDatum> listProjects() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeekRestApiCollectionResponse response = CLIENT
            .target(PROJECTS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> projects = response.getData();
        return projects;
    }
    
    private void handleFailedAttempt(int num) {
        num++;
    }

    /*public Response createInvestigation(Investigation investigation) {
        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> System.out.println(String.format("Connection attempt failed %s", failure)))     
            .get(() -> CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(investigation, MediaType.APPLICATION_JSON)));
        
        return response;
    }*/
    
    public Pair<Integer, Response> createInvestigation(Investigation investigation) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);

        Response response = Failsafe.with(RETRY_POLICY)
            .onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num))     
            .get(() -> CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(investigation, MediaType.APPLICATION_JSON)));
        
        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }

    public Investigation getInvestigation(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Investigation.class);
    }

    public List<ApiResponseDatum> listInvestigations() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeekRestApiCollectionResponse response = CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> investigations = response.getData();
        return investigations;
    }

    public Response updateInvestigation(String id, Investigation investigation) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(investigation, MediaType.APPLICATION_JSON));
    }

    public Pair<Integer, Response> createStudy(Study study) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);
        
        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(STUDIES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(study, MediaType.APPLICATION_JSON)));
        
        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }

    public Study getStudy(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(STUDIES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Study.class);
    }

    public List<ApiResponseDatum> listStudies() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeekRestApiCollectionResponse response = CLIENT
            .target(STUDIES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> studies = response.getData();
        return studies;
    }

    public Response updateStudy(String id, Study study) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(STUDIES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(study, MediaType.APPLICATION_JSON));
    }

    public Pair<Integer, Response> createAssay(Assay assay) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);
        
        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(ASSAYS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(assay, MediaType.APPLICATION_JSON)));
    
        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }

    public Assay getAssay(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(ASSAYS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Assay.class);
    }

    public List<ApiResponseDatum> listAssays() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeekRestApiCollectionResponse response = CLIENT
            .target(ASSAYS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> assays = response.getData();
        return assays;
    }

    public Response updateAssay(String id, Assay assay) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(ASSAYS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(assay, MediaType.APPLICATION_JSON));
    }

    public Pair<Integer, Response> createDataFile(DataFile dataFile) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);
        
        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(DATAFILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(dataFile, MediaType.APPLICATION_JSON)));

        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }

    public DataFile getDataFile(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(DATAFILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(DataFile.class);
    }

    public List<ApiResponseDatum> listDataFiles() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        SeekRestApiCollectionResponse response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> System.out.println(String.format("Connection attempt failed %s", failure)))     
            .get(() -> CLIENT
            .target(DATAFILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class));
        
        List<ApiResponseDatum> assays = response.getData();
        return assays;
    }

    public Response updateDataFile(String id, DataFile dataFile) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(DATAFILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(dataFile, MediaType.APPLICATION_JSON));
    }

    public Pair<Integer, Response> createModelFile(ModelFile modelFile) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);
        
        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(MODEL_FILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .post(Entity.entity(modelFile, MediaType.APPLICATION_JSON)));

        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }

    public ModelFile getModelFile(int id) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(ModelFile.class);
    }

    public Pair<Integer, List<ApiResponseDatum>> listModelFiles() {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);
        
        SeekRestApiCollectionResponse response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(MODEL_FILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .get(SeekRestApiCollectionResponse.class));
        
        List<ApiResponseDatum> assays = response.getData();
        Pair<Integer, List<ApiResponseDatum>> resultPair = new Pair(num.intValue(), assays);
        return resultPair;
    }

    public Response updateModelFile(String id, ModelFile modelFile) {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(modelFile, MediaType.APPLICATION_JSON));
    }

    public Pair<Integer,Response> uploadDataFileContent(File dataFileContent, String path) 
        throws Exception {
        try {
            Thread.sleep(REQUEST_DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeekRestApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        int num = 1;
        AtomicInteger num = new AtomicInteger(1);

        Response response = Failsafe.with(RETRY_POLICY).onSuccess(cxn -> System.out.println(String.format("SUCCESS!!!! %s", cxn)))
            .onFailure(failure -> System.out.println(String.format("Failed to create connection %s", failure)))
            .onFailedAttempt((result, failure, context) -> num.incrementAndGet())     
            //.onFailedAttempt((result, failure, context) -> handleFailedAttempt(num)) 
            .get(() -> CLIENT
            .target(BASE_REST_URI).path(path)
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", AUTH_HEADER_VALUE)
            .put(Entity.entity(Files.readAllBytes(dataFileContent.toPath()),
                    MediaType.APPLICATION_OCTET_STREAM)));
        
        Pair<Integer, Response> resultPair = new Pair(num.intValue(), response);
        return resultPair;
    }
}
