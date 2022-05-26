
package com.example.backendtheater.paypal;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "email_address",
    "merchant_id"
})
@Generated("jsonschema2pojo")
@Entity
public class Payee {

    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("merchant_id")
    @Id
    private String merchantId;
    @JsonIgnore @Convert(converter = HashMapConverter.class)
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("email_address")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonProperty("merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    @JsonProperty("merchant_id")
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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
