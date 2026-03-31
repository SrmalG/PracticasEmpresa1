package com.practica.empresa.empresa.dtos.out;

public class DeleteDTOOut {

    private String messsage;

    public DeleteDTOOut(final String messsage) {
        this.messsage = messsage;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    @Override
    public String toString() {
        return "DeleteDTOOut{" +
                "messsage='" + messsage + '\'' +
                '}';
    }
}
