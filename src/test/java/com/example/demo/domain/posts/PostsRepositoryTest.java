package com.example.demo.domain.posts;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDateTime;
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
        //postsRepository.save(Posts.builder().title("테스트").content("테스트입니다").author("남우진").build());
        postsRepository.save(Posts.builder().title("test").content("this is test").author("wjnam").build());

        List<Posts> postList = postsRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getTitle(), is("test"));
        assertThat(posts.getContent(), is("this is test"));
    }

    @Test
    public void createBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("time test")
                .content("time test content")
                .author("time test author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertTrue(posts.getCreateDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}