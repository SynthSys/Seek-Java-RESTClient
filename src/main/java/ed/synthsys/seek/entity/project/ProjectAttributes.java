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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "avatar",
    "title",
    "description",
    "web_page",
    "wiki_page",
    "default_license",
    "default_policy"
})
public class ProjectAttributes {

    @JsonProperty("avatar")
    private Object avatar;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("web_page")
    private String webPage;
    @JsonProperty("wiki_page")
    private String wikiPage;
    @JsonProperty("default_license")
    private String defaultLicense;
    @JsonProperty("default_policy")
    private DefaultPolicy defaultPolicy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("avatar")
    public Object getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

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

    @JsonProperty("web_page")
    public String getWebPage() {
        return webPage;
    }

    @JsonProperty("web_page")
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    @JsonProperty("wiki_page")
    public String getWikiPage() {
        return wikiPage;
    }

    @JsonProperty("wiki_page")
    public void setWikiPage(String wikiPage) {
        this.wikiPage = wikiPage;
    }

    @JsonProperty("default_license")
    public String getDefaultLicense() {
        return defaultLicense;
    }

    @JsonProperty("default_license")
    public void setDefaultLicense(String defaultLicense) {
        this.defaultLicense = defaultLicense;
    }

    @JsonProperty("default_policy")
    public DefaultPolicy getDefaultPolicy() {
        return defaultPolicy;
    }

    @JsonProperty("default_policy")
    public void setDefaultPolicy(DefaultPolicy defaultPolicy) {
        this.defaultPolicy = defaultPolicy;
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
        return new ToStringBuilder(this).append("avatar", avatar).append("title", title).append("description", description).append("webPage", webPage).append("wikiPage", wikiPage).append("defaultLicense", defaultLicense).append("defaultPolicy", defaultPolicy).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(webPage).append(additionalProperties).append(description).append(defaultLicense).append(defaultPolicy).append(avatar).append(wikiPage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProjectAttributes) == false) {
            return false;
        }
        ProjectAttributes rhs = ((ProjectAttributes) other);
        return new EqualsBuilder().append(title, rhs.title).append(webPage, rhs.webPage).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(defaultLicense, rhs.defaultLicense).append(defaultPolicy, rhs.defaultPolicy).append(avatar, rhs.avatar).append(wikiPage, rhs.wikiPage).isEquals();
    }

}
