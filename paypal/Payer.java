
package com.example.backendtheater.paypal;

import java.util.HashMap;
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
    "name",
    "email_address",
    "payer_id",
    "address"
})
@Generated("jsonschema2pojo")
@Entity
@Table(name = "payers")
public class Payer {
    @JsonProperty("name") @OneToOne
    private Name name;
    @JsonProperty("email_address")
    private String emailAddress;
    @Id
    @JsonProperty("payer_id")
    private String payerId;
    @JsonProperty("address") @OneToOne
    private Address address;
    @JsonIgnore @Convert(converter = HashMapConverter.class)
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("email_address")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonProperty("payer_id")
    public String getPayerId() {
        return payerId;
    }

    @JsonProperty("payer_id")
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
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
