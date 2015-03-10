package ua.artcode.week7.jpa;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Book() {
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }




}
