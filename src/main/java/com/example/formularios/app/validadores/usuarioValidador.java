package com.example.formularios.app.validadores;


import com.example.formularios.app.model.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class usuarioValidador implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");

        if (usuario.getIdentificador2().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}") == false){
            errors.rejectValue("identificador2","Pattern.usuario.identificador2");
        }
    }
}
