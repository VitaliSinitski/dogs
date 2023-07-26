package com.dogs.command;

public enum CrudEnum {
    READ(new ReadCommand());

    private final CrudCommand crudCommand;

    public CrudCommand getCurrentCommand() {
        return crudCommand;
    }

    CrudEnum(CrudCommand newCrudCommand) {
        crudCommand = newCrudCommand;
    }


}
