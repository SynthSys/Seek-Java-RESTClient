package ed.synthsys.seek.dom.project;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "attributes",
    "relationships"
})
public class ProjectData {

    @JsonProperty("type")
    private String type;
    @JsonProperty("attributes")
    private ProjectAttributes attributes;
    @JsonProperty("relationships")
    private ProjectResponseRelationships relationships;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("attributes")
    public ProjectAttributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(ProjectAttributes attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("relationships")
    public ProjectResponseRelationships getRelationships() {
        return relationships;
    }

    @JsonProperty("relationships")
    public void setRelationships(ProjectResponseRelationships relationships) {
        this.relationships = relationships;
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
        return new ToStringBuilder(this).append("type", type).append("attributes", attributes).append("relationships", relationships).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(attributes).append(type).append(relationships).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProjectData) == false) {
            return false;
        }
        ProjectData rhs = ((ProjectData) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(attributes, rhs.attributes).append(type, rhs.type).append(relationships, rhs.relationships).isEquals();
    }

}
