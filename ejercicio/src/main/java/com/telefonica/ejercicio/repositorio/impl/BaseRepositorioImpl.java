package com.telefonica.ejercicio.repositorio.impl;

import com.telefonica.ejercicio.repositorio.BaseRepositorio;
import com.telefonica.ejercicio.util.Criterio;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marcos
 */
public class BaseRepositorioImpl<Entidad extends Serializable, TipoLlave extends Serializable> implements BaseRepositorio<Entidad, TipoLlave> {

    @Autowired
    protected SessionFactory sessionFactory;
    
    protected Class<Entidad> domainClass;
    
    @SuppressWarnings("unchecked")
    public BaseRepositorioImpl() {
        super();
        this.domainClass = (Class<Entidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Entidad obtener(TipoLlave id) {
        return (Entidad) this.sessionFactory.getCurrentSession().createCriteria(domainClass).add(Restrictions.eq("estado", Boolean.TRUE)).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entidad obtenerInclusoEliminado(TipoLlave id) {
        return (Entidad) this.sessionFactory.getCurrentSession().createCriteria(domainClass).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void crear(Entidad entidad) {
        getCurrentSession().save(entidad);
    }

    @Override
    public void actualizar(Entidad entidad) {
        getCurrentSession().saveOrUpdate(entidad);
    }

    @Override
    public void grabarTodos(List<Entidad> list) {
        list.forEach((entidad) -> {
            this.sessionFactory.getCurrentSession().save(entidad);
        });
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entidad> obtenerTodos() {
        Criterio filtro = Criterio.forClass(domainClass);
        filtro.add(Restrictions.eq("estado", Boolean.TRUE));
        Criteria busqueda = filtro.getExecutableCriteria(this.sessionFactory.getCurrentSession());
        busqueda.setProjection(null);
        busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
        return (List<Entidad>) busqueda.list();
    }
    
    @Override
    public Entidad obtenerPorCriterio(Criterio filtro) {
        Criteria busqueda = filtro.getExecutableCriteria(this.sessionFactory.getCurrentSession());
        busqueda.setProjection(null);
        busqueda.setResultTransformer(Criteria.ROOT_ENTITY);
        busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
//        busqueda.setMaxResults(1);
        return (Entidad) busqueda.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Entidad> buscarPorCriteria(Criterio filtro) {
        Criteria busqueda = filtro.getExecutableCriteria(this.sessionFactory.getCurrentSession());
        busqueda.setProjection(null);
        busqueda.setResultTransformer(Criteria.ROOT_ENTITY);
        busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
        return (List<Entidad>) busqueda.list();
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    public List proyeccionPorCriteria(Criterio filtro, Class resultado) {
        Criteria busqueda = filtro.getExecutableCriteria(this.sessionFactory.getCurrentSession());
        busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
        busqueda.setResultTransformer(new AliasToBeanResultTransformer(resultado));
        return busqueda.list();
    }
    
    @Override
    public Object obtenerConResultSet(Criterio filtro, Class resultado) {
        Criteria busqueda = filtro.getExecutableCriteria(this.sessionFactory.getCurrentSession());
        busqueda.setFirstResult(((Criterio) filtro).getFirstResult());
        busqueda.setMaxResults(1);
        busqueda.setResultTransformer(new AliasToBeanResultTransformer(resultado));
        return busqueda.uniqueResult();
    }
    
}
