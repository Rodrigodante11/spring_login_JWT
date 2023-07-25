package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/api/usuario")
public class IndexController {

    @GetMapping( value = "/" , produces = "application/json")
    public ResponseEntity init(@RequestParam (value = "nome", defaultValue = "nome não informado") String nome){
        System.out.println(nome);
        return new ResponseEntity("Ola " +nome + " Rest Spring boot" , HttpStatus.OK);
    }
}
