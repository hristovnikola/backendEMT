package com.example.labemt.service.impl;

import com.example.labemt.model.Country;
import com.example.labemt.model.dto.CountryDto;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.model.exceptions.CountryNotFoundException;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Country save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return this.countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, CountryDto countryDto) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return this.countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        Country country = this.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        this.countryRepository.delete(country);
    }
}
