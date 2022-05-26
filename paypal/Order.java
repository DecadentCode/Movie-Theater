
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
    "id",
    "intent",
    "status",
    "purchase_units",
    "payer",
    "create_time",
    "update_time",
    "links"
})
@Generated("jsonschema2pojo")
@Entity
@Table(name = "orders")
public class Order {
    //TODO expand
    public boolean isValid() {
        return getStatus().equals("COMPLETED");
    }

    @JsonProperty("id") @Column(name = "order_id")
    private String id;
    @JsonProperty("intent") @Column(name = "fillabuster")
    private String intent;
    @JsonProperty("status") @Column
    private String status;
    @JsonProperty("create_time") @Column
    private String createTime;
    @JsonProperty("update_time") @Column
    private String updateTime;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits = null;

    @ElementCollection
    @CollectionTable(name = "order_links", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_links")
    @JsonProperty("links")
    private List<Link> links = null;

    @Convert(converter = HashMapConverter.class)
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @OneToOne
    @JsonProperty("payer")
    private Payer payer;

    @Id
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("intent")
    public String getIntent() {
        return intent;
    }

    @JsonProperty("intent")
    public void setIntent(String intent) {
        this.intent = intent;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @ElementCollection
    @CollectionTable(name = "order_purchase_units", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_purchase_units")
    @JsonProperty("purchase_units")
    public List<PurchaseUnit> getPurchaseUnits() {
        return purchaseUnits;
    }

    @JsonProperty("purchase_units")
    public void setPurchaseUnits(List<PurchaseUnit> purchaseUnits) {
        this.purchaseUnits = purchaseUnits;
    }

    @JsonProperty("payer")
    public Payer getPayer() {
        return payer;
    }

    @JsonProperty("payer")
    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    @JsonProperty("create_time")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("create_time")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("update_time")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    @ElementCollection
    @CollectionTable(name = "order_links", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "order_links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Convert(converter = HashMapConverter.class)
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
