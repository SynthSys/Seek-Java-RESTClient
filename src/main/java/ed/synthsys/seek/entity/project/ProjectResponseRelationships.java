package ed.synthsys.seek.entity.project;

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
import ed.synthsys.seek.entity.common.Organisms;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "programmes",
    "organisms"
})
public class ProjectResponseRelationships {

    @JsonProperty("programmes")
    private Programmes programmes;
    @JsonProperty("organisms")
    private Organisms organisms;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("programmes")
    public Programmes getProgrammes() {
        return programmes;
    }

    @JsonProperty("programmes")
    public void setProgrammes(Programmes programmes) {
        this.programmes = programmes;
    }

    @JsonProperty("organisms")
    public Organisms getOrganisms() {
        return organisms;
    }

    @JsonProperty("organisms")
    public void setOrganisms(Organisms organisms) {
        this.organisms = organisms;
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
        return new ToStringBuilder(this).append("programmes", programmes).append("organisms", organisms).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(organisms).append(additionalProperties).append(programmes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProjectResponseRelationships) == false) {
            return false;
        }
        ProjectResponseRelationships rhs = ((ProjectResponseRelationships) other);
        return new EqualsBuilder().append(organisms, rhs.organisms).append(additionalProperties, rhs.additionalProperties).append(programmes, rhs.programmes).isEquals();
    }

}
