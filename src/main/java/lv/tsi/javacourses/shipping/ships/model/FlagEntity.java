package lv.tsi.javacourses.shipping.ships.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Flag")
@Table(name = "flags")
public class FlagEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Should be 3 characters at least")
    @Column(name = "name", length = 50)
    private String name;

    @NotBlank
    @Column(name = "capital", length = 100)
    private String capital;

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
