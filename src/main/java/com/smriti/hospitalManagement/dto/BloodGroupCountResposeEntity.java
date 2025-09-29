package com.smriti.hospitalManagement.dto;

import com.smriti.hospitalManagement.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResposeEntity {

    private BloodGroupType bloodGroupType;
    private Long count;

}
