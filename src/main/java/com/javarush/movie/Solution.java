package com.javarush.movie;

import com.javarush.movie.entity.Film;
import com.javarush.movie.entity.Language;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Solution {

    public static void main(String[] args) {

        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Language language = new Language();
            language.setName("ru");
            language.setLastUpdate(LocalDateTime.now());

            Film film = new Film();
            film.setTitle("film2");
            film.setLanguage(language);
            film.setLastUpdate(LocalDateTime.now());
            film.setRentalDuration((short) 11);
            film.setRentalRate(new BigDecimal(15));
            film.setReplacementCost(new BigDecimal(2));

//            Transaction transaction = session.beginTransaction();
//            session.persist(film);
//            transaction.commit();

        }
    }
}
