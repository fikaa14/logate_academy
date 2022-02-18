package com.academy.demo.repository;

import com.academy.demo.dto.CustomerWithAdressDTO;
import com.academy.demo.entity.Customer;
import com.academy.demo.projection.CustomerProjection;
import com.academy.demo.projection.CustomerWithAddressInCityProjection;
import com.academy.demo.specs.CustomerSearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    @Query(
            value = "select customer from Customer as customer" +
                    " where customer.phone like :phonePart% "
    )
    Page<Customer> findCustomersWithPhoneNumberStartingWith(@Param("phonePart") String phonePart, Pageable pageable);

    @Query(value = "select customer.id from Customer customer")
    Page<Integer> findCustomerIds(Pageable pageable);

    @Query(value = "select distinct customer from Customer customer " +
            "join fetch customer.adresses " +
            "where customer.id in (:customerIds)")
    List<Customer> findAllByIdIn(@Param("customerIds") List<Integer> customerIds);

    @Query(
        value = "select customer.full_name as fullName, " +
                "customer.email as email, " +
                "city.name as cityName, " +
                "country.name as countryName, " +
                "adress.street as street " +
                "from customer, city, country, adress, customer_adress " +
                "where customer.id = customer_adress.customer_id and " +
                "adress.id = customer_adress.adress_id and " +
                "adress.city_id = city.id and " +
                "city.country_id = country.id and " +
                "city.name = :cityName",
        nativeQuery = true
    )
    List<CustomerWithAddressInCityProjection> findAllByAddressInCity(@Param("cityName")String cityName);

    @Query(
           value="select customer from Customer as customer " +
                   "join customer.adresses as addresses " +
                   "join addresses.city as city " +
                   "join city.country as country " +
                   "where country.name = 'China' "
    )
    Page<Customer> findCustomerByAddressInChinaUsingJPQL(Pageable pageable);

    @Query(
            value= "select customer.* from customer, adress, city, country, customer_adress " +
                    "where customer.id = customer_adress.customer_id and " +
                    "adress.id = customer_adress.adress_id and " +
                    "adress.city_id = city.id and " +
                    "city.country_id = country.id and " +
                    "country.name = 'China'",
            nativeQuery = true
    )
    Page<Customer> findCustomerByAddressInChinaUsingSQL(Pageable pageable);

    @Query(
            value = "select customer.id from Customer as customer " +
                    "where customer.phone = :phoneNumber"
    )
    Integer findCustomerIdWithPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(
            value="SELECT customer FROM Customer as customer"
    )
    Page<Customer> getAll(Pageable pageable);

    @Query(
            value = "select function('upper', customer.fullName) from Customer customer"
    )
    List<String> findAllCustomerWithUpperName();

    @Procedure("get_all_customers")
    List<CustomerProjection> findAllCustomersUsingStoredProcedure();

    @Procedure("get_all_customers_by_email_domain")
    List<CustomerProjection> findAllCustomersByEmailPartUsingStoredProcedureTest(@Param("emailDomain") String emailPart);

    @Procedure(procedureName = "get_customer_id_by_phone_number", outputParameterName = "customerId")
    Integer findIdByPhoneNumberUsingStoredProcedure(@Param("phoneNumber") String phoneNumber);

    @Query(
            value = "select customer from Customer as customer " +
                    "where (:fullName is null or customer.fullName = :fullName) and " +
                    "(:email is null or customer.email = :email) and " +
                    "(:phone is null or customer.phone = :phone)"
    )
    List<Customer> searchAllCustomers(@Param("fullName")String fullName,
                                      @Param("email") String email,
                                      @Param("phone") String phone);
}
