package com.example.demo.controllers;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")   //el mapping nos dice en que direccion del servidor se va a activar los metodos de esta clase
public class UsuarioController {
    @Autowired //importamos el servicio para que se instancie automaticamente por spring
    UsuarioService usuarioService;

    @GetMapping() //para indicarle que cuando llegue una peticion de tipo get desde el navegador o otros, se ejecute este flujo
    public ArrayList<UsuarioModel> obtenerUsuarios(){ //vamos a regresar el arreglo de todos los usuarios
        return usuarioService.obtenerUsuarios(); //lo hacemos con el servicio que programamos anteriormente que se llama obtenerUsuarios
    }

    @PostMapping()
    public UsuarioModel guardarUsuarui(@RequestBody UsuarioModel usuario){ //va a regresar el usuario pero actualizado // todos los clientes pueden enviar informacion dentro de la solicitud http
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerporPrioridad(prioridad);
    }
    
    @DeleteMapping( path = "/{id}")
    public String eleminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se elimino el usuario con id "+ id;
        }else{
            return "No pudo eliminar el usuario con id "+ id;
        }
    }



}
