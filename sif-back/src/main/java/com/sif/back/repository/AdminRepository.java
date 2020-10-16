package com.sif.back.repository;

import com.sif.back.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(value = "select\n" +
        "count(*) as commodityCount,\n" +
        "count(if(statu=-1,true,null)) as notDealCount,\n" +
        "count(if(now() < begin_time and statu!=-1, true, null)) as notBeginCount,\n" +
        "count(if(now()< end_time and now() > begin_time and statu!=-1, true, null)) as biddingCount,\n" +
        "count(if(end_time < now() and statu!=-1, true, null)) as endCount,\n" +
        "count(if(statu=-2, true, null)) as notPassCount\n" +
        "from commodity_tb;",nativeQuery = true)
    List<Object[]> getCount();

    @Query(value="select \n" +
        "t1.cid as cid,\n" +
        "t1.cname as cname,\n" +
        "t3.name as uname,\n" +
        "t1.price as price,\n" +
        "t1.addprice as addprice,\n" +
        "t1.create_time as createTime,\n" +
        "t1.begin_time as beginTime,\n" +
        "t1.end_time as endTime,\n" +
        "t1.type_id as typeId\n" +
        "from commodity_tb as t1\n" +
        "LEFT JOIN user_commodity_tb as t2\n" +
        "on t1.cid = t2.cid\n" +
        "LEFT JOIN user_tb as t3\n" +
        "on t2.uid = t3.uid\n" +
        "where t1.statu = -1;", nativeQuery = true)
    List<Object[]> getNotDealCommodity();


    @Query(value = "SELECT\n" +
        "\tu3.NAME name3,\n" +
        "\tu3.typeid typeid3,\n" +
        "\tu2.NAME name2,\n" +
        "\tu2.typeid typeid2,\n" +
        "\tu1.NAME name1,\n" +
        "\tu1.typeid typeid1\n" +
        "FROM\n" +
        "\t(\n" +
        "\t\tcommodity_type_tb u1\n" +
        "\t\tRIGHT JOIN commodity_type_tb u2 ON u2.parentid = u1.typeid\n" +
        "\t)\n" +
        "RIGHT JOIN commodity_type_tb u3 ON u3.parentid = u2.typeid",nativeQuery = true)
    List<Object[]> getCommodityType();
}
