package com.defect.tracker.repositories;

import com.defect.tracker.domain.Defect;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface DefectRepository extends JpaRepository<Defect, Integer> {

    @RestResource(path = "descriptionIgnoreCaseContaining", rel = "descriptionIgnoreCaseContaining")
    @ApiOperation(value = "Search defect based on description",response = List.class)
    Page findByDescriptionIgnoreCaseContaining(@Param("description") String description, Pageable p);

    @ApiOperation(value = "Search defect based on name",response = List.class)
    List<Defect> findByName(@Param("name") String name);
}
