package ed.synthsys.seek.dom.assay;

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
import ed.synthsys.seek.dom.common.DatumWrapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class Assay implements Serializable {

    private static final long serialVersionUID = 5365962674510447830L;

    @JsonProperty("data")
    private AssayData data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public AssayData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(AssayData data) {
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
        if ((other instanceof Assay) == false) {
            return false;
        }
        Assay rhs = ((Assay) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(data, rhs.data).isEquals();
    }

    public void setStudyId(String studyId) {
        AssayData data = this.getData();
        AssayRelationships rels = data.getRelationships();
        DatumWrapper study = rels.getStudy();
        
        ed.synthsys.seek.dom.common.Datum studyDatum = study.getData();
        String seekInvestigationId = studyDatum.getId();

        if(studyId.equals(seekInvestigationId)) {
            return;
        }else {
            studyDatum.setId(studyId);
            studyDatum.setType("studies");
            
            study.setData(studyDatum);
            rels.setStudy(study);
            data.setRelationships(rels);
            this.setData(data);
        }
    }
}
