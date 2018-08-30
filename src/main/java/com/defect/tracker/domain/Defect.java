package com.defect.tracker.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="aplication_id")
    @ApiModelProperty(notes = "The database generated defect ID")
    private Integer id;

    @Column(nullable=false)
    @ApiModelProperty(notes = "Name of the defect")
    private String name;

    @Column(nullable=false)
    @ApiModelProperty(notes = "Description of the defect")
    private String description;

    @Column(nullable = false)
    @ApiModelProperty(notes = "OwnerId of the defect")
    private Integer ownerId;

    @Transient
    @ApiModelProperty(notes = "ownerName of the defect")
    private String ownerName;

    @Transient
    @ApiModelProperty(notes = "ownerRole of the defect")
    private String ownerRole;

}
