package ed.synthsys.seek.entity.programme;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ed.synthsys.seek.entity.common.Projects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "projects",
    "administrators"
})
public class Relationships {

    @JsonProperty("projects")
    private Projects projects;
    @JsonProperty("administrators")
    private Administrators administrators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("projects")
    public Projects getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @JsonProperty("administrators")
    public Administrators getAdministrators() {
        return administrators;
    }

    @JsonProperty("administrators")
    public void setAdministrators(Administrators administrators) {
        this.administrators = administrators;
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
        return new ToStringBuilder(this).append("projects", projects).append("administrators", administrators).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(projects).append(additionalProperties).append(administrators).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Relationships) == false) {
            return false;
        }
        Relationships rhs = ((Relationships) other);
        return new EqualsBuilder().append(projects, rhs.projects).append(additionalProperties, rhs.additionalProperties).append(administrators, rhs.administrators).isEquals();
    }

}
