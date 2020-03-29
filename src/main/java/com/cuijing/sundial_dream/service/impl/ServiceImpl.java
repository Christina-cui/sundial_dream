package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    protected Log log = LogFactory.getLog(this.getClass());
    @Autowired
    protected M baseMapper;

    public ServiceImpl() {
    }

    public M getBaseMapper() {
        return this.baseMapper;
    }

    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
    }

    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(this.currentModelClass());
    }

    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(this.currentModelClass()));
    }

    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(this.currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    public boolean save(T entity) {
        return this.retBool(this.baseMapper.insert(entity));
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.sqlStatement(SqlMethod.INSERT_ONE);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for(Iterator var7 = entityList.iterator(); var7.hasNext(); ++i) {
                T anEntityList = (T) var7.next();
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var16) {
            var5 = var16;
            throw var16;
        } finally {
            if (batchSqlSession != null) {
                if (var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var15) {
                        var5.addSuppressed(var15);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdate(T entity) {
        if (null == entity) {
            return false;
        } else {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal)) ? this.updateById(entity) : this.save(entity);
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var7 = null;

        try {
            int i = 0;

            for(Iterator var9 = entityList.iterator(); var9.hasNext(); ++i) {
                T entity = (T) var9.next();
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                if (!StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal))) {
                    MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
                    param.put("et", entity);
                    batchSqlSession.update(this.sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(SqlMethod.INSERT_ONE), entity);
                }

                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var20) {
            var7 = var20;
            throw var20;
        } finally {
            if (batchSqlSession != null) {
                if (var7 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    public boolean removeById(Serializable id) {
        return SqlHelper.retBool(this.baseMapper.deleteById(id));
    }

    public boolean removeByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "error: columnMap must not be empty", new Object[0]);
        return SqlHelper.retBool(this.baseMapper.deleteByMap(columnMap));
    }

    public boolean remove(Wrapper<T> wrapper) {
        return SqlHelper.retBool(this.baseMapper.delete(wrapper));
    }

    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(this.baseMapper.deleteBatchIds(idList));
    }

    public boolean updateById(T entity) {
        return this.retBool(this.baseMapper.updateById(entity));
    }

    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return this.retBool(this.baseMapper.update(entity, updateWrapper));
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        String sqlStatement = this.sqlStatement(SqlMethod.UPDATE_BY_ID);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for(Iterator var7 = entityList.iterator(); var7.hasNext(); ++i) {
                T anEntityList = (T) var7.next();
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
                param.put("et", anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var17) {
            var5 = var17;
            throw var17;
        } finally {
            if (batchSqlSession != null) {
                if (var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    public T getById(Serializable id) {
        return this.baseMapper.selectById(id);
    }

    public Collection<T> listByIds(Collection<? extends Serializable> idList) {
        return this.baseMapper.selectBatchIds(idList);
    }

    public Collection<T> listByMap(Map<String, Object> columnMap) {
        return this.baseMapper.selectByMap(columnMap);
    }

    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return throwEx ? this.baseMapper.selectOne(queryWrapper) : SqlHelper.getObject(this.log, this.baseMapper.selectList(queryWrapper));
    }

    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return (Map)SqlHelper.getObject(this.log, this.baseMapper.selectMaps(queryWrapper));
    }

    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(this.baseMapper.selectCount(queryWrapper));
    }

    public List<T> list(Wrapper<T> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    public List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return this.baseMapper.selectMaps(queryWrapper);
    }

    public <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return (List)this.baseMapper.selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    public IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectMapsPage(page, queryWrapper);
    }

    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(this.log, this.listObjs(queryWrapper, mapper));
    }
}

