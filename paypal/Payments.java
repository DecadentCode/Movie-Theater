
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
    "captures"
})
@Entity
@Generated("jsonschema2pojo")
public class Payments {
    @Id
    @GeneratedValue
    @Column(name = "payments_id")
    private int id;

    @ElementCollection
    @CollectionTable(name = "payment_captures", joinColumns = @JoinColumn(name = "payments_id"))
    @Column(name = "payment_captures")
    @JsonProperty("captures")
    private List<Capture> captures = null;
    @JsonIgnore @Convert(converter = HashMapConverter.class)
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("captures")
    public List<Capture> getCaptures() {
        return captures;
    }

    @JsonProperty("captures")
    public void setCaptures(List<Capture> captures) {
        this.captures = captures;
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
