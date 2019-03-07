package ed.synthsys.seek.dom.study;

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
import ed.synthsys.seek.dom.common.Policy;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "description",
    "experimentalists",
    "person_responsible_id",
    "other_creators",
    "policy"
})
public class StudyAttributes implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("experimentalists")
    private String experimentalists;
    @JsonProperty("person_responsible_id")
    private String personResponsibleId;
    @JsonProperty("other_creators")
    private String otherCreators;
    @JsonProperty("policy")
    private Policy policy;
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("experimentalists")
    public String getExperimentalists() {
        return experimentalists;
    }

    @JsonProperty("experimentalists")
    public void setExperimentalists(String experimentalists) {
        this.experimentalists = experimentalists;
    }

    @JsonProperty("person_responsible_id")
    public String getPersonResponsibleId() {
        return personResponsibleId;
    }

    @JsonProperty("person_responsible_id")
    public void setPersonResponsibleId(String personResponsibleId) {
        this.personResponsibleId = personResponsibleId;
    }

    @JsonProperty("other_creators")
    public String getOtherCreators() {
        return otherCreators;
    }

    @JsonProperty("other_creators")
    public void setOtherCreators(String otherCreators) {
        this.otherCreators = otherCreators;
    }

    @JsonProperty("policy")
    public Policy getPolicy() {
        return policy;
    }

    @JsonProperty("policy")
    public void setPolicy(Policy policy) {
        this.policy = policy;
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
        return new ToStringBuilder(this).append("title", title).append("description", description).append("experimentalists", experimentalists).append("personResponsibleId", personResponsibleId).append("otherCreators", otherCreators).append("policy", policy).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(otherCreators).append(title).append(additionalProperties).append(personResponsibleId).append(description).append(policy).append(experimentalists).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StudyAttributes) == false) {
            return false;
        }
        StudyAttributes rhs = ((StudyAttributes) other);
        return new EqualsBuilder().append(otherCreators, rhs.otherCreators).append(title, rhs.title).append(additionalProperties, rhs.additionalProperties).append(personResponsibleId, rhs.personResponsibleId).append(description, rhs.description).append(policy, rhs.policy).append(experimentalists, rhs.experimentalists).isEquals();
    }

}
