package com.smriti.hospitalManagement;

import com.smriti.hospitalManagement.dto.BloodGroupCountResposeEntity;
import com.smriti.hospitalManagement.entity.Patient;
import com.smriti.hospitalManagement.repository.PatientRepository;
import com.smriti.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {
        List<Patient> patientList = patientRepository.findAllPatientsWithAppointment();
        System.out.println(patientList);
        // SimpleJPARepository uses EntityManager to connect w db and all methods are implemented here
        /* Patient p1 = new Patient();
        patientRepository.save(p1); */
        /*yaha if you click save and uske implementation pr jao(the fx button on left),
        * on line 446, you will see
        * this.entityManager.persist(entity); that means your content is being saved-> persist state
        * if temporary -> transiet state else garbage collector
        * yaha entityManager ke persist ko call ho rha hai
        * Entity -> persist()->EntityManager->Transient->Persist/ PersistentContext->Insert->Database
        * yaha p1 toh transient state mei hai uske uar aapne persist call krdia so now you go to persistent state
        * and data goes in PersistentContext. Ab if transaction commit hojata hai, tabh toh insert the db wala call hogi */

    }
    /*when you run a transaction, there can be multiple commits.
    if the transaction becomes persistent toh fine wrna rollback ho jata hai
     */
    @Test
    public void testTransactionMethods(){
        /* Patient patient = patientService.getPatientById(1L);
        System.out.println(patient); */
        // Patient patient = patientRepository.findByName("Smriti Dube");
        /* List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(2004, 1, 21), "smriti@gmail.com" ); */

        // List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(2002,1,18));

        /* List<Patient> patientList = patientRepository.findAllPatients();
        for(Patient patient: patientList){
            System.out.println(patient);
        }
        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(Object[] objects: bloodGroupList){
            System.out.println(objects[0] + " " + objects[1]);
        }*/

        // this was not ideal so we will fix it now. we will write a NativeQuery now (pure sql query)

        /* int rowsUpdated= patientRepository.updateNameWithId("Smriti Dube Rajhans",1L);
        System.out.println(rowsUpdated); */

        /* List<BloodGroupCountResposeEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(BloodGroupCountResposeEntity bloodGroupCountRespose: bloodGroupList){
            System.out.println(bloodGroupCountRespose);
        }  */

        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(0,2));
        for(Patient patient: patientList){
            System.out.println(patient);
        }
    }
}