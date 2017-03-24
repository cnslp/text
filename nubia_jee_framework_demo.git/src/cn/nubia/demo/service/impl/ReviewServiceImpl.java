package cn.nubia.demo.service.impl;

import cn.nubia.demo.model.Review;
import cn.nubia.demo.service.ReviewService;
import cn.nubia.framework.core.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by cnslp on 2017/3/17.
 */
@Service(value = "reviewService")
public class ReviewServiceImpl extends BaseService implements ReviewService {
    @Override
    public List<Review> findReview() {
        return dao.find("from Review");
    }

    @Override
    public Review findById(int id) {
        List<Review> list = dao.find("from Review where review_id=?",id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Review findByname(String name) {
        List<Review> list = dao.find("from Review where username=?",name);
        return list.isEmpty() ? null : list.get(0);
    }
}
