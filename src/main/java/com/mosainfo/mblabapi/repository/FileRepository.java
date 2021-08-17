package com.mosainfo.mblabapi.repository;

import com.mosainfo.mblabapi.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    Optional<File> findByProyecto(String proyecto);
    boolean existsByProyecto(String proyecto);
}
