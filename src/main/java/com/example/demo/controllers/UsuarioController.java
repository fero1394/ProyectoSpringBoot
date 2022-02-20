package com.example.demo.controllers;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @RestController: le dice a spring que esta es su funcion Esto significa que esta clase es un Controlador
 * @RequestMapping("/usuario"): el mapping nos dice en que direccion del servidor se van a activar los metodos de esta clase
 * Esto significa que la URL comienza con /usuario (después de la ruta de la aplicación)
 * @Autowired: importamos el servicio para que se instancie automaticamente por spring. / Esto significa obtener el bean llamado UsuarioSerivice
 * Que es generado automáticamente por Spring, lo usaremos para manejar los datos
 * @GetMapping(): para indicarle que cuando llegue una peticion de tipo get desde el navegador o otros, se ejecute este flujo
 * @RequestParam: significa que es un parámetro de la solicitud GET o POST
 * @PostMapping(): Se usa para guardar
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * obtenerUsuarios(): vamos a regresar el arreglo de todos los usuarios
     * @return usuarioService.obtenerUsuarios(); Es un metodo programado en la
     * clase servicio por lo que va a llamar este metodo con la instancia
     */

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    /**
     * va a regresar el usuario pero actualizado todos los clientes pueden enviar informacion
     * dentro de la solicitud http guardada en el modelo
     * @param usuario
     * @return
     */
    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
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

    /**
     * Enpoint /nombre recibe la peticion como get para buscar en la base de datos a un usuario por nombre
     * @param nombre: variable que recibe el metodo
     * @return retorna la informacion del usuario pasado por parametro
     */
    @GetMapping("/nombre")
    public ArrayList<UsuarioModel> obtenerUsuarioPorNombre(@RequestParam("nombre") String nombre){
        return this.usuarioService.obtenerPorNombre(nombre);
    }

    /**
     * Se crea enpoint /VIP para acceder al listado de los usuarios VIP los cuales estan definidos por la prioridad de 1 - 5
     * @return regresa un listado con los usuarios VIP
     */
    @GetMapping("/VIP")
    public ArrayList<UsuarioModel> obtenerUsuariosVIP(){
        return this.usuarioService.usuariosVIP();
    }


}
