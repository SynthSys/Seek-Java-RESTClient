package ed.synthsys.seek.entity.assay;

import java.util.HashMap;
import java.util.List;
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
    "assay_class",
    "assay_type",
    "technology_type",
    "tags",
    "other_creators",
    "description",
    "policy"
})
public class AssayAttributes implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("assay_class")
    private AssayClass assayClass;
    @JsonProperty("assay_type")
    private AssayType assayType;
    @JsonProperty("technology_type")
    private TechnologyType technologyType;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("other_creators")
    private String otherCreators;
    @JsonProperty("description")
    private String description;
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

    @JsonProperty("assay_class")
    public AssayClass getAssayClass() {
        return assayClass;
    }

    @JsonProperty("assay_class")
    public void setAssayClass(AssayClass assayClass) {
        this.assayClass = assayClass;
    }

    @JsonProperty("assay_type")
    public AssayType getAssayType() {
        return assayType;
    }

    @JsonProperty("assay_type")
    public void setAssayType(AssayType assayType) {
        this.assayType = assayType;
    }

    @JsonProperty("technology_type")
    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    @JsonProperty("technology_type")
    public void setTechnologyType(TechnologyType technologyType) {
        this.technologyType = technologyType;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("other_creators")
    public String getOtherCreators() {
        return otherCreators;
    }

    @JsonProperty("other_creators")
    public void setOtherCreators(String otherCreators) {
        this.otherCreators = otherCreators;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
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
        return new ToStringBuilder(this).append("title", title).append("assayClass", assayClass).append("assayType", assayType).append("technologyType", technologyType).append("tags", tags).append("otherCreators", otherCreators).append("description", description).append("policy", policy).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tags).append(otherCreators).append(assayClass).append(title).append(technologyType).append(assayType).append(additionalProperties).append(description).append(policy).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AssayAttributes) == false) {
            return false;
        }
        AssayAttributes rhs = ((AssayAttributes) other);
        return new EqualsBuilder().append(tags, rhs.tags).append(otherCreators, rhs.otherCreators).append(assayClass, rhs.assayClass).append(title, rhs.title).append(technologyType, rhs.technologyType).append(assayType, rhs.assayType).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(policy, rhs.policy).isEquals();
    }

}
