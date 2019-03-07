/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ed.synthsys.seek.dom.common;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "original_filename",
    "url",
    "md5sum",
    "sha1sum",
    "content_type",
    "link",
    "size"
})
public class DataFileContentBlobResponse implements Serializable {

    private static final long serialVersionUID = -1949216203818860869L;

    @JsonProperty("original_filename")
    private String originalFilename;
    @JsonProperty("url")
    private String url;
    @JsonProperty("md5sum")
    private String md5sum;
    @JsonProperty("sha1sum")
    private String sha1sum;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("link")
    private String link;
    @JsonProperty("size")
    private String size;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    private String filepath;

    @JsonProperty("original_filename")
    public String getOriginalFilename() {
        return originalFilename;
    }

    @JsonProperty("original_filename")
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("md5sum")
    public String getMd5sum() {
        return md5sum;
    }

    @JsonProperty("md5sum")
    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    @JsonProperty("sha1sum")
    public String getSha1sum() {
        return sha1sum;
    }

    @JsonProperty("sha1sum")
    public void setSha1sum(String sha1sum) {
        this.sha1sum = sha1sum;
    }

    @JsonProperty("content_type")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("content_type")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("originalFilename", originalFilename).append("url", url).append("md5sum", md5sum).append("sha1sum", sha1sum).append("contentType", contentType).append("link", link).append("size", size).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(md5sum).append(sha1sum).append(additionalProperties).append(link).append(originalFilename).append(contentType).append(url).append(size).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataFileContentBlobResponse) == false) {
            return false;
        }
        DataFileContentBlobResponse rhs = ((DataFileContentBlobResponse) other);
        return new EqualsBuilder().append(md5sum, rhs.md5sum).append(sha1sum, rhs.sha1sum).append(additionalProperties, rhs.additionalProperties).append(link, rhs.link).append(originalFilename, rhs.originalFilename).append(contentType, rhs.contentType).append(url, rhs.url).append(size, rhs.size).isEquals();
    }

}
