package com.project.buensabor.controllers;

import com.project.buensabor.dto.userDto.SectionDto;
import com.project.buensabor.entities.Section;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/section")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSection()
    {
        List<SectionDto> list = sectionService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public SectionDto getSectionByID(@PathVariable("id") Long sectionID)
    {
        SectionDto section = sectionService.getSection(sectionID);
        return section;
    }

    @PostMapping("/save")
    public Section saveSection(@Valid @RequestBody Section section)
    {
        return sectionService.saveSection(section);
    }

    @PutMapping("/update/{id}")
    public Section updateSection(@RequestBody Section section, @PathVariable("id") Long sectionID)
    {
        return sectionService.updateSection(section, sectionID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSectionById(@PathVariable("id") Long sectionID)
    {
        sectionService.deleteSectionById(sectionID);
        return "Deleted Successfully";
    }
}
