package com.smriti.hospitalManagement;
import com.smriti.hospitalManagement.entity.Appointment;
import com.smriti.hospitalManagement.entity.Insurance;
import com.smriti.hospitalManagement.entity.Patient;
import com.smriti.hospitalManagement.service.AppointmentService;
import com.smriti.hospitalManagement.service.InsuranceService;
import com.smriti.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,30))
                .build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

       var newPatient = insuranceService.dissaccociateInsuranceFromPatient(patient.getId());
       System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,14,30))
                .reason("Skin Burns")
                .build();
        var newAppointment = appointmentService.createNewAppointment(appointment,1L,2L);
        System.out.println(newAppointment);

        var updateAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println(updateAppointment);
    }
}
