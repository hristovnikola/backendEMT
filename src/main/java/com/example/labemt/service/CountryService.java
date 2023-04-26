package com.example.labemt.service;

import com.example.labemt.model.Country;
import com.example.labemt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();

    Optional<Country> findById(Long id);

    Country save(CountryDto countryDto);

    Country edit(Long id, CountryDto countryDto);

    void delete(Long id);
}
