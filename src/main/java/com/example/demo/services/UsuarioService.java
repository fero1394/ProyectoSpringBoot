package com.example.demo.services;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired //para que spring sepa que hay una instancia de service y cual utilizar
    UsuarioRepository usuarioRepository;

    /**
     * Este metodo me devuelve todos los usuarios que tengo en la base de datos
     * lo casteo para devolverlo en un json
     * @return
     */
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>)usuarioRepository.findAll();
    }

    /**
     * Ejempo se recibe
     * nombre: juan
     * email:juan@gmai.com
     * regresa
     * id:1
     * nombre:juan
     * email:juan@gmail.com
     * @param :usuario
     * @return
     */

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){ //para que no cause problemas si el id no es el correcto se pone de tipo Optional
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerporPrioridad(Integer prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    /**
     *Metodo llamado por la clase controller para que service ejecute la logica
     * este metodo hace un llamado a repositorio para que busque en la BD
     * @param nombre
     * @return
     */
    public ArrayList<UsuarioModel> obtenerPorNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }

    /**
     * El metodo Usuarios VIP es llamado por una peticion desde controller el cual hace un llamado
     * a la clase service para que ejecute la logica y seleccione a los usuarios vip y los devuelva
     * en un arraylist, en este metodo se reutiliza el metodo findByPrioridad ya que los usuarios VIP
     * estan en el rango de prioridad entre 1 - 5
     * @return
     */

    public ArrayList<UsuarioModel> usuariosVIP(){
        ArrayList listaUsuariosVIP = new ArrayList();

        for(int i = 0;i<=5;i++){

            if(usuarioRepository.findByPrioridad(i).isEmpty()){
                continue;
            }
            else{
                listaUsuariosVIP.add(usuarioRepository.findByPrioridad(i));
                //System.out.println(((Object)usuarioRepository.findAll()).getClass().getSimpleName());
            }
        }
        return listaUsuariosVIP;

    }
}
