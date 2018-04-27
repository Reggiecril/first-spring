package com.reggie.guestbook.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "entries")
public class GuestBookEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private Integer id;
    @Size(min=2,max=10)
    @Column (name = "USER")
    @NotEmpty
    private String user;
    @NotEmpty
    @Size(min=2,max=10)
    @Column (name = "COMMENT")
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}