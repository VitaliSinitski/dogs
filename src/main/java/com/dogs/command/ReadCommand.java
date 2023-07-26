package com.dogs.command;

import com.dogs.dto.TagDto;
import com.dogs.services.TagService;
import com.dogs.services.TagServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// http://localhost:8080/controller?command=read

public class ReadCommand implements CrudCommand {

    @Override
    public List<TagDto> execute(HttpServletRequest request) {
        TagService tagService = TagServiceImpl.getInstance();
        return tagService.findAll();
    }
}
