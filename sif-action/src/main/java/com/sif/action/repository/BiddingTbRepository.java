package com.sif.action.repository;

import com.sif.action.pojo.BiddingTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-09 10:49
 **/
public interface BiddingTbRepository extends JpaRepository<BiddingTb,String> {

    @Query(value = "SELECT * FROM bidding_tb WHERE cid = ? ORDER BY bidtime DESC",nativeQuery = true)
    List<BiddingTb> findBiddingRecord(String cid);

    @Query(value="select\n" +
        " t1.bidid AS bidid,\n" +
        " t1.cid AS cid,\n" +
        " t2.image AS image,\n" +
        " t2.cname AS cname,\n" +
        " t1.bidprice AS bidprice,\n" +
        " t3.nowprice AS nowprice,\n" +
        "t1.bidtime AS bidtime,\n" +
        " t2.end_time AS endtime,\n" +
        "t4.statu as nowstatus\n" +
        "FROM\n" +
        "\t(\n" +
        "\t\tSELECT\n" +
        "\t\t\tmax(bidid) AS bidid,\n" +
        "\t\t\tcid AS cid,\n" +
        "\t\t\tcount(bidid) AS bidcount,\n" +
        "\t\t\tmax(bidprice) AS bidprice,\n" +
        "\t\t\tmax(bidtime) AS bidtime\n" +
        "\t\tFROM\n" +
        "\t\t\tbidding_tb\n" +
        "\t\twhere statu = 1\n" +
        "\t\tGROUP BY\n" +
        "\t\t\tuid,\n" +
        "\t\t\tcid\n" +
        "\t\tHAVING\n" +
        "\t\t\tuid = ?\n" +
        "\t) t1\n" +
        "LEFT JOIN commodity_tb t2 ON t1.cid = t2.cid\n" +
        "LEFT JOIN (\n" +
        "\tSELECT\n" +
        "\t\tcid AS cid,\n" +
        "\t\tmax(bidprice) nowprice\n" +
        "\tFROM\n" +
        "\t\tbidding_tb\n" +
        "\tGROUP BY\n" +
        "\t\tcid\n" +
        ") t3 ON t1.cid = t3.cid\n" +
        "LEFT JOIN commodity_status_tb t4 on t2.statu=t4.id;", nativeQuery = true)
    List<Object[]> findMyBidding(String uid);


    @Query(value = "SELECT\n" +
        "\tbidid,\n" +
        "\tcid,\n" +
        "\tuid,\n" +
        "\tbidprice,\n" +
        "\tbidtime,\n" +
        "\tstatu\n" +
        "FROM\n" +
        "\tbidding_tb\n" +
        "WHERE\n" +
        "\tcid = ?\n" +
        "AND statu = '1'\n" +
        "ORDER BY\n" +
        "\tbidprice DESC\n" +
        "LIMIT 1", nativeQuery = true)
    List<BiddingTb> isMaxBidder(String cid);

    @Query(value = "select \n" +
        "t1.bidid as bidid,\n" +
        "t2.name as uname,\n" +
        "t3.score as score,\n" +
        "t1.bidprice as bidprice,\n" +
        "t1.bidtime as bidtime\n" +
        " from bidding_tb as t1 \n" +
        "LEFT JOIN user_tb as t2\n" +
        "on t1.uid = t2.uid\n" +
        "LEFT JOIN user_credit_tb as t3\n" +
        "on t1.uid = t3.uid\n" +
        "where t1.cid=? and t1.statu = 1\n" +
        "order by t1.bidtime desc", nativeQuery = true)
    List<Object[]> selectHistoryBidding(String cid);


    /**
    * @Description: 查找最高的竞拍记录
    * @Param: [cid]
    * @return: java.util.List<com.sif.action.pojo.BiddingTb>
    * @Author: shenyini
    * @Date: 2019/12/17
    */
    @Query(value ="SELECT\n" +
        "\tbidid,\n" +
        "\tcid,\n" +
        "\tuid,\n" +
        "\tbidprice,\n" +
        "\tbidtime,\n" +
        "\tstatu\n" +
        "FROM\n" +
        "\tbidding_tb\n" +
        "WHERE\n" +
        "\tcid = ?\n" +
        "AND statu = 1\n" +
        "ORDER BY\n" +
        "\tbidprice DESC\n" +
        "LIMIT 1",nativeQuery = true)
    List<BiddingTb> selectMaxBiddingByCid(String cid);

    /** 
    * @Description: 查询竞购过商品的用户信息
    * @Param: [cid] 
    * @return: java.util.List<java.lang.Object[]> 
    * @Author: shenyini
    * @Date: 2019/12/23 
    */ 
    @Query(value = "SELECT\n" +
        "\tt1.cid,t1.uid, t2.mail\n" +
        "FROM\n" +
        "\tdeposit_pay_tb AS t1\n" +
        "LEFT JOIN user_tb AS t2 ON t1.uid = t2.uid\n" +
        "WHERE\n" +
        "\tt1.cid = ?\n" +
        "AND t1.statu = 1", nativeQuery = true)
    List<Object[]> selectDepositUserByCid(String cid);
}
