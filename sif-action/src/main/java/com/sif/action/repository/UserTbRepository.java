package com.sif.action.repository;

import com.sif.action.pojo.UserTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTbRepository extends JpaRepository<UserTb, String> {

    @Query(value = "select * from user_tb where uid=?", nativeQuery = true)
    public UserTb selectUser(String uid);

    /** 
    * @Description: 获取登录信息
    * @Param: [uid] 
    * @return: java.util.List<java.lang.Object[]> 
    * @Author: xifujiang 
    * @Date: 2019/11/19 
    */ 
    @Query(value = "SELECT t1.uid as uid, t1.name as name, t2.memberid as memberid, t3.score as score FROM user_tb t1 LEFT JOIN user_member_tb t2 ON t2.uid = t1.uid LEFT JOIN user_credit_tb t3 ON t3.uid = t1.uid WHERE t1.name = ?1 AND t1.password = ?2",nativeQuery = true)
    List<Object[]>  getUserInfo(String uid,String password);

    @Query(value="SELECT\n" +
        "\tt1. NAME AS NAME,\n" +
        "\tt1.age as age,\n" +
        "\tt1.phone as phone,\n" +
        "\tt1.mail as mail,\n" +
        "\tt3.rank as rank,\n" +
        "\tt4.score as score,\n" +
        "\tt5.money as money\n" +
        "FROM\n" +
        "\tuser_tb AS t1\n" +
        "LEFT JOIN user_member_tb t2 ON t2.uid = t1.uid\n" +
        "LEFT JOIN member_rule_tb t3 ON t2.memberid = t3.memberid\n" +
        "LEFT JOIN user_credit_tb AS t4 ON t1.uid = t4.uid\n" +
        "LEFT JOIN user_package_tb AS t5 ON t1.uid = t5.uid\n" +
        "WHERE\n" +
        "\tt1.uid = ?", nativeQuery = true)
    List<Object[]> selectUserDetail(String uid);

    /** 
    * @Description: 更新后获取用户信息 
    * @Param: [uid] 
    * @return: java.util.List<java.lang.Object[]> 
    * @Author: shenyini
    * @Date: 2019/12/11 
    */ 
    @Query(value = "SELECT t1.uid as uid, t1.name as name, t2.memberid as memberid, t3.score as score FROM user_tb t1 LEFT JOIN user_member_tb t2 ON t2.uid = t1.uid LEFT JOIN user_credit_tb t3 ON t3.uid = t1.uid WHERE t1.uid = ?",nativeQuery = true)
    List<Object[]>  getUserUpgrade(String uid);

    /** 
    * @Description: 查看用户的竞拍数
    * @Param: [uid] 
    * @return: java.lang.Integer 
    * @Author: shenyini
    * @Date: 2019/12/11 
    */ 
//    @Query(value="select count(DISTINCT cid) as cid from bidding_tb where uid = ? and statu = 1",nativeQuery = true)
    @Query(value="SELECT\n" +
        "\tcount(DISTINCT cid) AS cid\n" +
        "FROM\n" +
        "\tbidding_tb\n" +
        "WHERE\n" +
        "\tuid = ?1\n" +
        "AND statu = 1\n" +
        "and cid not in(\n" +
        "\tSELECT\n" +
        "\t\tcid from commodity_tb where statu>=3)",nativeQuery = true)
    Integer getUserBiddingCount(String uid);
}
