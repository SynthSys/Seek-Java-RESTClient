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
import ed.synthsys.seek.dom.common.Datum;
import ed.synthsys.seek.dom.common.DatumWrapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class Study implements Serializable {

    private static final long serialVersionUID = -6189100320646762683L;

    @JsonProperty("data")
    private StudyData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public StudyData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(StudyData data) {
        this.data = data;
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
        return new ToStringBuilder(this).append("data", data).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Study) == false) {
            return false;
        }
        Study rhs = ((Study) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).isEquals();
    }

    public void setInvestigationId(String investigationId) {
        StudyData data = this.getData();
        StudyRelationships rels = data.getRelationships();
        DatumWrapper investigation = rels.getInvestigation();
        
        Datum investigationDatum = investigation.getData();
        String seekInvestigationId = investigationDatum.getId();

        if(investigationId.equals(seekInvestigationId)) {
            return;
        } else {
            investigationDatum.setId(investigationId);
            investigationDatum.setType("investigations");
            
            investigation.setData(investigationDatum);
            rels.setInvestigation(investigation);
            data.setRelationships(rels);
            this.setData(data);
        }
    }
}
