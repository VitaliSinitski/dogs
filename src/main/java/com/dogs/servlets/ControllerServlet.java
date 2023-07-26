package com.dogs.servlets;

import com.dogs.command.CrudCommand;
import com.dogs.command.CrudFactory;
import com.dogs.dto.TagDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// http://localhost:8080/controller?command=read

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private final CrudFactory crudFactory = CrudFactory.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CrudCommand crudCommand = crudFactory.defineCommand(request);
        if (crudCommand != null) {
            List<TagDto> tagList = crudCommand.execute(request);

            // convert list to JSON
            String jsonResponse = objectMapper.writeValueAsString(tagList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid command");
        }
    }
}
