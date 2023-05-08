package com.project.buensabor.controllers.Base;

import com.project.buensabor.entities.Base.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController <E extends Base, ID extends Serializable> {

//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll();
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(Pageable pageable);
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOne(@PathVariable ID id);
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveOne(@RequestBody E entity) throws Exception;
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOne(@PathVariable ID id, @RequestBody E entity);
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable ID id);

}
