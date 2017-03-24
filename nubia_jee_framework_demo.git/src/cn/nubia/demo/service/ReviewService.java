package cn.nubia.demo.service;

import cn.nubia.demo.model.Review;
import cn.nubia.framework.core.Service;

import java.util.List;

/**
 * Created by cnslp on 2017/3/17.
 */
public interface ReviewService extends Service {
   public List<Review> findReview();

   public Review findById(int id);

   public Review findByname(String name);
}
