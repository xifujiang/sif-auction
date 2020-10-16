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
        "t1.oid as oid,\n" +
        "t1.uid as uid,\n" +
        "t1.cid AS cid,\n" +
        "t2.image as image,\n" +
        "t2.cname as cname,\n" +
        "t1.deposit as deposit,\n" +
        "t1.price as price,\n" +
        "t1.time as time,\n" +
        "t3.statu as statu\n" +
        "FROM\n" +
        "order_tb t1\n" +
        "LEFT JOIN commodity_tb t2 ON t1.cid = t2.cid\n" +
        "left JOIN commodity_status_tb t3 on t2.statu = t3.id\n" +
        "WHERE\n" +
        "uid = ?",nativeQuery = true)
    List<Object[]> getMyOrder(String uid);

    @Query(value="SELECT\n" +
        "\tt1.oid as oid,\n" +
        "\tt1.addressid as addressid,\n" +
        "\tt2.addressee as addressee,\n" +
        "\tt2.province as province,\n" +
        "\tt2.city as city,\n" +
        "\tt2.part as part,\n" +
        "\tt2.detail as detail,\n" +
        "\tt2.phone as phone\n" +
        "FROM\n" +
        "\torder_tb t1\n" +
        "LEFT JOIN user_address_tb t2 ON t1.addressid = t2.addid\n" +
        "WHERE\n" +
        "\tcid = ?",nativeQuery = true)
    List<Object[]>  selectOrderAddress(String cid);
}
