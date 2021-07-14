package com.example.formularios.app.controllers;

import com.example.formularios.app.model.domain.Usuario;
import com.example.formularios.app.validadores.usuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("usuario")//mantiene el objeto usuario persistente en toda la session sin que se pierdan datos, se debe poner el nombre de la clase en minuscula
public class formControllers {


    //******************************* codigo que se usa cuando tenemos una clase validadora "usuarioValidador" ****************************
    @Autowired
    private usuarioValidador validador; //inyectamos la clase validadora

    @InitBinder
    public void initBinder(WebDataBinder binder) {  //se usa para que @Valid valide tanto con la clase validadora como las anotaciones que ponemos en el POJO
        binder.addValidators(validador);//se valida en el metodo procesar que es el que recibe los datos del formulario

        //***************** codigo que valida el campo fecha del formulario dandole un patron a la fecha introducida *********************
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));//para todos los campos date del formulario osea global
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false)); //para un campo especifico, se le pone como segundo parametro el nombre del input del formulario
        //***********************  fin de la validacion del campo fechaNacimiento de la clase Usuario ***************************
    }

    //************************************** FIN del codigo para validar con la clase validadora ******************************************

    //metodo mapeado al formulario
    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("arnold mauricio"); // se usa para pasar datos por defecto al formulario
        usuario.setApellido("muñoz corredor");// se usa para pasar datos por defecto al formulario
        usuario.setIdentificador("785.257.236-K"); //como No esta mapeado en el formulario, se pierde cuando se hace un request a un nuevo formulario, por eso usamos @SessionAttributes
        model.addAttribute("titulo", "formulario usuarios");
        model.addAttribute("usuario", usuario);  //lo ponemos para que el formulario no de un error por no encontrar la palabra usuario con la que se comparte la informacion
        return "form";
    }

    /*
    @PostMapping("/form")
    public String procesar(Model model, @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "resultado del formulario");
        return "resultado";
    }*/

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, @RequestParam String identity, Model model) {

        //validador.validate(usuario, result); //inyeccion de la clase validadora, como usamos initBinder no es necesario inyectarla

        if (result.hasErrors()) {
            //forma manual de pasar mensaje de errores al formulario
            /*Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);*/
            return "form";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "resultado del formulario");
        model.addAttribute("identity", identity);
        return "resultado";
    }

}
