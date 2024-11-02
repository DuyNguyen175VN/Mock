package com.mock.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STAFF")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "staffId") // Sử dụng staffId để tránh đệ quy quá sâu
public class Staff {
    @Id
    @NotBlank(message = "Please specify value for this field")
    @Column(name = "STAFF_ID", columnDefinition = "CHAR(7)")
    private String staffId;

    @NotBlank(message = "Please specify value for this field")
    @Column(name = "FIRST_NAME", columnDefinition = "NVARCHAR(20) NOT NULL")
    private String firstName;

    @NotBlank(message = "Please specify value for this field")
    @Column(name = "LAST_NAME", columnDefinition = "NVARCHAR(20) NOT NULL")
    private String lastName;

    @NotBlank(message = "Please specify value for this field")
    @Column(name = "GENDER", columnDefinition = "VARCHAR(20) NOT NULL")
    private String gender;

    @NotBlank(message = "Please specify value for this field")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email address")
    @Column(name = "EMAIL", nullable = false, columnDefinition = "VARCHAR(30) CHECK(EMAIL LIKE '%_@__%.__%')")
    private String email;

    @NotEmpty (message = "Department cannot be empty")
    @Column(name = "DEPARTMENT", columnDefinition = "VARCHAR(50)")
    private String Department;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
//    @Column(name = "PHONE_NUMBER", nullable = false, columnDefinition = "VARCHAR(11) CHECK(PHONE_NUMBER ~ '^[0-9]+$')")
    @Column(name = "PHONE_NUMBER", nullable = false, columnDefinition = "VARCHAR(11) CHECK(PHONE_NUMBER NOT LIKE '%[^0-9]%')")
    private String phoneNumber;


    @NotNull(message = "Please specify value for this field")
    @Column(name = "SALARY", columnDefinition = "NUMERIC(10, 2)")
    private Double salary;

    @NotNull(message = "Please specify value for this field")
    @Column(name = "RANK", nullable = false, columnDefinition = "INT CHECK(RANK BETWEEN 1 AND 4)")
    private Integer rank;

    @NotBlank(message = "Please specify value for this field")
    @Column(name = "USERNAME", nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Claim> claimsList;

    @JsonIgnore
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<StaffProject> staffProjectsList;

    public List<Staff> getPM(){
        List<Staff> result = new ArrayList<>();
        if(staffProjectsList != null && staffProjectsList.size() > 0){
            for (StaffProject staffProject : staffProjectsList) {
                if(staffProject.getRole() == Role.PM){
                    result.add(staffProject.getStaff());
                }
            }
            return result;
        }
        return null;
    }

}
