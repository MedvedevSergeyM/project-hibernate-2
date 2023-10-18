package com.javarush.movie;

import com.javarush.movie.entity.*;
import com.javarush.movie.entity.type.Rating;
import com.javarush.movie.entity.type.SpecialFeature;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Inventory inventory = solution.newFilmForRent();
        Customer customer = solution.newCustomer();
        Rental rental = solution.rentInventory(customer, inventory);
        solution.returnInventory(rental);
    }

    private Inventory newFilmForRent() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Language> queryLanguage = session.createQuery("select l from Language l where l.name = 'English'", Language.class);
            queryLanguage.setMaxResults(1);
            Language language = queryLanguage.getSingleResult();

            Query<Category> queryCategory = session.createQuery("select c from Category c where c.name in ('Comedy', 'Children')", Category.class);
            Set<Category> categories = new HashSet<>(queryCategory.getResultList());

            Query<Actor> queryActor = session.createQuery("select a from Actor a where a.id in (11, 41, 50, 71, 72)", Actor.class);
            Set<Actor> actors = new HashSet<>(queryActor.getResultList());

            Set<SpecialFeature> specialFeatures = new HashSet<>(Arrays.asList(SpecialFeature.COMMENTARIES, SpecialFeature.DELETED_SCENES));

            Film film = new Film();
            film.setLanguage(language);
            film.setCategories(categories);
            film.setActors(actors);
            film.setTitle("VERY INTERESTING FILM");
            film.setDescription("Very interesting film for children");
            film.setYear(Year.now());
            film.setRentalDuration((byte) 6);
            film.setRentalRate(BigDecimal.valueOf(2.99));
            film.setLength((short) 181);
            film.setReplacementCost(BigDecimal.valueOf(16.99));
            film.setRating(Rating.PG_13);
            film.setSpecialFeatures(specialFeatures);
            session.persist(film);

            FilmText filmText = new FilmText();
            filmText.setId(film.getId());
            filmText.setFilm(film);
            filmText.setTitle(film.getTitle());
            filmText.setDescription(film.getDescription());
            session.persist(filmText);

            Store store = session.get(Store.class, (byte) 2);
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            session.persist(inventory);

            transaction.commit();
            return inventory;
        }
    }

    private Customer newCustomer() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Store store = session.get(Store.class, (byte) 2);
            Address address = session.get(Address.class, (short) 74);

            Customer customer = new Customer();
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

    private Rental rentInventory(Customer customer, Inventory inventory) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Staff staff = session.get(Staff.class, (byte) 1);

            Rental rental = new Rental();
            rental.setCustomer(session.get(Customer.class, customer.getId()));
            rental.setInventory(session.get(Inventory.class, inventory.getId()));
            rental.setRentalDate(LocalDateTime.now());
            rental.setReturnDate(null);
            rental.setStaff(staff);
            session.persist(rental);

            transaction.commit();
            return rental;
        }
    }

    private void returnInventory(Rental rental) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            rental.setReturnDate(LocalDateTime.now());
            session.update(rental);
            transaction.commit();
        }
    }

}
