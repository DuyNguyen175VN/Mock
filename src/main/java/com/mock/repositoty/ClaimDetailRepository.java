package com.mock.repositoty;


import com.mock.entities.ClaimDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimDetailRepository extends JpaRepository<ClaimDetail, Integer> {
}
