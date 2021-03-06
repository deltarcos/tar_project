package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DURATION")
public class Duration {

    @Id
    @SequenceGenerator(name="DURATION_SEQ",sequenceName="DURATION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DURATION_SEQ")
    @Column(name="DURATION_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String time;

    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "duration"
    private Collection<Appointments> appm;
}