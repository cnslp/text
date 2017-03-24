package cn.nubia.demo.controller;

import cn.nubia.demo.model.Review;
import cn.nubia.demo.service.ReviewService;
import cn.nubia.framework.util.StringUtil;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cnslp on 2017/3/17.
 */
@Controller
@RequestMapping("/review")
public class ReviewController  {
    @Resource
    private ReviewService reviewService;

    @RequestMapping("listReview")
    public String listview(Model model){
        System.out.println("jinlia");
        model.addAttribute("list", reviewService.findReview());
        return "/review/listReview";
    }

    @RequestMapping("/viewReview")
    public String viewreview(Model model, HttpServletRequest request)
    {
        String id = request.getParameter("review_id");
        if(!StringUtil.isEmpty(id)){
            Review review = reviewService.get(Review.class,Integer.valueOf(id));
            model.addAttribute("review",review);
        }
        return "/review/viewreview";
    }

    @RequestMapping("/update")
    public String updatareview(HttpServletRequest request, Model model){
        int id = Integer.valueOf(request.getParameter("review_id"));
        Review review = reviewService.findById(id);
        model.addAttribute("review",review);
        return "/review/viewreview";
    }



    @RequestMapping("/saveReview")
    public String savareview(Model model,Review review,HttpServletRequest request){
        String id = request.getParameter("review_id");
        if (StringUtil.isEmpty(id))
        {
            reviewService.save(review);
        }else {
            reviewService.update(review);
        }
        return listview(model);
    }

    @RequestMapping("/delReview")
    @ResponseBody
    public String delreview(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("review_id"));
        reviewService.delete(Review.class,id);
        return "success";
    }
}
