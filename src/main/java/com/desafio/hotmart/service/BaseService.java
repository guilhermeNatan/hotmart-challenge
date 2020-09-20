package com.desafio.hotmart.service;

import com.desafio.hotmart.repository.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class BaseService<E>
{
  protected static final int PAGINACAO = 30;

  protected abstract JpaRepository<E, Long> getEntityRepository();

  protected BaseRepo<E> getBaseRepository()
  {
    return null;
  }


  public Collection<E> findAll()
  {
    return this.getEntityRepository().findAll();
  }

  /**
   * @param pageable pagination parameters
   * @return object list with pagination
   */
  public Page<E> findAllWithPaging(Pageable pageable)
  {
    return this.getEntityRepository().findAll(pageable);
  }


  public E findOne(Long id)
  {
    return this.getEntityRepository().findById(id).get();
  }

  public void delete(Long id)
  {
    this.getEntityRepository().deleteById(id);
  }

  /**
   * Persist a object without flushh
   *
   * @param entity  a object for persist.
   * @return object persist.
   */
  public E save(E entity)
  {
    validateBeforeSave(entity);
    return this.getEntityRepository().save(entity);
  }

  /**
   * Deve implementar as validações antes da entidade ser persistida no banco.
   * @param entity Entidade.
   */
  public abstract void validateBeforeSave(E entity);


  /**
   * Salva um objeto com flush.
   *
   * @param entity a ser salva.
   * @return entidade salva.
   */
  public E saveAndFlush(E entity)
  {
    validateBeforeSave(entity);
    return this.getEntityRepository().saveAndFlush(entity);
  }


}
