package com.example.formularios.app.model.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Usuario {

    @NotEmpty(message = "el campo username No debe ser vacio")
    @Size(min = 3, max = 8)
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email(message = "debe ser un Email valido")
    private String email;
    @NotBlank
    private String nombre;
    @NotEmpty
    private String apellido;

    private String identificador;

    //@Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
    private String identificador2;
    @NotNull
    @Min(3) //se refiere a la menor cifra que se puede introducir
    @Max(5000) //se refiere a la mayor cifra que se puede introducir
    private Integer cuenta;

    @NotNull
    @PastOrPresent //valida que solo sean fechas pasadas o presente
    //@FutureOrPresent //valida que solo sean fechas futuras o presente
    //@DateTimeFormat(pattern = "yyyy-MM-dd") //formato que va a recibir el input fecha del  fromulario
    private Date fechaNacimiento;




    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador2() {
        return identificador2;
    }

    public void setIdentificador2(String identificador2) {
        this.identificador2 = identificador2;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
