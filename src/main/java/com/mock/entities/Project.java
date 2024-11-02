package com.mock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PROJECT")
public class Project {
    @Id
    @Column(name = "PROJECT_ID", columnDefinition = "CHAR(7)")
    @NotBlank(message = "Please specify value for this field")
    private String projectId;

    @NotBlank(message = "Please specify value for this field")
    @Column(name = "PROJECT_NAME", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String projectName;

    @NotBlank(message = "Please specify value for this field")
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", columnDefinition = "DATE NOT NULL")
    private Date startDate;

    @NotBlank(message = "Please specify value for this field")
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", columnDefinition = "DATE NOT NULL")
    private Date endDate;


    @Column(name = "CUSTOMER_NAME", columnDefinition = "NVARCHAR(255)")
    private String customerName;

    @Column(name = "BUDGET", columnDefinition = "DECIMAL(15, 2)")
    private Double budget;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Claim> claimsList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<StaffProject> staffProjectsList;

    public Integer getDuration(){
        if(startDate != null && endDate != null){
            return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        }
        return 0;
    }
    public String getPm(){
        if(staffProjectsList != null && staffProjectsList.size() > 0){
            for (StaffProject staffProject : staffProjectsList) {
                if(staffProject.getRole() == Role.PM){
                    return staffProject.getStaff().getFirstName() + " " + staffProject.getStaff().getLastName();
                }
            }
        }
        return "[No PM]";
    }

    public String getQA(){
        if(staffProjectsList != null && staffProjectsList.size() > 0){
            for (StaffProject staffProject : staffProjectsList) {
                if(staffProject.getRole() == Role.QA){
                    return staffProject.getStaff().getFirstName() + " " + staffProject.getStaff().getLastName();
                }
            }
        }
        return "[No QA]";
    }
}
