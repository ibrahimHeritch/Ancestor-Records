package com.uncc.ticket.service;


import com.uncc.ticket.model.BlogEntity;
import com.uncc.ticket.model.UsersEntity;
import com.uncc.ticket.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }

    @Transactional
    public BlogEntity storeBlog(BlogEntity blog){
        return blogRepository.save(blog);
    }

    public List<BlogEntity> getAllBlogs(){
        return blogRepository.findAll();
    }

    public List<BlogEntity> getAllBlogsByUser(UsersEntity user){
        return blogRepository.findAllByOwner(user);
    }

    public BlogEntity findById(Long id) { return blogRepository.getOne(id); }

    @Transactional
    public void deleteById(Long id) { blogRepository.deleteById(id); }


}
