package uz.map.uzbekistanmap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
//    model, stateNumber, madeYear, type, Region
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String stateNumber;

    @Column(nullable = false)
    private String madeYear;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    private Region region;
}
