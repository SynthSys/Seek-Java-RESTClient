package ed.synthsys.seek.entity.investigation;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ed.synthsys.seek.entity.common.Creators;
import ed.synthsys.seek.entity.common.DatumListWrapper;
import ed.synthsys.seek.entity.common.Projects;
import ed.synthsys.seek.entity.common.Publications;
import ed.synthsys.seek.entity.common.Relationships;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "projects",
    "publications",
    "creators"
})
public class InvestigationRelationships implements Serializable, Relationships {

    @JsonProperty("projects")
    private Projects projects;
    @JsonProperty("publications")
    private Publications publications;
    @JsonProperty("creators")
    private Creators creators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    // Attributes used for Permissions setter
    @JsonProperty("studies")
    private DatumListWrapper studies;
    @JsonProperty("assays")
    private DatumListWrapper assays;
    @JsonProperty("data_files")
    private DatumListWrapper dataFiles;
    @JsonProperty("models")
    private DatumListWrapper models;

    @JsonProperty("projects")
    public Projects getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @JsonProperty("publications")
    public Publications getPublications() {
        return publications;
    }

    @JsonProperty("publications")
    public void setPublications(Publications publications) {
        this.publications = publications;
    }

    @JsonProperty("creators")
    public Creators getCreators() {
        return creators;
    }

    @JsonProperty("creators")
    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("studies")
    public DatumListWrapper getStudies() {
        return studies;
    }

    @JsonProperty("studies")
    public void setStudies(DatumListWrapper studies) {
        this.studies = studies;
    }

    @JsonProperty("assays")
    public DatumListWrapper getAssays() {
        return assays;
    }

    @JsonProperty("assays")
    public void setAssays(DatumListWrapper assays) {
        this.assays = assays;
    }

    @JsonProperty("data_files")
    public DatumListWrapper getDataFiles() {
        return dataFiles;
    }

    @JsonProperty("data_files")
    public void setDataFiles(DatumListWrapper dataFiles) {
        this.dataFiles = dataFiles;
    }

    @JsonProperty("models")
    public DatumListWrapper getModels() {
        return models;
    }

    @JsonProperty("models")
    public void setModels(DatumListWrapper models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("projects", projects).append("publications", publications).append("creators", creators).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(creators).append(projects).append(publications).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvestigationRelationships) == false) {
            return false;
        }
        InvestigationRelationships rhs = ((InvestigationRelationships) other);
        return new EqualsBuilder().append(creators, rhs.creators).append(projects, rhs.projects).append(publications, rhs.publications).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
