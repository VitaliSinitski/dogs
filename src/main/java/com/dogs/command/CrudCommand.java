package com.dogs.command;

import com.dogs.dto.TagDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CrudCommand {
    List<TagDto> execute(HttpServletRequest request);
}

