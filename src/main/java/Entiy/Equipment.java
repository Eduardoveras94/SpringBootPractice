/**
 * Created by Djidjelly Siclait on 10/5/2016.
 */
package Entiy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable{
    // Attributes
    @Id
    private String equipmentId;
    private String equipmentName;
    private SubFamily subFamily;
    private Integer stock;

    // Constructors
    public Equipment(){

    }

    public Equipment(String equipmentName, SubFamily subFamily, Integer stock){
        this.setEquipmentId("PUCMM-E-" + UUID.randomUUID().toString().split("-")[0].toUpperCase());
        this.setEquipmentName(equipmentName);
        this.setSubFamily(subFamily);
        this.setStock(stock);
    }

    // Auxiliary Function
    public Family getFamily(){
        return subFamily.getFamily();
    }

    // Getters and Setters
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public SubFamily getSubFamily() {
        return subFamily;
    }

    public void setSubFamily(SubFamily subFamily) {
        this.subFamily = subFamily;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
