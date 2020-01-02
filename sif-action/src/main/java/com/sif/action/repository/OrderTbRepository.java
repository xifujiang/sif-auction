package com.sif.action.repository;

import com.sif.action.pojo.OrderTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderTbRepository extends JpaRepository<OrderTb, String> {

    /**
    * @Description: 获取用户订单
    * @Param: [uid]
    * @return: java.util.List<java.lang.Object[]>
    * @Author: shenyini
    * @Date: 2019/12/25
    */
    @Query(value = "SELECT\n" +
        "\tt1.oid as oid,\n" +
        "\tt1.uid as uid,\n" +
        "\tt1.cid AS cid,\n" +
        "\tt2.image as image,\n" +
        "t2.cname as cname,\n" +
        "t1.deposit as deposit,\n" +
        "t1.price as price,\n" +
        "t1.time as time,\n" +
        "case t1.order_status\n" +
        "when 0 then '还未支付'\n" +
        "when 1 then '已支付'\n" +
        "when 2 then '支付过期'\n" +
        "else '异常'\n" +
        "end as statu\n" +
        "FROM\n" +
        "\torder_tb t1\n" +
        "LEFT JOIN commodity_tb t2 ON t1.cid = t2.cid\n" +
        "WHERE\n" +
        "\tuid = ?",nativeQuery = true)
    List<Object[]> getMyOrder(String uid);
}
