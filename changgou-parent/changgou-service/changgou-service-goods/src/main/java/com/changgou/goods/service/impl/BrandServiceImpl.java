package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/*****
 * @Author: luna
 * @Description: com.changgou.goods.service.impl
 ****/
@Service
public class BrandServiceImpl implements BrandService {


    @Autowired
    private BrandMapper brandMapper;


    /***
     * 分页+条件搜索
     * @param brand
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer pageNum, Integer size) {
        //静态分页 PageHelper.startPage(pageNum,size)
        PageHelper.startPage(pageNum,size);
        //条件搜索
        Example example = createExample(brand);
        //搜索
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装PageInfo<T>
        return new PageInfo<Brand>(brands);
    }

    /****
     * 分页查询
     * @param pageNum : 当前页
     * @param size : 每页显示条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer pageNum, Integer size) {
        //静态分页 PageHelper.startPage(pageNum,size)
        PageHelper.startPage(pageNum,size);
        //查询
        List<Brand> brands = brandMapper.selectAll();
        //封装PageInfo<T>
        return new PageInfo<Brand>(brands);
    }

    /****
     * 根据用户输入的条件查询
     * 1)输入name-根据name查询[模糊查询]
     * 2)输入了letter-根据letter查询
     * @param brand
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //条件组装抽取
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    public Example createExample(Brand brand) {
        //动态构建条件Example,criteria:动态组装条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //组装条件
        if(brand!=null){
            //id
            if(!StringUtils.isEmpty(brand.getId())){
                criteria.andEqualTo("id",brand.getId());
            }

            if(!StringUtils.isEmpty(brand.getName())){
                //1)输入name-根据name查询[模糊查询]   select * from tb_brand wehere name like '%brand.getName%'
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            if(!StringUtils.isEmpty(brand.getLetter())){
                //2)输入了letter-根据letter查询       select * from tb_brand where letter= 'brand.getLetter'
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        return example;
    }

    /***
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /***
     * 修改品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        //通用Mapper修改数据，忽略空值
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /****
     * 添加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        /***
         * insertSelective：增加操作,忽略空值
         * brand.id=null
         * brand.name=华为6666
         * brand.image=null
         * brand.letter=H
         * brand.seq=null
         *
         * 只要方法中带有Selective都会忽略空值
         * INSERT INTO tb_brand(name,letter) VALUES(?,?)
         *
         * brandMapper.insert(brand);
         * INSERT INTO tb_brand(id,name,image,letter,seq) VALUES(?,?,?,?,?)
         */
        brandMapper.insertSelective(brand);
    }

    /***
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        //通用Mapper：selectByPrimaryKey:根据ID查询
        return brandMapper.selectByPrimaryKey(id);
    }

    /***
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        //使用通用Mapper查询所有
        return brandMapper.selectAll();
    }
}
