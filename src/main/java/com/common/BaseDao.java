package com.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by joel on 2017/8/14.
 */
@NoRepositoryBean
public interface BaseDao<T,ID> extends JpaRepository<T, Long>{
}
