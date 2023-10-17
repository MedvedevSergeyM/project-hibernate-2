package com.javarush.movie;

import com.javarush.movie.entity.*;
import com.javarush.movie.entity.type.Rating;
import com.javarush.movie.entity.type.SpecialFeature;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Set;

@Log4j2
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        Customer customer = solution.createCustomer();
//        System.out.println(customer);

        solution.returnInventory();
    }

    private Customer createCustomer() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();

            Query<Store> queryStore = session.createQuery("select s from Store s", Store.class);
            queryStore.setMaxResults(1);
            Store store = queryStore.getSingleResult();

            Query<Address> queryAddress = session.createQuery("select a from Address a", Address.class);
            queryAddress.setMaxResults(1);
            Address address = queryAddress.getSingleResult();

            customer.setStore(store);
            customer.setFirstName("Sergey");
            customer.setLastName("Medvedev");
            customer.setEmail("medvedev@grade-plus.ru");
            customer.setAddress(address);
            customer.setActive(true);

            session.persist(customer);
            transaction.commit();

            return customer;
        }
    }

    private void returnInventory() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Rental> queryRental = session.createQuery("select r from Rental r where r.returnDate is null", Rental.class);
            queryRental.setMaxResults(1);
            Rental rental = queryRental.getSingleResult();

            rental.setReturnDate(LocalDateTime.now());
            session.update(rental);

            transaction.commit();
        }
    }
}
