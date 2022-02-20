package com.example.demo.repositories;

import com.example.demo.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Debe crear el repositorio que contiene los registros
 * Esto será IMPLEMENTADO AUTOMÁTICAMENTE por Spring en un Bean llamado userRepository
 * CRUD se refiere a Crear, Leer, Actualizar, Eliminar
 */
@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {


    public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    /**
     *se crea este metodo findByNombre para buscar en la BD a los usuarios por nombre
     */
    public abstract ArrayList<UsuarioModel> findByNombre(String nombre);



}
