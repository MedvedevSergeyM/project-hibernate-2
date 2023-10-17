package com.javarush.movie;

import com.javarush.movie.entity.*;
import com.javarush.movie.entity.type.Rating;
import com.javarush.movie.entity.type.SpecialFeature;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Set;

@Log4j2
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Customer customer = solution.createCustomer();
        System.out.println(customer);
    }

    private Customer createCustomer() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();

            Query<Store> queryStore = session.createQuery("select s from Store s", Store.class);
            Store store = queryStore.getResultList().get(0);
            System.out.println(store);

            Query<Address> queryAddress = session.createQuery("select a from Address a", Address.class);
            Address address = queryAddress.getResultList().get(0);
            System.out.println(address);

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
}
