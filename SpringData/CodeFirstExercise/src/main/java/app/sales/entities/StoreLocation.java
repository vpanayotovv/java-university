package app.sales.entities;

import app.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {

    String locationName;

    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
