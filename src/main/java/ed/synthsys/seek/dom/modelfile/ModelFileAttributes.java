package ed.synthsys.seek.dom.modelfile;

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
import ed.synthsys.seek.dom.common.ContentBlob;
import ed.synthsys.seek.dom.common.Policy;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "description",
    "tags",
    "license",
    "other_creators",
    "content_blobs",
    "policy",
    "model_type",
    "model_format",
    "environment"
})
public class ModelFileAttributes implements Serializable {

    private static final long serialVersionUID = -5996178474689909269L;

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("license")
    private String license;
    @JsonProperty("other_creators")
    private String otherCreators;
    @JsonProperty("content_blobs")
    private List<ContentBlob> contentBlobs = null;
    @JsonProperty("policy")
    private Policy policy;
    @JsonProperty("model_type")
    private String modelType;
    @JsonProperty("model_format")
    private String modelFormat;
    @JsonProperty("environment")
    private String environment;
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

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("other_creators")
    public String getOtherCreators() {
        return otherCreators;
    }

    @JsonProperty("other_creators")
    public void setOtherCreators(String otherCreators) {
        this.otherCreators = otherCreators;
    }

    @JsonProperty("content_blobs")
    public List<ContentBlob> getContentBlobs() {
        return contentBlobs;
    }

    @JsonProperty("content_blobs")
    public void setContentBlobs(List<ContentBlob> contentBlobs) {
        this.contentBlobs = contentBlobs;
    }

    @JsonProperty("policy")
    public Policy getPolicy() {
        return policy;
    }

    @JsonProperty("policy")
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @JsonProperty("model_type")
    public String getModelType() {
        return modelType;
    }

    @JsonProperty("model_type")
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    @JsonProperty("model_format")
    public String getModelFormat() {
        return modelFormat;
    }

    @JsonProperty("model_format")
    public void setModelFormat(String modelFormat) {
        this.modelFormat = modelFormat;
    }

    @JsonProperty("environment")
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty("environment")
    public void setEnvironment(String environment) {
        this.environment = environment;
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
        return new ToStringBuilder(this).append("title", title).append("description", description).append("tags", tags).append("license", license).append("otherCreators", otherCreators).append("contentBlobs", contentBlobs).append("policy", policy).append("modelType", modelType).append("modelFormat", modelFormat).append("environment", environment).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tags).append(otherCreators).append(title).append(environment).append(modelType).append(additionalProperties).append(contentBlobs).append(description).append(policy).append(modelFormat).append(license).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ModelFileAttributes) == false) {
            return false;
        }
        ModelFileAttributes rhs = ((ModelFileAttributes) other);
        return new EqualsBuilder().append(tags, rhs.tags).append(otherCreators, rhs.otherCreators).append(title, rhs.title).append(environment, rhs.environment).append(modelType, rhs.modelType).append(additionalProperties, rhs.additionalProperties).append(contentBlobs, rhs.contentBlobs).append(description, rhs.description).append(policy, rhs.policy).append(modelFormat, rhs.modelFormat).append(license, rhs.license).isEquals();
    }

}
