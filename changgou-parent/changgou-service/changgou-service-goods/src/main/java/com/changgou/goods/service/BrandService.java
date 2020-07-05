package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*****
 * @Author: luna
 * @Description: com.changgou.goods.service
 ****/
public interface BrandService {

    /****
     * 分页+条件搜索
     * @param pageNum
     * @param size
     * @param brand
     */
    PageInfo<Brand> findPage(Brand brand,Integer pageNum,Integer size);

    /***
     * 分页查询
     * @param pageNum : 当前页
     * @param size : 每页显示条数
     * @return PageInfo<T>
     */
    PageInfo<Brand> findPage(Integer pageNum,Integer size);


    /****
     * 根据用户输入的条件查询
     * 1)输入name-根据name查询[模糊查询]
     * 2)输入了letter-根据letter查询
     * @param brand
     */
    List<Brand> findList(Brand brand);

    /****
     * 删除
     * @param id
     */
    void delete(Integer id);

    /****
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /***
     * 增加
     * @param brand
     */
    void add(Brand brand);


    /***
     * 根据ID查询
     * @param id
     */
    Brand findById(Integer id);

    /***
     * 查询所有
     * @return List<Brand>
     */
    List<Brand> findAll();
}
