package com.hxw.ssm.dao;


import com.hxw.ssm.pojo.Orderdetail;
import com.hxw.ssm.pojo.OrderdetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderdetailMapper {
    int countByExample(OrderdetailExample example);

    int deleteByExample(OrderdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    List<Orderdetail> selectByExample(OrderdetailExample example);

    Orderdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByExample(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);
}