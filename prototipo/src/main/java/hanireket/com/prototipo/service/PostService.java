package hanireket.com.prototipo.service;

import hanireket.com.prototipo.model.Post;
import hanireket.com.prototipo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPost(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public void addPost(Post post){
        postRepository.save(post);
    }

    public void editPost(Long id, Post post) {
        Post postOLD = getPost(id);
        post.setDateTime(postOLD.getDateTime());
        post.setEdit(true);
        post.setEditDateTime(LocalDateTime.now());
        postRepository.save(post);
    }

    public void deletePost (Long id){
        postRepository.deleteById(id);
    }









}
