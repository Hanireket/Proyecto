package hanireket.com.prototipo.service;

import hanireket.com.prototipo.model.Post;
import hanireket.com.prototipo.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;


    @Test
    void whenSavingTask_thenRepositoryIsCalled() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setText("Text 1");
        post.setEdit(false);
        postService.addPost(post);
        verify(postRepository).save(post);
    }

    @Test
    void whenUpdatingPost_thenRepositoryIsCalled() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setText("Text 1");
        post.setEdit(true);
        postService.editPost(1L, post);
        verify(postRepository).save(post);
    }

    @Test
    void whenDeletingPost_thenRepositoryIsCalled() {
        postService.deletePost(1L);
        verify(postRepository).deleteById(1L);
    }

    @Test
    void whenGettingPost_thenRepositoryIsCalled() {
        postService.getPost(1L);
        verify(postRepository).findById(1L);
    }

    @Test
    void whenGettingAllPosts_thenRepositoryIsCalled() {
        postService.getAllPosts();
        verify(postRepository).findAll();
    }








}
