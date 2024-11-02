package com.mock.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "CLAIM_DETAIL")
public class ClaimDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", columnDefinition = "INT")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CLAIM", columnDefinition = "DATE NOT NULL")
    private Date dateClaim;

    @Column(name = "DAY", columnDefinition = "VARCHAR(20) NOT NULL")
    private String day;

    @Temporal(TemporalType.TIME)
    @Column(name = "FROM_TIME", columnDefinition = "TIME NOT NULL")
    private Time fromTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "TO_TIME", columnDefinition = "TIME NOT NULL")
    private Time toTime;

    @Column(name = "REMARKS_DETAILS", columnDefinition = "NVARCHAR(100) NOT NULL")
    private String remarksDetails;

    @Column(name = "TOTAL_NO_OF_HOURS", columnDefinition = "DECIMAL(3,1)")
    private Double totalNoOfHours;

    @ManyToOne
    @JoinColumn(name = "CLAIM_ID", referencedColumnName = "CLAIM_ID", columnDefinition = "INT NOT NULL")
    private Claim claim;
    /** chỉnh sủa ClaimId = Claim*/

}
