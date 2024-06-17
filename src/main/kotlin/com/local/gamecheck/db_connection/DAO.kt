package com.local.gamecheck.db_connection

import javax.persistence.EntityManager

abstract class DAO <TModel, TEntity>(protected val dbManager: EntityManager, protected val entityType:Class<TEntity>){
    abstract fun transformToEntity(originalObject:TModel):TEntity
    abstract fun entityToModel(originalObject:TEntity):TModel
    open fun getList():List<TModel>{
        val query = dbManager.createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map{
                entity -> entityToModel(entity)
        }
    }
    open fun add(newObject:TModel){
        val entity = transformToEntity(newObject)
        dbManager.transaction.begin()
        dbManager.persist(entity)
        dbManager.transaction.commit()
    }
    open fun getById(id:Int):TModel{
        //For example
        //val query = dbManager.createQuery("FROM GameEntity", GameEntity::class.java)
        val query = dbManager.createQuery("FROM ${entityType.simpleName} WHERE id = :id", entityType)
        query.setParameter("id", id)
        return entityToModel(query.singleResult)
    }
    open fun deleteById(id:Int){
        val query = dbManager.createQuery("FROM ${entityType.simpleName} WHERE id = :id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult
        dbManager.transaction.begin()
        dbManager.remove(entity)
        dbManager.transaction.commit()
    }
}