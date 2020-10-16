package com.sif.back.controller;

import com.sif.back.entity.MemberRuleTb;
import com.sif.back.service.MemberService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-02-09 19:11
 **/
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/api/list/member")
    public Result listMember() {
        List<MemberRuleTb> list = memberService.getListMember();
        return new Result(true, 200, "获取会员列表成功", list);
    }

    @PostMapping("/api/add/member")
    public Result addMember(@RequestBody MemberRuleTb newMember) {
        memberService.addMember(newMember);
        return new Result(true, 200, "添加会员成功");
    }

    @PostMapping("/api/edit/member")
    public Result editMember(@RequestBody MemberRuleTb editMember){
        memberService.editMember(editMember);
        return new Result(true, 200, "更新会员成功");
    }

    @GetMapping("/api/del/member")
    public Result delMember(@RequestParam int memberid) {
        memberService.delMember(memberid);
        return new Result(true, 200, "删除会员成功");
    }
}
