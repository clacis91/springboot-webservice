package com.example.demo.domain.posts;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void loadThread() {
        postsRepository.save(Posts.builder().title("테스트").content("테스트입니다").author("남우진").build());

        List<Posts> postList = postsRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getTitle(), is("테스트"));
        assertThat(posts.getContent(), is("테스트입니다"));
    }
}