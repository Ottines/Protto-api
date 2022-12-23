package com.protto.api.domain.person.controller;

import com.protto.api.domain.person.model.dao.PersonCreateRequest;
import com.protto.api.domain.person.model.dao.PersonVO;
import com.protto.api.domain.person.service.PersonService;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody @Valid PersonCreateRequest personCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personCreateRequest));
    }

    @GetMapping()
    public ResponseEntity<List<PersonVO>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> findById(@NonNull @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

}
