package com.mozzi.sns.controller;

import com.mozzi.sns.controller.request.CommentRequest;
import com.mozzi.sns.controller.request.PostCreateRequest;
import com.mozzi.sns.controller.request.PostModifyRequest;
import com.mozzi.sns.controller.request.QuestionCreateRequest;
import com.mozzi.sns.controller.response.CommentResponse;
import com.mozzi.sns.controller.response.PostResponse;
import com.mozzi.sns.controller.response.QuestionResponse;
import com.mozzi.sns.controller.response.Response;
import com.mozzi.sns.service.PostService;
import com.mozzi.sns.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 질문글 전체 리스트 조회
     *
     */
    @GetMapping
    public Response<Page<QuestionResponse>> postList(Pageable pageable){
        return Response.success(postService.postList(pageable).map(QuestionResponse::fromPost));
    }


    /**
     * 질문글 쓰기
     */
    @PostMapping
    public Response<Void> create(@RequestBody QuestionCreateRequest request, Authentication authentication){
        questionService.create(request.getEmail(), request.getType(), request.getContent());
        return Response.success();
    }

    /**
     * 질문글 수정
     */
    @PutMapping("/{id}")
    public Response<Void> modify(@PathVariable Long id, @RequestBody PostModifyRequest request, Authentication authentication){
        postService.modify(request.getTitle(), request.getContent(), request.getHashtag(), authentication.getName(), id);
        return Response.success();
    }

    /**
     * 질문글 삭제
     */
    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable Long id, Authentication authentication){
        postService.delete(authentication.getName(), id);
        return Response.success();
    }


}