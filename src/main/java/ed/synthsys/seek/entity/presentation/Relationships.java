package ed.synthsys.seek.entity.presentation;

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
import ed.synthsys.seek.entity.common.Creators;
import ed.synthsys.seek.entity.common.Events;
import ed.synthsys.seek.entity.common.Projects;
import ed.synthsys.seek.entity.common.Publications;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "creators",
    "projects",
    "publications",
    "events"
})
public class Relationships {

    @JsonProperty("creators")
    private Creators creators;
    @JsonProperty("projects")
    private Projects projects;
    @JsonProperty("publications")
    private Publications publications;
    @JsonProperty("events")
    private Events events;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("creators")
    public Creators getCreators() {
        return creators;
    }

    @JsonProperty("creators")
    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    @JsonProperty("projects")
    public Projects getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    @JsonProperty("publications")
    public Publications getPublications() {
        return publications;
    }

    @JsonProperty("publications")
    public void setPublications(Publications publications) {
        this.publications = publications;
    }

    @JsonProperty("events")
    public Events getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(Events events) {
        this.events = events;
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
        return new ToStringBuilder(this).append("creators", creators).append("projects", projects).append("publications", publications).append("events", events).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(creators).append(projects).append(publications).append(additionalProperties).append(events).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Relationships) == false) {
            return false;
        }
        Relationships rhs = ((Relationships) other);
        return new EqualsBuilder().append(creators, rhs.creators).append(projects, rhs.projects).append(publications, rhs.publications).append(additionalProperties, rhs.additionalProperties).append(events, rhs.events).isEquals();
    }

}
