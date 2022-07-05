package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPost"));
    }

    @Test
    @WithMockUser
    void shouldReturnDefaultMessagePostInfo() throws Exception {
        when(postService.findById(1)).thenReturn(Optional.of(Post.of("post", "")));
        this.mockMvc.perform(get("/postInfo/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    void shouldReturnDefaultMessageEdit() throws Exception {
        when(postService.findById(1)).thenReturn(Optional.of(Post.of("post", "")));
        this.mockMvc.perform(get("/edit/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    void shouldReturnDefaultMessageDelete() throws Exception {
        when(postService.findById(1)).thenReturn(Optional.of(Post.of("post", "")));
        this.mockMvc.perform(get("/deletePost/{postId}", "1"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/index"));
    }

    @Test
    @WithMockUser
    void shouldReturnDefaultMessageAdd() throws Exception {
        this.mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPost"));
    }
}
