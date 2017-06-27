package com.sample.service.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.data.repository.VirtualAliasRepository;
import com.sample.data.repository.VirtualDomainRepository;
import com.sample.domain.model.VirtualAlias;
import com.sample.domain.model.VirtualDomain;
import com.sample.domain.model.exception.BadArgumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 02/03/2017.
 */
@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VirtualAliasController extends BaseRestController {

    @Autowired
    VirtualAliasRepository virtualAliasRepository;

    @Autowired
    VirtualDomainRepository virtualDomainRepository;


    @RequestMapping(value = "/virtualAliases", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new virtual alias", notes = "create new virtual alias")
    public void saveVirtualAlias(
            @ApiParam(value = "Default value for sourceEmail email", required = true, defaultValue = "yourRealEmail@example.com") @RequestParam(value = "sourceEmail", required = true) String sourceEmail,
            @ApiParam(value = "Default value for destinationEmail email", required = true, defaultValue = "yourVirtualEmail@example.com") @RequestParam(value = "destinationEmail", required = true) String destinationEmail,
            @ApiParam(value = "Default value for domain", required = true, defaultValue = "example.com") @RequestParam(value = "domain", required = true) String domain
    ) {

        VirtualDomain virtualDomain = virtualDomainRepository.findByVdName(domain);

        if (virtualDomain == null) {
            String message = "No VirtualDomainId found for domain " + domain;
            throw new BadArgumentException(message, "virtualAliases", "POST");
        }

        VirtualAlias alias = new VirtualAlias();
        alias.setSource(sourceEmail);
        alias.setDestination(destinationEmail);
        alias.setVirtualDomain(virtualDomain);

        virtualAliasRepository.save(alias);

    }



    @RequestMapping(value = "/virtualAliases/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new virtual alias", notes = "create new virtual alias")
    public void updateVirtualAlias(
            @PathVariable("id") long id,
            @ApiParam(value = "Default value for sourceEmail email", required = true, defaultValue = "yourRealEmail@example.com") @RequestParam(value = "sourceEmail", required = true) String sourceEmail,
            @ApiParam(value = "Default value for destinationEmail email", required = true, defaultValue = "yourVirtualEmail@example.com") @RequestParam(value = "destinationEmail", required = true) String destinationEmail,
            @ApiParam(value = "Default value for domain", required = true, defaultValue = "example.com") @RequestParam(value = "domain", required = true) String domain
    ) {



        VirtualAlias alias = virtualAliasRepository.findOne(id);

        if(alias!=null){

            VirtualDomain virtualDomain = virtualDomainRepository.findByVdName(domain);

            if (virtualDomain == null) {
                String message = "No VirtualDomainId found for domain " + domain;
                throw new BadArgumentException(message, "virtualAliases", "PUT");
            }


            alias.setSource(sourceEmail);
            alias.setDestination(destinationEmail);
            alias.setVirtualDomain(virtualDomain);
            virtualAliasRepository.save(alias);
        }

    }




    @RequestMapping(value = "/virtualAliases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all virtual aliases", notes = "display all existing virtual aliases")
    public List<VirtualAlias> findAllVirtualAliases() {
        List<VirtualAlias> results = new ArrayList<>();
        results.addAll(virtualAliasRepository.findAll());
        return results;
    }


}
