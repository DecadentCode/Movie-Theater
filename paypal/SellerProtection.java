
package com.example.backendtheater.paypal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "dispute_categories"
})
@Entity
@Generated("jsonschema2pojo")
public class SellerProtection {
    @Id
    @GeneratedValue
    @Column(name = "sellerprotection_id")
    private int id;

    @JsonProperty("status")
    private String status;
    @ElementCollection
    @CollectionTable(name = "dispute_categories", joinColumns = @JoinColumn(name = "sellerprotection_id"))
    @Column(name = "dispute_categories")
    @JsonProperty("dispute_categories")
    private List<String> disputeCategories = null;
    @JsonIgnore @Convert(converter = HashMapConverter.class)
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("dispute_categories")
    public List<String> getDisputeCategories() {
        return disputeCategories;
    }

    @JsonProperty("dispute_categories")
    public void setDisputeCategories(List<String> disputeCategories) {
        this.disputeCategories = disputeCategories;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
