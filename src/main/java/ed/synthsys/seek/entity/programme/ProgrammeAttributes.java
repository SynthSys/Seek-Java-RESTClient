package ed.synthsys.seek.entity.programme;

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
    "title",
    "description",
    "web_page",
    "funding_details",
    "funding_codes"
})
public class ProgrammeAttributes {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("web_page")
    private String webPage;
    @JsonProperty("funding_details")
    private String fundingDetails;
    @JsonProperty("funding_codes")
    private List<String> fundingCodes = null;
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

    @JsonProperty("web_page")
    public String getWebPage() {
        return webPage;
    }

    @JsonProperty("web_page")
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    @JsonProperty("funding_details")
    public String getFundingDetails() {
        return fundingDetails;
    }

    @JsonProperty("funding_details")
    public void setFundingDetails(String fundingDetails) {
        this.fundingDetails = fundingDetails;
    }

    @JsonProperty("funding_codes")
    public List<String> getFundingCodes() {
        return fundingCodes;
    }

    @JsonProperty("funding_codes")
    public void setFundingCodes(List<String> fundingCodes) {
        this.fundingCodes = fundingCodes;
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
        return new ToStringBuilder(this).append("title", title).append("description", description).append("webPage", webPage).append("fundingDetails", fundingDetails).append("fundingCodes", fundingCodes).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fundingCodes).append(title).append(webPage).append(additionalProperties).append(description).append(fundingDetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProgrammeAttributes) == false) {
            return false;
        }
        ProgrammeAttributes rhs = ((ProgrammeAttributes) other);
        return new EqualsBuilder().append(fundingCodes, rhs.fundingCodes).append(title, rhs.title).append(webPage, rhs.webPage).append(additionalProperties, rhs.additionalProperties).append(description, rhs.description).append(fundingDetails, rhs.fundingDetails).isEquals();
    }

}
