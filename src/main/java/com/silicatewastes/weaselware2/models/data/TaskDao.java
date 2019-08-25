package com.silicatewastes.weaselware2.models.data;

import com.silicatewastes.weaselware2.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskDao extends JpaRepository<Task, Integer>, TaskDaoCustom {
}
