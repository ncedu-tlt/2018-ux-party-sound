package ru.ncedu.partysound.models.dto;

public class UsersDTO {

    private String name;

    public UsersDTO() {
    }

    public UsersDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
