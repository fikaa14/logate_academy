package com.academy.demo.specs;

import com.academy.demo.entity.City;
import com.academy.demo.entity.Country;
import com.academy.demo.search.CitySearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CitySearchSpecification implements Specification<City> {

    private CitySearch citySearch;

    public CitySearchSpecification(CitySearch citySearch) {this.citySearch = citySearch;}

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<City, Country> countryJoin = getCityCountryJoin(root);

        filterByCityNameFirstCharachters(root, criteriaBuilder, predicates);

        filterByCountryName(criteriaBuilder, predicates, countryJoin);

        filterByShortCode(criteriaBuilder, predicates, countryJoin);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private void filterByShortCode(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Join<City, Country> countryJoin) {
        if(citySearch.getCountryShortCode() != null)
        {
            Predicate countryShortCodePredicate = criteriaBuilder.equal(
                    countryJoin.get("shortCode"), citySearch.getCountryShortCode()
            );
            predicates.add(countryShortCodePredicate);
        }
    }

    private void filterByCountryName(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Join<City, Country> countryJoin) {
        if(citySearch.getCountryName() != null)
        {
            Predicate countryNamePredicate = criteriaBuilder.equal(
                    countryJoin.get("name"), citySearch.getCountryName());
            predicates.add(countryNamePredicate);
        }
    }

    private void filterByCityNameFirstCharachters(Root<City> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if(citySearch.getName() != null)
        {
            Predicate cityNamePredicate = criteriaBuilder.like(root.get("name"), citySearch.getName() + "%");
            predicates.add(cityNamePredicate);
        }
    }

    private Join<City, Country> getCityCountryJoin(Root<City> root) {
        Fetch<City, Country> countryFetch = root.fetch("country");
        return (Join<City, Country>)countryFetch;
    }
}
