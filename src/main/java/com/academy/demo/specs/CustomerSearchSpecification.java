package com.academy.demo.specs;

import com.academy.demo.entity.Adress;
import com.academy.demo.entity.City;
import com.academy.demo.entity.Country;
import com.academy.demo.entity.Customer;
import com.academy.demo.search.CustomerSearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerSearchSpecification implements Specification<Customer> {

    private CustomerSearch customerSearch;

    public CustomerSearchSpecification(CustomerSearch customerSearch)
    {
        this.customerSearch = customerSearch;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
    {
        List<Predicate> predicates = new ArrayList<>();

        Join<Customer, Adress> adressJoin = fetchRelations(root);

        filterByFullName(root, criteriaBuilder, predicates);
        filterByEmail(root, criteriaBuilder, predicates);
        filterByPhoneNumber(root, criteriaBuilder, predicates);
        filterByStreet(criteriaBuilder, predicates, adressJoin);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Join<Customer, Adress> fetchRelations(Root<Customer> root) {
        Fetch<Customer, Adress> adressFetch = root.fetch("adresses", JoinType.INNER);
        Join<Customer, Adress> adressJoin = (Join<Customer, Adress>) adressFetch;

        Fetch<Adress, City> cityFetch = adressJoin.fetch("city");
        Join<Adress, City> cityJoin = (Join<Adress,City>)cityFetch;

        Fetch<City, Country> countryFetch = cityJoin.fetch("country");
        Join<City, Country> countryJoin = (Join<City, Country>)countryFetch;
        return adressJoin;
    }

    private void filterByStreet(CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Join<Customer, Adress> adressJoin) {
        if(customerSearch.getAddressStreet() != null)
        {
            //WHERE address.street = "Ulica..."

            Predicate addressStreetPredicate = criteriaBuilder.equal(
                    adressJoin.get("street"), customerSearch.getAddressStreet());

            predicates.add(addressStreetPredicate);
        }
    }

    private void filterByPhoneNumber(Root<Customer> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if(customerSearch.getPhone() != null)
        {
            Predicate phonePredicate = criteriaBuilder.equal(root.get("phone"), customerSearch.getPhone());
            predicates.add(phonePredicate);
        }
    }

    private void filterByEmail(Root<Customer> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if(customerSearch.getEmail() != null)
        {
            Predicate emailPredicate = criteriaBuilder.like(root.get("email"),"%" + customerSearch.getEmail() + "%");
            predicates.add(emailPredicate);
        }
    }

    private void filterByFullName(Root<Customer> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if(customerSearch.getFullName() != null)
        {
            //WHERE full_name = :full_name
            Predicate fullNamePredicate = criteriaBuilder.equal(root.get("fullName"), customerSearch.getFullName());
            predicates.add(fullNamePredicate);
        }
    }
}
