package cn.nubia.demo.model;

import javax.persistence.*;


/**
 * Created by cnslp on 2017/3/17.
 */
@Entity
@Table(name = "tbl_review")
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Integer review_id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "comments",nullable = false)
    private String comments;

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
