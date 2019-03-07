package ed.synthsys.seek.entity.common;

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
    "data",
    "jsonapi",
    "meta"
})
public class SeekRestApiReadResponse {

    @JsonProperty("data")
    private ApiResponseDatum data = null;
    @JsonProperty("jsonapi")
    private Jsonapi jsonapi;
    @JsonProperty("meta")
    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public ApiResponseDatum getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(ApiResponseDatum data) {
        this.data = data;
    }

    @JsonProperty("jsonapi")
    public Jsonapi getJsonapi() {
        return jsonapi;
    }

    @JsonProperty("jsonapi")
    public void setJsonapi(Jsonapi jsonapi) {
        this.jsonapi = jsonapi;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
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
        return new ToStringBuilder(this).append("data", data).append("jsonapi", jsonapi).append("meta", meta).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(jsonapi).append(additionalProperties).append(data).append(meta).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SeekRestApiReadResponse) == false) {
            return false;
        }
        SeekRestApiReadResponse rhs = ((SeekRestApiReadResponse) other);
        return new EqualsBuilder().append(jsonapi, rhs.jsonapi).append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).append(meta, rhs.meta).isEquals();
    }

}
