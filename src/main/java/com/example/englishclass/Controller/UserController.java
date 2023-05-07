package com.example.englishclass.Controller;

import com.example.englishclass.Entity.Course;
import com.example.englishclass.Entity.CourseDescription;
import com.example.englishclass.Entity.Review;
import com.example.englishclass.Entity.User;
import com.example.englishclass.Repository.CourseDescriptionRepository;
import com.example.englishclass.Repository.CourseRepository;
import com.example.englishclass.Repository.ReviewRepository;
import com.example.englishclass.Repository.UserRepository;
import com.example.englishclass.UserService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final CourseDescriptionRepository courseDescriptionRepository;

    private final ReviewRepository reviewRepository;

    @PostMapping("/update")
    public String update(Model model, Authentication authentication, String email,
                         @RequestParam String phone,
                         @RequestParam String firstname,
                         @RequestParam String lastname
                         ) throws IOException {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);

        userService.update(user);

        model.addAttribute("user", user);
        return "redirect:/user/personal";
    }

    @GetMapping("/personal")
    public String personal(Model model, Authentication authentication, String email){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);
        model.addAttribute("user", user);

        Iterable<Course> courses = courseRepository.getAllByUserEmail(email);
        model.addAttribute("courses", courses);
        model.addAttribute("email", email);
        return "personal";
    }

    @PostMapping("/loading")
    public String loginMember(@RequestParam String email){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        System.out.println(auth.getAuthorities());

        System.out.println("Personal " + email);
        return "redirect:/personal";
    }

    @GetMapping("/course")
    public String course(Model model){
        Iterable<CourseDescription> courses = courseDescriptionRepository.findAll();
        model.addAttribute("courses", courses);
        return "Course";
    }

    @PostMapping("/course")
    public String coursePost(Model model, @RequestParam String name, String email){
        CourseDescription courseDescription = courseDescriptionRepository.getCourseDescriptionByName(name);
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        User user = userService.getMemberByEmail(email);

        Course course = new Course();
        course.setName(courseDescription.getName());
        course.setDescription(courseDescription.getDescription());
        course.setDateOver(courseDescription.getDateOver());
        course.setDateStart(courseDescription.getDateStart());
        course.setUserEmail(user.getEmail());
        course.setPrice(courseDescription.getPrice());

        courseRepository.save(course);

        return "redirect:/user/personal";
    }

    @GetMapping("/mycourses")
    public String mycourses(Model model){

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Iterable<Course> courses = courseRepository.getAllByUserEmail(email);
        model.addAttribute("courses", courses);
        return "mycourses";
    }

    @GetMapping("/review")
    public String review(Model model){
        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courses",courseList);

        return "add_review";
    }

    @PostMapping("/review")
    public String reviewAdd(@RequestParam String course, @RequestParam String review){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Review revie = new Review();
        revie.setReview(review);
        revie.setEmail(email);
        revie.setCourse(course);

        reviewRepository.save(revie);

        return "redirect:/user/thanks";
    }

    @GetMapping("/thanks")
    public String thanks(){
        return "thanks";
    }

}
