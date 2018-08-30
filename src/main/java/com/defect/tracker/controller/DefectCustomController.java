package com.defect.tracker.controller;

import com.defect.tracker.domain.Defect;
import com.defect.tracker.repositories.DefectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@BasePathAwareController
@Api(value="DefectController", description="Operations to track the defects")
public class DefectCustomController {

    @Autowired
    private DefectRepository defectRepository;

    private Logger log = Logger.getLogger(DefectCustomController.class);

    @RequestMapping(path = "applications", method = RequestMethod.GET, produces = "application/hal+json")
    @ApiOperation(value = "View a list of available applications",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved applications"),
            @ApiResponse(code = 401, message = "You are not authorized to view the applications"),
            @ApiResponse(code = 403, message = "Accessing the applications you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public @ResponseBody
    ResponseEntity<?> getApplications() {

        List<Defect> applications = defectRepository.findAll();
        log.info("Application count: " + applications.size());

        //applications.forEach(app -> getPersonInfo(app));

        Resources<Defect> resources = new Resources<>(applications);
        resources.add(linkTo(methodOn(DefectCustomController.class).getApplications()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "applications/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    @ApiOperation(value = "Search a application with an ID",response = Defect.class)
    public @ResponseBody
    ResponseEntity<?> getApplication(@PathVariable Integer id) {
        Defect application = defectRepository.findOne(id);
        //getPersonInfo(application);

        Resource resource = new Resource(application);
        resource.add(linkTo(methodOn(DefectCustomController.class).getApplication(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

}
