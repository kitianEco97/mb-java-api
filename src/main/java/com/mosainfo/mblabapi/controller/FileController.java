package com.mosainfo.mblabapi.controller;

import com.mosainfo.mblabapi.dto.FileDto;
import com.mosainfo.mblabapi.dto.Mensaje;
import com.mosainfo.mblabapi.entity.File;
import com.mosainfo.mblabapi.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/list")
    public ResponseEntity<List<File>> getAll() {
        List<File> list = fileService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<File> getById(@PathVariable("id") Integer id) {
        if (!fileService.existById( id ))
            return new ResponseEntity( new Mensaje( "Esa ficha no existe" ), HttpStatus.NOT_FOUND);
        File file = fileService.getOne( id ).get();
        return new ResponseEntity( file, HttpStatus.OK );
    }

    @GetMapping("/detailname/{proyecto}")
    public ResponseEntity<File> getByNombre(@PathVariable("proyecto")String proyecto) {
        if (!fileService.existByNombre( proyecto ))
            return new ResponseEntity( new Mensaje( "Esa ficha no existe" ), HttpStatus.NOT_FOUND );
        File file = fileService.getByNombre( proyecto ).get();
        return new ResponseEntity( file, HttpStatus.OK );
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FileDto fileDto) {
        if (fileService.existByNombre( fileDto.getProyecto()))
            return new ResponseEntity( new Mensaje( "Ese nombre de proyecto ya existe" ), HttpStatus.BAD_REQUEST );
        if (StringUtils.isBlank( fileDto.getProyecto() ))
            return new ResponseEntity( new Mensaje( "El nombre del proyecto es obligatorio" ), HttpStatus.BAD_REQUEST );
        if (StringUtils.isBlank( fileDto.getCliente() ) )
            return new ResponseEntity( new Mensaje( "El cliente es obligatorio" ), HttpStatus.BAD_REQUEST );
        if (StringUtils.isBlank( fileDto.getTipohormigon() ))
            return new ResponseEntity( new Mensaje( "El tipo de hormigón es obligatorio" ), HttpStatus.BAD_REQUEST );
        if (fileDto.getNumeromuestra() <= 0)
            return new ResponseEntity( new Mensaje( "El numero de muestra es obligatorio" ), HttpStatus.BAD_REQUEST );
        if (fileDto.getVolumen() <= 0)
            return new ResponseEntity( new Mensaje( "El volumen es obligatorio" ), HttpStatus.BAD_REQUEST );
        if (fileDto.getNhp() <= 0)
            return new ResponseEntity( new Mensaje( "El número de hormigón de prueba es obligatorio" ), HttpStatus.BAD_REQUEST );
        File file = new File(fileDto.getProyecto(), fileDto.getCliente(), fileDto.getTipohormigon(), fileDto.getNumeromuestra(), fileDto.getVolumen(), fileDto.getNhp());
        fileService.save( file );
        return new ResponseEntity(new Mensaje( "Ficha creada" ), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FileDto fileDto) {
        if (!fileService.existById( id ))
            return new ResponseEntity( new Mensaje( "Esa ficha no existe" ), HttpStatus.NOT_FOUND);
        if (fileService.existByNombre( fileDto.getProyecto()) && fileService.getByNombre( fileDto.getProyecto()).get().getId() != id)
            return new ResponseEntity( new Mensaje( "Ese nombre del proyecto ya existe" ), HttpStatus.BAD_REQUEST );
        if (StringUtils.isBlank( fileDto.getProyecto() ))
            return new ResponseEntity( new Mensaje( "El nombre del proyecto es obligatorio" ), HttpStatus.BAD_REQUEST );

        File file = fileService.getOne( id ).get();
        file.setProyecto( fileDto.getProyecto() );
        file.setCliente( fileDto.getCliente() );
        file.setTipohormigon( fileDto.getTipohormigon() );
        file.setNumeromuestra( fileDto.getNumeromuestra() );
        file.setVolumen(fileDto.getVolumen());
        file.setNhp( fileDto.getNhp() );
        fileService.save( file );
        return new ResponseEntity( new Mensaje( "Ficha actualizada" ), HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!fileService.existById( id ))
            return new ResponseEntity( new Mensaje( "Esa ficha no existe" ), HttpStatus.NOT_FOUND);
        fileService.delete( id );
        return new ResponseEntity( new Mensaje( "Ficha eliminada correctanente" ), HttpStatus.OK );
    }
}
