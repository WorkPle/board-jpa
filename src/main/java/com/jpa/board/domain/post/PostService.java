package com.jpa.board.domain.post;

import com.jpa.board.domain.entities.User;
import com.jpa.board.domain.user.UserRepository;
import com.jpa.board.domain.user.UserService;
import com.jpa.board.dtos.PostDto.*;
import com.jpa.board.domain.entities.Post;
import com.jpa.board.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long postId) {
        Post post = findPostById(postId);

        return PostResponse.from(post);
    }

    public PostResponse createPost(PostRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("해당 유저를 찾을 수 없습니다."));
        Post post = postRepository.save(request.toEntity(user));

        return PostResponse.from(post);
    }

    public PostResponse modifyPost(Long postId, ModifyRequest request){
        Post post = findPostById(postId);
        post.updatePost(request.title(), request.content());

        return PostResponse.from(post);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
     public Post findPostById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }
}
