package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DOCTORPROFILE")
public class DoctorProfile {
    @Id
    @SequenceGenerator(name="DoctorProfile_SEQ",sequenceName="DoctorProfile_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DoctorProfile_SEQ")
    @Column(name="DOCTORPROFILE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    private @NonNull String password;


    
    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Appointments> appm;
}