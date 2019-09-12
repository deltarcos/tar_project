package com.cpe.backend.controller;

import com.cpe.backend.entity.Room;
import com.cpe.backend.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RoomController {

    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public Collection<Room> Rooms() {
        return roomRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/room/{id}")
    public Optional<Room> Rooms(@PathVariable Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room;
    }
}