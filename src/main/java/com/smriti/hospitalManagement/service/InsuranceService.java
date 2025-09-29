package com.smriti.hospitalManagement.service;
import com.smriti.hospitalManagement.entity.Insurance;
import com.smriti.hospitalManagement.entity.Patient;
import com.smriti.hospitalManagement.repository.InsuranceRepository;
import com.smriti.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional consistency maintainance

        return patient;
    }

    public Patient dissaccociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with the id: " + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
