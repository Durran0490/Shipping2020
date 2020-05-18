package lv.tsi.javacourses.shipping.ships.model;

import lv.tsi.javacourses.shipping.auth.model.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity(name = "Booking")
@Table(name = "bookings")
public class BookingEntity implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created", updatable = false)
    private Instant create;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "vessel_id")
    private VesselEntity vessel;

    @PrePersist
    public void prePersist() {
        create = Instant.now();
        if (status == null) {
            status = BookingStatus.ACTIVE;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreate() {
        return create;
    }

    public void setCreate(Instant create) {
        this.create = create;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public VesselEntity getVessel() {
        return vessel;
    }

    public void setVessel(VesselEntity vessel) {
        this.vessel = vessel;
    }
}
