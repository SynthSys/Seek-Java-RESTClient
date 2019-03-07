package ed.synthsys.seek.dom.investigation;

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
import ed.synthsys.seek.dom.common.Datum;
import ed.synthsys.seek.dom.common.Projects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class Investigation implements Serializable {

    private static final long serialVersionUID = 502709409490432258L;

    @JsonProperty("data")
    private InvestigationData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public InvestigationData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(InvestigationData data) {
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
        if ((other instanceof Investigation) == false) {
            return false;
        }
        Investigation rhs = ((Investigation) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).isEquals();
    }

    public void setProjectId(String projectId) {
        InvestigationData data = this.getData();
        InvestigationRelationships rels = data.getRelationships();
        Projects projects = rels.getProjects();
        boolean hasProjectRel = false;
        
        for(Datum datum: projects.getData()) {
            if(datum.getId().equals(projectId)) {
                hasProjectRel = true;
            }
        }
        
        if(hasProjectRel == false) {
            Datum datum = new Datum();
            datum.setId(projectId);
            datum.setType("projects");
            
            projects.getData().add(datum);
            rels.setProjects(projects);
            data.setRelationships(rels);
            this.setData(data);
        }
    }
}
