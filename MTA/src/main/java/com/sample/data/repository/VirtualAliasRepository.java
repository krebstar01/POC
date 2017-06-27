package com.sample.data.repository;

import com.sample.domain.model.VirtualAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by justin on 15/02/2017.
 */
@Repository
public interface VirtualAliasRepository extends JpaRepository<VirtualAlias, Long> {

    // find VirtualAlias By Real Email source  --- helper call in order to get user for Update case
    VirtualAliasRepository findBySource (String realEmail);

}
