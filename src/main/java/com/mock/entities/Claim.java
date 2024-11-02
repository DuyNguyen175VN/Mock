package com.mock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CLAIM")
public class Claim {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CLAIM_ID", columnDefinition = "INT")
    private Integer claimId;

    @Column(name = "TOTAL_WORKING_HOUR", columnDefinition = "DECIMAL(3, 1) NOT NULL")
    private Double totalWorkingHour;

    @Column(name = "REMARKS_CLAIM", columnDefinition = "TEXT")
    private String remarksClaim;

    @Column(name = "AUDIT_TRAIL", columnDefinition = "TEXT")
    private String auditTrail;

    /*CHECK([STATUS] IN ('DRAFT', 'PENDING APPROVAL', 'APPROVED', 'PAID', 'REJECTED', 'CANCELLED'))*/
    @Column(name = "STATUS", columnDefinition = "VARCHAR(16) NOT NULL")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "STAFF_ID", referencedColumnName = "STAFF_ID", columnDefinition = "CHAR(7) NOT NULL")
    private Staff staff;
    /** Chỉnh sửa StaffId = Staff*/

    @ManyToOne
    @NotNull(message = "Project can't not null!")
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", columnDefinition = "CHAR(7) NOT NULL")
    private Project project;
    /** Chỉnh sửa ProjectId = Project*/

    @NotNull(message = "Details can not null")
    @Size(min = 1, message = "Details must contain at least one entry")
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimDetail> claimDetailsList;
}
