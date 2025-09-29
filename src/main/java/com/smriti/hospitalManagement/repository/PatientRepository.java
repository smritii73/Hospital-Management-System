package com.smriti.hospitalManagement.repository;

import com.smriti.hospitalManagement.dto.BloodGroupCountResposeEntity;
import com.smriti.hospitalManagement.entity.Patient;
import com.smriti.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);
    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup=?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate> :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.smriti.hospitalManagement.dto.BloodGroupCountResposeEntity(p.bloodGroup, Count(p)) " +
            "from Patient p group by p.bloodGroup")
    // List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResposeEntity> countEachBloodGroupType();

    @Query(value = "select * from patient",nativeQuery = true)
    //we write this query to tell the jpql ki no  need to pass to hibernate,
    // it is a raw sql query toh can be directly passed by using nativeQuery
    Page<Patient> findAllPatients(Pageable pageable);

    // Query helpful for updating
    @Transactional
    @Modifying //transactional management only possible when jpa is told that this query will update db
    @Query("UPDATE Patient p SET p.name = :name where p.id= :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);

    // @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientsWithAppointment();
}