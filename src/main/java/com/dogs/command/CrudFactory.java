package com.dogs.command;

import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

import static lombok.AccessLevel.PRIVATE;

// http://localhost:8080/controller?command=read

@NoArgsConstructor(access = PRIVATE)
public class CrudFactory {
    private static final CrudFactory INSTANCE = new CrudFactory();

    public CrudCommand defineCommand(HttpServletRequest request) {
        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return null;
        }

        CrudEnum currentEnum = CrudEnum.valueOf(action.toUpperCase());
        if (currentEnum == CrudEnum.READ) {
            return new ReadCommand();
        } else {
            return currentEnum.getCurrentCommand();
        }
    }

    public static CrudFactory getInstance() {
        return INSTANCE;
    }

}
