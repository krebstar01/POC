package com.sample.data.repository;

import com.sample.domain.model.VirtualDomain;
import com.sample.domain.model.VirtualUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by justin on 12.06.17.
 */
@Repository
public interface VirtualDomainRepository  extends JpaRepository<VirtualDomain, Long> {

    VirtualDomain findByVdName(String vdName);
}
