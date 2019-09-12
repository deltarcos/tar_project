package com.cpe.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.entity.Patient;
import com.cpe.backend.entity.Employee;
import com.cpe.backend.entity.Duration;
import com.cpe.backend.entity.Room;
import com.cpe.backend.entity.Appointments;
import com.cpe.backend.repository.PatientRepository;
import com.cpe.backend.repository.EmployeeRepository;
import com.cpe.backend.repository.DurationRepository;
import com.cpe.backend.repository.AppointmentsRepository;
import com.cpe.backend.repository.RoomRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AppointmentsController {
    @Autowired
    private final AppointmentsRepository appointmentsRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DurationRepository durationRepository;

    AppointmentsController(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    @GetMapping("/appointments")
    public Collection<Appointments> Appointments() {
        return appointmentsRepository.findAll().stream().collect(Collectors.toList());
    }

    

    @PostMapping("/appointments/{patient_id}/{employee_id}/{room_id}/{duration_id}/{appmDate}/{detail}")
    public Appointments newVideoRental(Appointments newAppointments,
    @PathVariable long patient_id,
    @PathVariable long employee_id,
    @PathVariable long room_id,
    @PathVariable long duration_id,
    @PathVariable String appmDate,
    @PathVariable String detail) {
    //VideoRental newVideoRental = new VideoRental();

    Patient appmPatient = patientRepository.findById(patient_id);
    Room room = roomRepository.findById(room_id);
    Employee createdBy = employeeRepository.findById(employee_id);
    Duration duration = durationRepository.findById(duration_id);

    newAppointments.setCreatedBy(createdBy);
    newAppointments.setRoom(room);
    newAppointments.setCreateDate(new Date());
    newAppointments.setAppmPatient(appmPatient);
    newAppointments.setDuration(duration);

    return appointmentsRepository.save(newAppointments); //บันทึก Objcet ชื่อ VideoRental
    
    }
}