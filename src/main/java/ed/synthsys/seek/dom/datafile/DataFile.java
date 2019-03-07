package ed.synthsys.seek.dom.datafile;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ed.synthsys.seek.dom.common.Assays;
import ed.synthsys.seek.dom.common.Datum;
import ed.synthsys.seek.dom.common.Projects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class DataFile implements Serializable {

    private static final long serialVersionUID = -3364542937097293946L;

    @JsonProperty("data")
    private DataFileData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    private String filepath = "";

    @JsonProperty("data")
    public DataFileData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(DataFileData data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("data", data).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataFile) == false) {
            return false;
        }
        DataFile rhs = ((DataFile) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).isEquals();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setAssayId(String assayId) {
        DataFileData data = this.getData();
        DataFileRelationships rels = data.getRelationships();
        Assays assays = rels.getAssays();
        
        List<Datum> assayData = assays.getData();
        boolean hasAssayId = false;
        
        int indexToRemove = -1;
        
        for(Datum assayDatum: assayData) {
            String seekAssayId = assayDatum.getId();

            if(assayId.equals(seekAssayId)) {
                return;
            }
        }
        
        if(hasAssayId == false) {
            Datum assayDatum = new Datum();
            assayDatum.setId(assayId);
            assayDatum.setType("assays");
            
            assayData.add(assayDatum);
            assays.setData(assayData);
            assayData.remove(indexToRemove);
            rels.setAssays(assays);
            data.setRelationships(rels);
            this.setData(data);
        }
    }

    public void setProjectId(String projectId) {
        DataFileData data = this.getData();
        DataFileRelationships rels = data.getRelationships();
        Projects projects = rels.getProjects();
        
        List<Datum> projectData = projects.getData();
        boolean hasProjectRel = false;
        
        for(Datum projectDatum: projectData) {
            String seekProjectId = projectDatum.getId();

            if(projectId.equals(seekProjectId)) {
                return;
            }
        }
        
        if(hasProjectRel == false) {
            Datum projectDatum = new Datum();
            projectDatum.setId(projectId);
            projectDatum.setType("projects");
            
            projectData.add(projectDatum);
            projects.setData(projectData);
            rels.setProjects(projects);
            data.setRelationships(rels);
            this.setData(data);
        }
    }
}
