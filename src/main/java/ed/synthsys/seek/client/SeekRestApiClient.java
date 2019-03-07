/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.synthsys.seek.client;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
public class SeekRestApiClient implements AutoCloseable {
    
    
    public SeekRestApiClient(String baseSeekURI) {
        this(baseSeekURI,null,null);
    }
    
    public SeekRestApiClient(String baseSeekURI, String userName, String password) {
        BASE_REST_URI = baseSeekURI;
        PEOPLE_REST_URI = baseSeekURI + "people";
        ASSAYS_REST_URI = baseSeekURI + "assays";
        PROJECTS_REST_URI = baseSeekURI + "projects";
        INVESTIGATIONS_REST_URI = baseSeekURI + "investigations";
        STUDIES_REST_URI = baseSeekURI + "studies";
        DATAFILES_REST_URI = baseSeekURI + "data_files";
        MODEL_FILES_REST_URI = baseSeekURI + "models";
        
        CLIENT = initClient(userName,password);
    } 
    
    
    boolean DEBUG = true;
    String BASE_REST_URI;
    String PEOPLE_REST_URI;
    String ASSAYS_REST_URI;
    String PROJECTS_REST_URI;
    String INVESTIGATIONS_REST_URI;
    String STUDIES_REST_URI;
    String DATAFILES_REST_URI;
    String MODEL_FILES_REST_URI;
  
    Client CLIENT;
    
    
    @Override
    public void close() {
        if (CLIENT != null) {
            CLIENT.close();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize(); 
    }
    
    private Client initClient(String userName, String password) {
        
        ClientConfig  clientConfig = new ClientConfig();
        
        if (userName != null) {
            HttpAuthenticationFeature auth = HttpAuthenticationFeature.basicBuilder()
            .credentials(userName, password)
            .build();

            clientConfig.register(auth) ;            
        }
        
        if (DEBUG) {
            // configure client logging
            clientConfig.register(new LoggingFeature(Logger.getLogger(
                LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, 
                LoggingFeature.Verbosity.PAYLOAD_ANY, 4096));
        }
        
        clientConfig.property(ClientProperties.USE_ENCODING, "UTF-8");
        
        Client client = ClientBuilder.newClient(clientConfig);
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        
        return client;
    }
    
    
    
    public void login(String userName, String password) {
        CLIENT.close();
        CLIENT = initClient(userName, password);
    }
    
    public Response createPerson(Person person) {
        return CLIENT
                .target(PEOPLE_REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .method("PATCH", Entity.entity(person, MediaType.APPLICATION_JSON));
    }

    public Person getPerson(int id) {
        return CLIENT
            .target(PEOPLE_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Person.class);
    }
    
    public Response updatePerson(String id, Person person) {
        return CLIENT
            .target(PEOPLE_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(person, MediaType.APPLICATION_JSON));
    }
    
    public Response listPeople() {
        Response response = CLIENT
            .target(PEOPLE_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(Response.class);
        
        return response;
    }

    public Response createProject(Project project) {
        return CLIENT
            .target(PROJECTS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(project, MediaType.APPLICATION_JSON));
    }

    public Project getProject(int id) {
        return CLIENT
            .target(PROJECTS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Project.class);
    }
    
    public List<ApiResponseDatum> listProjects() {
        SeekRestApiCollectionResponse response = CLIENT
            .target(PROJECTS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> projects = response.getData();
        return projects;
    }
    
    
    public Response createInvestigation(Investigation investigation) {
        return CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(investigation, MediaType.APPLICATION_JSON));
        
    }

    public Investigation getInvestigation(int id) {
        return CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Investigation.class);
    }

    public List<ApiResponseDatum> listInvestigations() {
        SeekRestApiCollectionResponse response = CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> investigations = response.getData();
        return investigations;
    }

    public Response updateInvestigation(String id, Investigation investigation) {
        return CLIENT
            .target(INVESTIGATIONS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(investigation, MediaType.APPLICATION_JSON));
    }

    public Response createStudy(Study study) {
        return CLIENT
            .target(STUDIES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(study, MediaType.APPLICATION_JSON));
    }

    public Study getStudy(int id) {
        return CLIENT
            .target(STUDIES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Study.class);
    }

    public List<ApiResponseDatum> listStudies() {
        SeekRestApiCollectionResponse response = CLIENT
            .target(STUDIES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> studies = response.getData();
        return studies;
    }

    public Response updateStudy(String id, Study study) {
        return CLIENT
            .target(STUDIES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(study, MediaType.APPLICATION_JSON));
    }

    public Response createAssay(Assay assay) {
        return CLIENT
            .target(ASSAYS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(assay, MediaType.APPLICATION_JSON));
    
    }

    public Assay getAssay(int id) {
        return CLIENT
            .target(ASSAYS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(Assay.class);
    }

    public List<ApiResponseDatum> listAssays() {
        SeekRestApiCollectionResponse response = CLIENT
            .target(ASSAYS_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class);
        
        List<ApiResponseDatum> assays = response.getData();
        return assays;
    }

    public Response updateAssay(String id, Assay assay) {
        return CLIENT
            .target(ASSAYS_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(assay, MediaType.APPLICATION_JSON));
    }

    public Response createDataFile(DataFile dataFile) {
        return CLIENT
            .target(DATAFILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(dataFile, MediaType.APPLICATION_JSON));

    }

    public DataFile getDataFile(int id) {
        return CLIENT
            .target(DATAFILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(DataFile.class);
    }

    public List<ApiResponseDatum> listDataFiles() {
        return CLIENT
            .target(DATAFILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class)
            .getData();
        
    }

    public Response updateDataFile(String id, DataFile dataFile) {
        return CLIENT
            .target(DATAFILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(dataFile, MediaType.APPLICATION_JSON));
    }

    public Response createModelFile(ModelFile modelFile) {
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(modelFile, MediaType.APPLICATION_JSON));
    }

    public ModelFile getModelFile(int id) {
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .get(ModelFile.class);
    }

    public List<ApiResponseDatum> listModelFiles() {
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .get(SeekRestApiCollectionResponse.class)
            .getData();
        
    }

    public Response updateModelFile(String id, ModelFile modelFile) {
        return CLIENT
            .target(MODEL_FILES_REST_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(modelFile, MediaType.APPLICATION_JSON));
    }

    public Response uploadDataFileContent(File dataFileContent, String path) throws Exception {
        return CLIENT
            .target(BASE_REST_URI).path(path)
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.entity(Files.readAllBytes(dataFileContent.toPath()),
                    MediaType.APPLICATION_OCTET_STREAM));
        
    }
}
