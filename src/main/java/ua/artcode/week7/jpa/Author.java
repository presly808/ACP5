package ua.artcode.week7.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "salary")
    private double salary;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    private String name;
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "author_type")
    private AuthorType type;

    // List, Set, Map, Queue
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(double salary, Date birthday, String name,
                  String mail, AuthorType type) {
        this.salary = salary;
        this.birthday = birthday;
        this.name = name;
        this.mail = mail;
        this.type = type;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public AuthorType getType() {
        return type;
    }

    public void setType(AuthorType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", type=" + type +
                '}';
    }
}
