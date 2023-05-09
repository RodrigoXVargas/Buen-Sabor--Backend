package com.project.buensabor.controllers.Base;

import com.project.buensabor.entities.Base.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController <E extends Base, ID extends Serializable> {

    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> getAll();
    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> getAll(Pageable pageable);
    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> getOne(@PathVariable ID id);
    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> saveOne(@RequestBody E entity) throws Exception;
    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> updateOne(@PathVariable ID id, @RequestBody E entity);
    @PreAuthorize("hasAnyRole('admin', 'superAdmin')")
    public ResponseEntity<?> deleteById(@PathVariable ID id);

}
