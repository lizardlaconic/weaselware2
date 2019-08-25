package com.silicatewastes.weaselware2.models.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class TaskDaoImpl implements TaskDaoCustom {
  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public void clear() {
    em.clear();
  }
}
