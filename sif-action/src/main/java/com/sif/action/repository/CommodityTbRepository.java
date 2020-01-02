package com.sif.action.repository;

import com.sif.action.pojo.CommodityTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-07 14:59
 **/
public interface CommodityTbRepository extends JpaRepository<CommodityTb,String> {

    @Query(value = "SELECT * from commodity_tb where cid in (SELECT cid from user_commodity_tb where uid = ?)", nativeQuery = true)
    List<CommodityTb> findAllComodityByUid(String uid);


    /*查询商品列表*/
    @Query(value = "SELECT t1.cid as cid, t1.image as image,t1.price as price,t1.cname as intro,t3.score as score, t4.name as userName from commodity_tb t1 LEFT JOIN user_commodity_tb t2 on t1.cid = t2.cid LEFT JOIN user_credit_tb t3 on t2.uid = t3.uid LEFT JOIN user_tb t4 on t3.uid = t4.uid where t1.type_id like %?1% ", nativeQuery = true)
    List<Object[]> findGoodList(String typeid);

    /*查询历史商品*/
    @Query(value="SELECT\n" +
        "\tcid AS cid,\n" +
        "\timage AS image,\n" +
        "\tprice AS price,\n" +
        "\taddprice AS addprice\n" +
        "FROM\n" +
        "\tcommodity_tb\n" +
        "WHERE\n" +
        "\tcid IN (\n" +
        "\t\tSELECT\n" +
        "\t\t\tcid\n" +
        "\t\tFROM\n" +
        "\t\t\tuser_commodity_tb\n" +
        "\t\tWHERE\n" +
        "\t\t\tuid IN (\n" +
        "\t\t\t\tSELECT\n" +
        "\t\t\t\t\tuid\n" +
        "\t\t\t\tFROM\n" +
        "\t\t\t\t\tuser_commodity_tb\n" +
        "\t\t\t\tWHERE\n" +
        "\t\t\t\t\tcid = ?1\n" +
        "\t\t\t)\n" +
        "\t) and cid <>?2\n" +
        "order by create_time desc limit 5",nativeQuery = true)
    List<Object[]> findHistoryGoodList(String cid, String ncid);

    @Query(value = "select * from commodity_tb where now()< end_time and now() > begin_time", nativeQuery = true)
    List<CommodityTb> getIsBiddingCommodity();
}