package ed.synthsys.seek.dom.assay;

import ed.synthsys.seek.dom.common.Organisms;
import ed.synthsys.seek.dom.common.Publications;
import ed.synthsys.seek.dom.common.Creators;
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
import ed.synthsys.seek.dom.common.DatumWrapper;
import ed.synthsys.seek.dom.common.Relationships;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "study",
    "publications",
    "organisms",
    "sops",
    "models",
    "data_files",
    "documents",
    "creators"
})
public class AssayRelationships implements Serializable, Relationships {

    @JsonProperty("study")
    private DatumWrapper study;
    @JsonProperty("publications")
    private Publications publications;
    @JsonProperty("organisms")
    private Organisms organisms;
    @JsonProperty("sops")
    private Sops sops;
    @JsonProperty("models")
    private Models models;
    @JsonProperty("data_files")
    private DataFiles dataFiles;
    @JsonProperty("documents")
    private Documents documents;
    @JsonProperty("creators")
    private Creators creators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("study")
    public DatumWrapper getStudy() {
        return study;
    }

    @JsonProperty("study")
    public void setStudy(DatumWrapper study) {
        this.study = study;
    }

    @JsonProperty("publications")
    public Publications getPublications() {
        return publications;
    }

    @JsonProperty("publications")
    public void setPublications(Publications publications) {
        this.publications = publications;
    }

    @JsonProperty("organisms")
    public Organisms getOrganisms() {
        return organisms;
    }

    @JsonProperty("organisms")
    public void setOrganisms(Organisms organisms) {
        this.organisms = organisms;
    }

    @JsonProperty("sops")
    public Sops getSops() {
        return sops;
    }

    @JsonProperty("sops")
    public void setSops(Sops sops) {
        this.sops = sops;
    }

    @JsonProperty("models")
    public Models getModels() {
        return models;
    }

    @JsonProperty("models")
    public void setModels(Models models) {
        this.models = models;
    }

    @JsonProperty("data_files")
    public DataFiles getDataFiles() {
        return dataFiles;
    }

    @JsonProperty("data_files")
    public void setDataFiles(DataFiles dataFiles) {
        this.dataFiles = dataFiles;
    }

    @JsonProperty("documents")
    public Documents getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(Documents documents) {
        this.documents = documents;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("study", study).append("publications", publications).append("organisms", organisms).append("sops", sops).append("models", models).append("dataFiles", dataFiles).append("documents", documents).append("creators", creators).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(creators).append(models).append(organisms).append(publications).append(documents).append(additionalProperties).append(study).append(dataFiles).append(sops).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AssayRelationships) == false) {
            return false;
        }
        AssayRelationships rhs = ((AssayRelationships) other);
        return new EqualsBuilder().append(creators, rhs.creators).append(models, rhs.models).append(organisms, rhs.organisms).append(publications, rhs.publications).append(documents, rhs.documents).append(additionalProperties, rhs.additionalProperties).append(study, rhs.study).append(dataFiles, rhs.dataFiles).append(sops, rhs.sops).isEquals();
    }

}
