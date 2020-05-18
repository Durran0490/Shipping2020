package lv.tsi.javacourses.shipping.ships.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Vessel")
@Table(name = "vessels")
public class VesselEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "imo", nullable = false)
    private String imo;

    @Min(value = 1900)
    @Digits(integer = 4, fraction = 0)
    @Column(name = "year", nullable = false)
    private int year;

    @Max(value = 1000000)
    @Min(value = 100)
    @Column(name = "deadweight", nullable = false)
    private int deadweight;

    @DecimalMax("60.00")
    @DecimalMin("1.00")
    @Digits(integer = 2, fraction = 2)
    @Column(name = "speed", nullable = false)
    private Double speed;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipyard_id", nullable = false)
    private ShipyardEntity shipyard;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flag_id", nullable = false)
    private FlagEntity flag;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vesseltype_id", nullable = false)
    private VesselTypeEntity vesseltype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_file_id")
    private FileEntity cover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDeadweight() {
        return deadweight;
    }

    public void setDeadweight(int deadweight) {
        this.deadweight = deadweight;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public ShipyardEntity getShipyard() {
        return shipyard;
    }

    public void setShipyard(ShipyardEntity shipyard) {
        this.shipyard = shipyard;
    }

    public VesselTypeEntity getVesseltype() {
        return vesseltype;
    }

    public void setVesseltype(VesselTypeEntity vesseltype) {
        this.vesseltype = vesseltype;
    }

    public FlagEntity getFlag() {
        return flag;
    }

    public void setFlag(FlagEntity flag) {
        this.flag = flag;
    }

    public FileEntity getCover() {
        return cover;
    }

    public void setCover(FileEntity cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VesselEntity that = (VesselEntity) o;
        return year == that.year &&
                deadweight == that.deadweight &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imo, that.imo) &&
                Objects.equals(speed, that.speed) &&
                Objects.equals(shipyard, that.shipyard) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(vesseltype, that.vesseltype) &&
                Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imo, year, deadweight, speed, shipyard, flag, vesseltype);
    }

    @Override
    public String toString() {
        return "VesselEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imo='" + imo + '\'' +
                ", year=" + year +
                ", deadweight=" + deadweight +
                ", speed=" + speed +
                ", shipyard=" + shipyard +
                ", flag=" + flag +
                ", vesseltype=" + vesseltype +
                ", cover=" + cover +
                '}';
    }
}

