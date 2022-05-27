
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
    "reference_id",
    "amount",
    "payee",
    "shipping",
    "payments"
})
@Entity
@Generated("jsonschema2pojo")
public class PurchaseUnit {
    @Id
    @GeneratedValue
    private int id;

    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("amount") @OneToOne
    private Amount amount;
    @JsonProperty("payee") @OneToOne
    private Payee payee;
    @JsonProperty("shipping") @OneToOne
    private Shipping shipping;
    @JsonProperty("payments") @OneToOne
    private Payments payments;
    @JsonIgnore @Convert(converter = HashMapConverter.class)
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reference_id")
    public String getReferenceId() {
        return referenceId;
    }

    @JsonProperty("reference_id")
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty("amount")
    public Amount getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @JsonProperty("payee")
    public Payee getPayee() {
        return payee;
    }

    @JsonProperty("payee")
    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    @JsonProperty("shipping")
    public Shipping getShipping() {
        return shipping;
    }

    @JsonProperty("shipping")
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @JsonProperty("payments")
    public Payments getPayments() {
        return payments;
    }

    @JsonProperty("payments")
    public void setPayments(Payments payments) {
        this.payments = payments;
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
