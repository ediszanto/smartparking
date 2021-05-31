package com.example.smartparking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.lang.annotation.Inherited;


@Getter
@Setter
@Table(name = "parking_spot")
@Entity
//@ToString
public class ParkingSpot extends Throwable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long number;
    private String size;
    private String status; // asta e pt <free> din documentatie ( FREE / TAKEN )


//
//    @PostPersist
//    public void setSpotNumber(){
//        if(id != null){
//            number = id;
//        }
//    }
}
