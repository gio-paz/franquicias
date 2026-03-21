package com.Giovanny.franquicias.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.Giovanny.franquicias.domain.model.Franquicia;

public interface FranquiciaR2dbcRepository extends ReactiveCrudRepository<Franquicia, Long> {
    
}
