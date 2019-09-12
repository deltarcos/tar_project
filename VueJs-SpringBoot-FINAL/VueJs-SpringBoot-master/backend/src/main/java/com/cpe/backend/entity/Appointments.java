package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.entity.Employee;;

@Data
@Entity
@NoArgsConstructor
@Table(name="APPOINTMENTS")
public class Appointments {

    @Id
    @SequenceGenerator(name="appointments_seq",sequenceName="appointments_seq", initialValue=10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="appointments_seq")
    @Column(name = "APPOINTMENTS_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CREATEDATE")
    private @NonNull Date createDate;

    @Column(name="APPMDATE")
    private @NonNull String appmDate;

    @Column(name="DETAIL")
    private @NonNull String detail;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "Room_ID", insertable = true)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
    @JoinColumn(name = "Patient_ID", insertable = true)
    private Patient appmPatient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)
    @JoinColumn(name = "Duration_ID", insertable = true)
    private Duration duration;


}