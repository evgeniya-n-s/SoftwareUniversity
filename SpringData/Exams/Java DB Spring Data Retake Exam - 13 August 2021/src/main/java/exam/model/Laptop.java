package exam.model;

import exam.model.enums.WarrantyType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mac_address", unique = true,nullable = false)
    private String macAddress;

    @Column(name = "cpu_speed",nullable = false)
    private double cpuSpeed;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int storage;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "warranty_type",nullable = false)
    private WarrantyType warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %.2f\n" +
                "#Shop name - %s\n" +
                "##Town - %s\n",
                this.macAddress,
                this.cpuSpeed,
                this.ram,
                this.storage,
                this.price,
                this.getShop().getName(),
                this.getShop().getTown().getName());
    }
}

