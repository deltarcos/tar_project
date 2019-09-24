package com.cpe.backend;
import com.cpe.backend.entity.Patient;
import com.cpe.backend.entity.Employee;
import com.cpe.backend.entity.Duration;
import com.cpe.backend.entity.Room;
import com.cpe.backend.repository.PatientRepository;
import com.cpe.backend.repository.EmployeeRepository;
import com.cpe.backend.repository.DurationRepository;
import com.cpe.backend.repository.RoomRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PatientRepository patientRepository, EmployeeRepository employeeRepository, DurationRepository durationRepository, RoomRepository roomRepository) {
		return args -> {
			Stream.of("พญ.ขวัญชนก อังศุภโชติ", "นพ.นครินทร์ สาลีทอง", "นพ.อดิเรก ตรียสรศัย", "นพ.ยิ่งยศ สันติธนานนท์").forEach(name -> {
				Employee employee = new Employee(); // สร้าง Object Employee
				employee.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Employee
				employeeRepository.save(employee); // บันทึก Objcet ชื่อ Employee
			});

			Stream.of("สมชาย วงษ์ประกอบ", "งามเฉิด สิตถาหุล", "ไตรรงค์ โตสุขุมวงศ์", "รัชพล สดาวรรธน์").forEach(name -> {
				Patient patient = new Patient(); // สร้าง Object Patient
				patient.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Patient
				patientRepository.save(patient); // บันทึก Objcet ชื่อ Patient
			});

			Stream.of("08:00-10:00 น.", "10:00-12:00 น.", "13:00-15:00 น.", "15:00-17:00 น.").forEach(time -> {
				Duration duration = new Duration(); // สร้าง Object Time
				duration.setTime(time); // set ชื่อ (name) ให้ Object ชื่อ Time
				durationRepository.save(duration); // บันทึก Objcet ชื่อ Time
			});

			Stream.of("ห้องตรวจ 1", "ห้องตรวจ 2", "ห้องตรวจ 3", "ห้องตรวจ 4").forEach(title -> {
				Room room = new Room(); // สร้าง Object Room
				room.setTitle(title); // set ชื่อ (name) ให้ Object ชื่อ Room
				roomRepository.save(room); // บันทึก Objcet ชื่อ Room
			});

			patientRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Patient บน Terminal
			employeeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
			durationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Time บน Terminal
			roomRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Room บน Terminal
		};
	}

}
