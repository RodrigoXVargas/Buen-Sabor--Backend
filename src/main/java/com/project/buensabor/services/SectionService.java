package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.SectionDto;
import com.project.buensabor.entities.Section;
import com.project.buensabor.repositories.SectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {


    private final SectionRepository sectionRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    public List<SectionDto> getAllCategories() {
        List<SectionDto> result = new ArrayList<>();
        List<Section> list = sectionRepository.findAll();

        for (Section section : list) {
            SectionDto sectionDto = SectionDto.builder().section(section.getSection()).build();
            result.add(sectionDto);
        }
        return result;
    }

    public SectionDto getSection(Long sectionID) {
        Optional<Section> section = sectionRepository.findById(sectionID);
        if(!(section.isEmpty())) {
            return SectionDto.builder().section(section.get().getSection()).build();
        }
        return null;
    }

    public Section updateSection(Section section, Long sectionID) {
        Optional<Section> sectionList = sectionRepository.findById(sectionID);
        if (sectionList.get().getId_section() == section.getId_section()) {
            return sectionRepository.save(section);
        } else {
            return null;
        }
    }

    public void deleteSectionById(Long sectionID) {
        sectionRepository.deleteById(sectionID);
    }
}
