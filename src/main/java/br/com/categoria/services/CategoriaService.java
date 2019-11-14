package br.com.categoria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.categoria.body.Categoria;
import br.com.categoria.entities.CategoriaEntity;
import br.com.categoria.exception.CategoriaNotFound;
import br.com.categoria.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<CategoriaEntity> getAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    public CategoriaEntity get(Long idCategoria) {
        Optional<CategoriaEntity> subEmitterOp = repository.findById(idCategoria);

        if (subEmitterOp.isPresent()) {
            return subEmitterOp.get();
        }

        return null;
    }

    public CategoriaEntity insertOrUpdate(Categoria categoria) {
        return this.repository.save(convertToEntity(categoria));
    }

    public CategoriaEntity insert(CategoriaEntity categoria) {

        return this.repository.save(categoria);
    }

    public CategoriaEntity convertToEntity(Categoria categoria) {

        CategoriaEntity entity = new CategoriaEntity();

        entity.setIdCategoria(categoria.getIdCategoria());
        entity.setCategoria(categoria.getCategoria());

        return entity;
    }

    public void delete(Long id) throws CategoriaNotFound {

        Optional<CategoriaEntity> entityOp = repository.findById(id);

        if (entityOp.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new CategoriaNotFound("Categria not found");
        }

    }

}