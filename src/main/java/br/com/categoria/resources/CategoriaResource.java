package br.com.categoria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.categoria.body.Categoria;
import br.com.categoria.entities.CategoriaEntity;
import br.com.categoria.exception.CategoriaNotFound;
import br.com.categoria.services.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "brasilPrev")
public final class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @ResponseBody
    @ApiOperation(value = "Busca por todas as Categorias registradas")
    @GetMapping(path = "/categoria")
    public ResponseEntity<?> getAll(Pageable pageable) {

        Page<CategoriaEntity> categoria = service.getAll(pageable);

        if (categoria == null) {
            return (ResponseEntity<?>) ResponseEntity.noContent();
        }
        return ResponseEntity.ok(categoria);

    }

    @ResponseBody
    @ApiOperation(value = "buscar por id categoria ")
    @GetMapping(path = "/categoria/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {

        CategoriaEntity categoria = service.get(id);

        if (categoria == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoria);

    }

    @RequestBody
    @ApiOperation(value = "Criar Categoria")
    @PostMapping(path = "/categoria/nova")
    public ResponseEntity<?> post(Categoria categoria) {

        try {
            return ResponseEntity.ok(service.insertOrUpdate(categoria));

        } catch (Exception e) {

            return ResponseEntity.status(500).build();
        }

    }

    @RequestBody
    @ApiOperation(value = "Atualizar categoria")
    @PutMapping(path = "/categoria/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, Categoria categoria) {

        CategoriaEntity entity = service.get(id);

        try {
            return ResponseEntity.ok(service.insert(entity));

        } catch (Exception e) {

            return ResponseEntity.status(500).build();
        }

    }

    @RequestBody
    @ApiOperation(value = "Excluir categoria")
    @DeleteMapping(path = "/categoria/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).build();
        } catch (CategoriaNotFound e) {
            return ResponseEntity.noContent().build();
        }
    }

}
