package com.dogs.servlets;

import com.dogs.dao.TagDao;
import com.dogs.dao.TagDaoImpl;
import com.dogs.dto.TagDto;
import com.dogs.mappers.TagMapper;
import com.dogs.services.TagService;
import com.dogs.services.TagServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tags")
public class TagServlet extends HttpServlet {
    private final TagMapper tagMapper = new TagMapper();
    private final TagDao tagDao = new TagDaoImpl();
    private TagService tagService;

    @Override
    public void init() throws ServletException {
        // инициализация TagServiceImpl, что бы использовать Dependency Injection.
        tagService = new TagServiceImpl(tagMapper, tagDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TagDto> tags = tagService.findAll();
        // Здесь вы можете преобразовать список tags в JSON и отправить обратно клиенту.
        // Например, используя библиотеку Jackson:

        ObjectMapper mapper = new ObjectMapper();
        String jsonTags = mapper.writeValueAsString(tags);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonTags);
    }
}

