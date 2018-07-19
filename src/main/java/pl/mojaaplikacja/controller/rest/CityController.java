package pl.mojaaplikacja.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.mojaaplikacja.model.City;

@RestController
@RequestMapping("/cities")
public class CityController {

	private List<City> cities;

	public CityController() {
		cities = Collections.synchronizedList(new ArrayList<City>());
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<City> getCities(@RequestParam(defaultValue="name") String orderBy) {
	  List<City> citiesCopy = new ArrayList<>(cities);
        if("name".equals(orderBy)) {
            citiesCopy.sort(Comparator.comparing(City::getName));
        } else if("population".equals(orderBy)) {
            citiesCopy.sort(Comparator.comparing(City::getPopulation));
        }
        return citiesCopy;
	}
	
	@GetMapping(path="/{id}")
	public City getCity(@PathVariable int id) {
		return cities.get(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveCity(@RequestBody City city) {
		cities.add(city);
	}
	
	
}

