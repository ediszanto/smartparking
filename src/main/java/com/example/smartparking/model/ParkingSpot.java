package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "parking_spot")
@Entity
@ToString
//@Embeddable
public class ParkingSpot  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long number;

    @NotNull
    private String size;

    @NotNull
    private String status; // asta e pt <free> din documentatie ( FREE / TAKEN )


    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", number=" + number +
                ", size='" + size + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
