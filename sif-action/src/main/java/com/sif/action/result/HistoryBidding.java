package com.sif.action.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description: 竞购历史
 * @author: shenyini
 * @create: 2019-12-11 13:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryBidding implements Serializable {
    private String bidid;
    private String uname;
    private Integer score;
    private BigDecimal bidprice;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bidtime;
}
