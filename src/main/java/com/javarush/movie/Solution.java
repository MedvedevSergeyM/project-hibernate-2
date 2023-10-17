package com.javarush.movie;

import com.javarush.movie.entity.Film;
import com.javarush.movie.entity.Language;
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

        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
//            Language language1 = new Language();
//            language1.setName("ru");
//            Language language2 = new Language();
//            language2.setName("en");
//
//            Transaction transaction1 = session.beginTransaction();
//            session.persist(language1);
//            session.persist(language2);
//            transaction1.commit();
//
            Query<Language> query = session.createQuery("select l from Language l", Language.class);
            query.list().forEach(log::info);
//
//
//            Film film = new Film();
//            film.setTitle("film2");
//            film.setLanguage(language1);
//            film.setRating(Rating.PG_13);
//            film.setSpecialFeatures(Set.of(SpecialFeature.COMMENTARIES, SpecialFeature.TRAILERS));
//
//            Transaction transaction2 = session.beginTransaction();
//            session.persist(film);
//            transaction2.commit();
//
//            System.out.println(film);
//
        }

    }
}
