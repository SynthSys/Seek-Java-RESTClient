package ed.synthsys.seek.dom.person;

import java.util.HashMap;
import java.util.List;
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
    "first_name",
    "last_name",
    "email",
    "description",
    "web_page",
    "orcid",
    "phone",
    "skype_name",
    "expertise",
    "tools",
    "project_positions"
})
public class PersonAttributes {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("description")
    private String description;
    @JsonProperty("web_page")
    private String webPage;
    @JsonProperty("orcid")
    private String orcid;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("skype_name")
    private String skypeName;
    @JsonProperty("expertise")
    private List<String> expertise = null;
    @JsonProperty("tools")
    private List<String> tools = null;
    @JsonProperty("project_positions")
    private List<ProjectPosition> projectPositions = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
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

    @JsonProperty("orcid")
    public String getOrcid() {
        return orcid;
    }

    @JsonProperty("orcid")
    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("skype_name")
    public String getSkypeName() {
        return skypeName;
    }

    @JsonProperty("skype_name")
    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    @JsonProperty("expertise")
    public List<String> getExpertise() {
        return expertise;
    }

    @JsonProperty("expertise")
    public void setExpertise(List<String> expertise) {
        this.expertise = expertise;
    }

    @JsonProperty("tools")
    public List<String> getTools() {
        return tools;
    }

    @JsonProperty("tools")
    public void setTools(List<String> tools) {
        this.tools = tools;
    }

        @JsonProperty("project_positions")
    public List<ProjectPosition> getProjectPositions() {
        return projectPositions;
    }

    @JsonProperty("project_positions")
    public void setProjectPositions(List<ProjectPosition> projectPositions) {
        this.projectPositions = projectPositions;
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
        return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName).append("email", email).append("description", description).append("webPage", webPage).append("orcid", orcid).append("phone", phone).append("skypeName", skypeName).append("expertise", expertise).append("tools", tools).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lastName).append(tools).append(expertise).append(phone).append(orcid).append(webPage).append(email).append(additionalProperties).append(description).append(firstName).append(skypeName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PersonAttributes) == false) {
            return false;
        }
        PersonAttributes rhs = ((PersonAttributes) other);
        return new EqualsBuilder().append(lastName, rhs.lastName).append(tools, rhs.tools).append(expertise, rhs.expertise).append(phone, rhs.phone).append(orcid, rhs.orcid).append(webPage, rhs.webPage).append(email, rhs.email).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(firstName, rhs.firstName).append(skypeName, rhs.skypeName).isEquals();
    }

}
