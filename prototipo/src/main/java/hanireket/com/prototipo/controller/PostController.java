package hanireket.com.prototipo.controller;

import hanireket.com.prototipo.model.Post;
import hanireket.com.prototipo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.getAllPosts());
        return "index";
    }

    @PostMapping("/addPost")
    public String addPost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("posts", postService.getAllPosts());
            return "index";
        }
        postService.addPost(post);
        return "redirect:/";

    }

    @GetMapping("/edit/{id}")
    public String shoeEditPost(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Post post) {
        postService.editPost(id, post);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }




}
