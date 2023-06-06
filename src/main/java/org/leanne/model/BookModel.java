package org.leanne.model;
import jakarta.persistence.*;

// import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;


    public BookModel() {

    }

    public BookModel(String title) {
        this.title = title;

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + "]";
    }

}