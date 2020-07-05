package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*****
 * @Author: luna
 * @Description: com.changgou.goods.controller
 ****/
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /***
     * 分页查询
     * url: /brand/search/pageNum/size
     */
    @PostMapping(value = "/search/{pageNum}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "pageNum")Integer pageNum,
                                            @PathVariable(value = "size")Integer size,
                                            @RequestBody(required = false)Brand brand){
        //调用Service实现分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(brand,pageNum, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK,"条件查询品牌集合成功！",pageInfo);
    }

    /***
     * 分页查询
     * url: /brand/search/pageNum/size
     */
    @GetMapping(value = "/search/{pageNum}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "pageNum")Integer pageNum,
                                            @PathVariable(value = "size")Integer size){
        int a=10/0;
        //调用Service实现分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(pageNum, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK,"条件查询品牌集合成功！",pageInfo);
    }

    /***
     * 根据条件查询
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        //条件查询品牌
        List<Brand> brands = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK,"条件查询品牌集合成功！",brands);
    }


    /***
     * 根据ID删除实现]
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id")Integer id){
        //调用Service实现删除
        brandService.delete(id);
        return new Result(true, StatusCode.OK,"删除数据成功！");
    }


    /****
     * 修改品牌
     * @param brand
     */
    @PutMapping
    public Result update(@RequestBody Brand brand){
        //调用Service实现修改
        brandService.update(brand);
        return new Result(true, StatusCode.OK,"修改数据成功！");
    }


    /****
     * 增加品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        //调用Service实现增加
        brandService.add(brand);
        return new Result(true, StatusCode.OK,"增加数据成功！");
    }


    /***
     * 根据ID查询
     * @param id
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        //调用Service查询数据
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK,"根据ID查询品牌成功！",brand);
    }

    /****
     * 查询所有
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        //调用Service查询所有
        List<Brand> brands = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询品牌集合成功！",brands);
    }
}
