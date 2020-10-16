package com.sif.action.repository;

import com.sif.action.pojo.CommodityPojoTb;
import com.sif.action.result.GoodsListResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-07 14:59
 **/
public interface CommodityTbRepository extends JpaRepository<CommodityPojoTb,String> {

    @Query(value = "SELECT\n" +
        "\tt1.cid as cid,\n" +
        "  t1.cname as cname,\n" +
        "  t1.price as price,\n" +
        "\tt1.addprice as addprice,\n" +
        "\tt1.nowprice as nowprice,\n" +
        "\tt1.image as image,\n" +
        "\tt1.images as images,\n" +
        "\tt1.des as des,\n" +
        "\tt1.create_time as create_time,\n" +
        "\tt1.update_time as update_time,\n" +
        "\tt1.begin_time as begin_time,\n" +
        "\tt1.end_time as end_time,\n" +
        "\tt1.type_id as type_id,\n" +
        "\tt1.brand_name as brand_name,\n" +
        "\tt2.statu as statu\n" +
        "FROM\n" +
        "\tcommodity_tb t1, commodity_status_tb t2\n" +
        "WHERE\n" +
        "\tt1.cid IN (\n" +
        "\t\tSELECT\n" +
        "\t\t\tcid\n" +
        "\t\tFROM\n" +
        "\t\t\tuser_commodity_tb\n" +
        "\t\tWHERE\n" +
        "\t\t\tuid = ?\n" +
        "\t) and \n" +
        "t1.statu = t2.id", nativeQuery = true)
    List<Object[]> findAllComodityByUid(String uid);


    /*查询商品列表*/
    @Query(value = "SELECT\n" +
        "\tt1.cid AS cid,\n" +
        "\tt1.image AS image,\n" +
        "\tt1.price AS price,\n" +
        "\tt1.addprice AS addprice,\n" +
        "\tt1.nowprice AS nowprice,\n" +
        "\tt1.cname AS intro,\n" +
        "\tt3.score AS score,\n" +
        "\tt4. NAME AS userName,\n" +
        "  t5.statu as statu\n" +
        "FROM\n" +
        "\tcommodity_tb t1\n" +
        "LEFT JOIN user_commodity_tb t2 ON t1.cid = t2.cid\n" +
        "LEFT JOIN user_credit_tb t3 ON t2.uid = t3.uid\n" +
        "LEFT JOIN user_tb t4 ON t3.uid = t4.uid\n" +
        "LEFT JOIN commodity_status_tb t5 on t1.statu = t5.id \n" +
        "WHERE\n" +
        "\tt1.type_id LIKE  %?1% ", nativeQuery = true)
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

    @Query(value = "select * from commodity_tb where statu=2", nativeQuery = true)
    List<CommodityPojoTb> getIsBiddingCommodity();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE user_credit_tb SET score = score + ?1 WHERE uid = ?2",nativeQuery = true)
    void changeCredit(int num, String uid);

    @Query(value="select score from user_credit_tb\n" +
        "WHERE\n" +
        "\tuid =? ",nativeQuery = true)
    int getCredit(String uid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update user_package_tb t1 , order_tb t2 set t1.money= t1.money + t2.deposit where t1.uid = t2.uid and t2.cid=?;\n",nativeQuery = true)
    void rebackMoney(String cid);

    @Query(value="SELECT\n" +
        "\tt1.cid,\n" +
        "\tt1.comment as comment,\n" +
        "\tt2.rank AS quality,\n" +
        "\tt3.rank AS speed,\n" +
        "\tt4.rank AS attitude,\n" +
        "\tt5.name AS name,\n" +
        "\tt6.cname AS cname,\n" +
        "\tt1.time AS time\n" +
        "FROM\n" +
        "\t(\n" +
        "\t\tSELECT\n" +
        "\t\t\t*\n" +
        "\t\tFROM\n" +
        "\t\t\tcommodity_comment_tb\n" +
        "\t\tWHERE\n" +
        "\t\t\tsellerid = ?\n" +
        "\t) AS t1\n" +
        "LEFT JOIN commit_rank_tb t2 ON t1.cquality = t2.id\n" +
        "LEFT JOIN commit_rank_tb t3 ON t1.cspeed = t3.id\n" +
        "LEFT JOIN commit_rank_tb t4 ON t1.cattitude = t4.id\n" +
        "LEFT JOIN user_tb t5 ON t1.buyerid = t5.uid\n" +
        "LEFT JOIN commodity_tb t6 ON t1.cid = t6.cid",nativeQuery = true)
    List<Object[]> sellerComment(String sellerid);

    @Query(value = "SELECT\n" +
        "\tt1.cid as cid,\n" +
        "\tt2.cname as intro,\n" +
        "\tt2.brand_name as title,\n" +
        "\tt2.image as img\n" +
        "FROM\n" +
        "\t(\n" +
        "\t\tSELECT\n" +
        "\t\t\tcid\n" +
        "\t\tFROM\n" +
        "\t\t\tdeposit_pay_tb\n" +
        "\t\tWHERE\n" +
        "\t\t\tstatu = 1\n" +
        "\t\tGROUP BY\n" +
        "\t\t\tcid\n" +
        "\t\tORDER BY\n" +
        "\t\t\tcount(cid) DESC LIMIT 8\n" +
        "\t) AS t1\n" +
        "LEFT JOIN commodity_tb AS t2 ON t1.cid = t2.cid", nativeQuery = true)
    List<Object[]> hotCommodity();


    @Query(value = "SELECT\n" +
        "\tt2.cid as cid,\n" +
        "\tt2.cname as intro,\n" +
        "\tt2.brand_name as title,\n" +
        "\tt2.image as img\n" +
        "FROM\n" +
        "\tcommodity_tb t2\n" +
        "WHERE\n" +
        "\tt2.brand_name IN (\n" +
        "\t\tSELECT DISTINCT\n" +
        "\t\t\t(t1.brand_name)\n" +
        "\t\tFROM\n" +
        "\t\t\tcommodity_tb t1\n" +
        "\t\tWHERE\n" +
        "\t\t\tcid IN (\n" +
        "\t\t\t\tSELECT\n" +
        "\t\t\t\t\tcid\n" +
        "\t\t\t\tFROM\n" +
        "\t\t\t\t\tfavorite_commodity_tb\n" +
        "\t\t\t\tWHERE\n" +
        "\t\t\t\t\tuid = '013cb15e-0ff2-4fc1-b517-fd2ed952caf8'\n" +
        "\t\t\t)\n" +
        "\t)\n" +
        "order by rand() limit 8", nativeQuery = true)
    List<Object[]> recommendCommodity(String uid);

    @Query(value = "SELECT\n" +
        "\tcid as cid,\n" +
        "\tcname as intro,\n" +
        "\tbrand_name as title,\n" +
        "\timage as img\n" +
        "FROM\n" +
        "\tcommodity_tb\n" +
        "order by rand() limit 8", nativeQuery = true)
    List<Object[]> queryRandomCommodity();

}