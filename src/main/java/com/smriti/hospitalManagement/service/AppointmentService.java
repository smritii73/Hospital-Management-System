package com.smriti.hospitalManagement.service;
import com.smriti.hospitalManagement.entity.Appointment;
import com.smriti.hospitalManagement.entity.Doctor;
import com.smriti.hospitalManagement.entity.Patient;
import com.smriti.hospitalManagement.repository.AppointmentRepository;
import com.smriti.hospitalManagement.repository.DoctorRepository;
import com.smriti.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have an id");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);
        return appointmentRepository.save(appointment);
    }
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        appointment.setDoctor(doctor); // will automatically call the update, because t is dirty
        doctor.getAppointments().add(appointment); // just for bidirectional consistency
        return appointment;
    }
}
