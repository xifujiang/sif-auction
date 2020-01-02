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

    @Query(value="select  \n" +
        "t1.bidid as bidid, t1.cid as cid, t2.image as image, t2.cname as cname, \n" +
        "t1.bidprice as bidprice,t3.nowprice as nowprice, " +
//        "t1.bidcount as bidcount, " +
        "t1.bidtime as bidtime,t2.end_time as endtime, if(now() > t2.end_time,'竞拍结束','仍可竞拍') as nowstatus  \n" +
        "from\n" +
        "(select max(bidid) as bidid, cid as cid, count(bidid) as bidcount, max(bidprice) as bidprice, max(bidtime) as bidtime\n" +
        "from bidding_tb GROUP BY uid,cid\n" +
        "having uid = ?\n" +
        ") t1\n" +
        "left JOIN\n" +
        "commodity_tb t2\n" +
        "on t1.cid = t2.cid\n" +
        "left JOIN\n" +
        "(select cid as cid, max(bidprice) nowprice from bidding_tb group by cid) t3\n" +
        "on t1.cid = t3.cid;", nativeQuery = true)
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
