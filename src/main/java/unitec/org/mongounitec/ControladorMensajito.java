/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec.org.mongounitec;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rapid
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ControladorMensajito{
    
    @Autowired RepositorioMensajito repoMensa;
  //  aqui a contnuacion van las 5 operaciones basicas con la entidad mensaje
    
   //metodo get guscar todos
    @CrossOrigin
   @RequestMapping(value="/mensajito", method=RequestMethod.GET,
           headers = {"Accept=application/json"}) 
   public ArrayList<Mensajito> obtenerTodos()throws Exception{
           return (ArrayList<Mensajito>) repoMensa.findAll();
   }
    
   //metodo get buscar por id
   @CrossOrigin
   @RequestMapping(value="/mensajito/{id}", method=RequestMethod.GET,
           headers = {"Accept=application/json"}) 
   public Mensajito obtenerPorId (@PathVariable String id) throws Exception{
           return repoMensa.findOne(id);
   }
   
   //metodo POST: guardar version para clientes variables (web y desktop)
   @CrossOrigin
    @RequestMapping(value="/mensajito/{titulo}/{cuerpo}",method=RequestMethod.POST,
            headers = {"Accept=application/json"})
    public Estatus guardarMensajito (@PathVariable String titulo,@PathVariable String cuerpo)
            throws Exception{
        repoMensa.save(new Mensajito(titulo, cuerpo));
        Estatus estatus=new Estatus();
        estatus.setSuccess(true);
        return estatus;
    }
    
    //metodo POST: guardar version mas pura y efectiva
    @CrossOrigin
    @RequestMapping(value="/mensajito", method=RequestMethod.POST, 
            headers = {"Accept=application/json"})
    public Estatus guardarMensajitoMejorado(@RequestBody String json)
    throws Exception{
        //transformamos el json a objeto java
        ObjectMapper maper= new ObjectMapper();
        Mensajito mensa =maper.readValue(json, Mensajito.class);
        repoMensa.save(mensa);
        Estatus es = new Estatus();
        es.setSuccess(true);
        return es;
    }
    
    //Metodo PUT: actualizar
    @CrossOrigin
      @RequestMapping(value="/mensajito", method=RequestMethod.PUT, 
            headers = {"Accept=application/json"})
    public Estatus actualizarMensajito(@RequestBody String json)
    throws Exception{
        //transformamos el json a objeto java
        ObjectMapper maper= new ObjectMapper();
        Mensajito mensa =maper.readValue(json, Mensajito.class);
        repoMensa.save(mensa);
        Estatus es = new Estatus();
        es.setSuccess(true);
        return es;
    }
    
    //Metodo 
    @CrossOrigin
    @RequestMapping(value="/mensajito/{id}", method=RequestMethod.DELETE, 
            headers = {"Accept=application/json"})
    public Estatus borrarMensajito(@PathVariable String id)
    throws Exception{
        
        repoMensa.delete(id);
        Estatus es = new Estatus();
        es.setSuccess(true);
        return es;
    }
}
