package hanireket.com.prototipo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import hanireket.com.prototipo.model.Post;
import hanireket.com.prototipo.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(PostControllerTest.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void whenGetAllPosts_thenReturnJsonArray() throws Exception {
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Post 1");
        post1.setText("Text 1");
        post1.setEdit(false);

        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Post 2");
        post2.setText("Text 2");
        post2.setEdit(true);


        when(postService.getAllPosts()).thenReturn(Arrays.asList(post1, post2));
        String expectedJson = "[{'id': 1, 'title': 'Post 1', 'text': 'Text 1', 'isEdit': 'false'}, {'id': 2, 'title': 'Post 2', 'text': 'Text 2', 'isEdit': 'true'}]";

        mockMvc.perform(get("/api/v1/posts/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));

        verify(postService).getAllPosts();
    }

    @Test
    public void whenGetPost_thenReturnJson() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setText("Text 1");
        post.setEdit(false);
        when(postService.getPost(1L)).thenReturn(post);
        String expectedJson = "{'id': 1, 'title': 'Post 1', 'text': 'Text 1', 'isEdit': 'false'}";

        mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));

        verify(postService).getPost(1L);
    }


    @Test
    public void whenAddPost_thenReturnStatusOk() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setText("Text 1");
        post.setEdit(false);
        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(post);

        mockMvc.perform(post("/api/v1/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk());

        verify(postService).addPost(any(Post.class));
    }

    @Test
    public void whenDeletePost_thenReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/api/v1/posts/1"))
                .andExpect(status().isOk());

        verify(postService).deletePost(1L);
    }

    @Test
    public void whenUpdatePost_thenReturnStatusOk() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setText("Text 1");
        post.setEdit(false);
        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(post);

        mockMvc.perform(put("/api/v1/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk());

        verify(postService).editPost(1L, any(Post.class));
    }



}
