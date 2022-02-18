package com.academy.demo.repository;

import com.academy.demo.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>
{

    List<Adress> findByPostalCode(String postalCode);

    List<Adress> findByCityName(String cityName);

    List<Adress> findByCityCountryShortCode(String shortCode);

    @Query(
            value = " select adress from Adress adress " +
                    "where adress.postalCode = :postalCode"
    )
    List<Adress> findByPostalCodeJPQL(@Param("postalCode") String postalCode);

    @Query(
            value = " SELECT adress FROM Adress adress" +
                    " JOIN FETCH adress.city as city" +
                    " WHERE city.name = :cityName"
    )
    List<Adress> findByCityNameJPQL(@Param("cityName") String cityName);

    @Query(
            value = " SELECT adress FROM Adress adress" +
                    " JOIN FETCH adress.city as city " +
                    " JOIN FETCH city.country as country" +
                    " WHERE country.shortCode = :shortCode"
    )
    List<Adress> findByCityCountryShortCodeJPQL(@Param("shortCode") String shortCode);

}
