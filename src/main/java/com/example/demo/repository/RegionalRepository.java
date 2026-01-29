package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Regional;
public interface RegionalRepository extends JpaRepository<Regional, Long> {
}
