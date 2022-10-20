package com.modfrenzy.empathy.Controller;

import com.modfrenzy.empathy.Models.Reference;
import com.modfrenzy.empathy.Repo.ReferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private ReferenceRepo referenceRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Empathy start page";
    }

    @GetMapping(value = "/references")
    public List<Reference> getReferences() {
        return referenceRepo.findAll();
    }

    @PostMapping(value = "/references/save")
    public long saveReference(@RequestBody Reference reference) {
        Reference savedReference = referenceRepo.save(reference);
        return savedReference.getId();
    }

    @PutMapping(value = "/references/update/{id}")
    public String updateReference(@PathVariable long id, @RequestBody Reference reference) {
        Reference updatedReference = referenceRepo.findById(id).get();
        updatedReference.setName(reference.getName());
        updatedReference.setDescription(reference.getDescription());
        referenceRepo.save(updatedReference);
        return "Updated reference with id: " + id;
    }

    @DeleteMapping(value = "/references/delete/{id}")
    public String deleteReference(@PathVariable long id){
        Reference deletedReference = referenceRepo.findById(id).get();
        referenceRepo.delete(deletedReference);
        return "Delete reference with id: " + id;
    }
}
