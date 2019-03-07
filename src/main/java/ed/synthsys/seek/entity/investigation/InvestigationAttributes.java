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
import ed.synthsys.seek.entity.common.Policy;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "policy",
    "description",
    "other_creators"
})
public class InvestigationAttributes implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("policy")
    private Policy policy;
    @JsonProperty("description")
    private String description;
    @JsonProperty("other_creators")
    private String otherCreators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("policy")
    public Policy getPolicy() {
        return policy;
    }

    @JsonProperty("policy")
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("other_creators")
    public String getOtherCreators() {
        return otherCreators;
    }

    @JsonProperty("other_creators")
    public void setOtherCreators(String otherCreators) {
        this.otherCreators = otherCreators;
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
        return new ToStringBuilder(this).append("title", title).append("policy", policy).append("description", description).append("otherCreators", otherCreators).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(otherCreators).append(title).append(additionalProperties).append(description).append(policy).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InvestigationAttributes) == false) {
            return false;
        }
        InvestigationAttributes rhs = ((InvestigationAttributes) other);
        return new EqualsBuilder().append(otherCreators, rhs.otherCreators).append(title, rhs.title).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(policy, rhs.policy).isEquals();
    }

}
