package hanireket.com.prototipo.controller;

import hanireket.com.prototipo.model.Post;
import hanireket.com.prototipo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public Iterable<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PostMapping("/")
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @PutMapping("/{id}")
    public void editPost(@PathVariable Long id, @RequestBody Post post) {
        postService.editPost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }


}
