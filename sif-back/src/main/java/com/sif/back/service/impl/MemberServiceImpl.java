package com.sif.back.service.impl;

import com.sif.back.entity.MemberRuleTb;
import com.sif.back.mapper.MemberRuleTbMapper;
import com.sif.back.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-02-09 19:12
 **/
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRuleTbMapper memberRuleTbMapper;

    @Override
    public List<MemberRuleTb> getListMember() {
        Map map = new HashMap();
        List<MemberRuleTb> list = memberRuleTbMapper.selectByMap(map);
        return list;
    }

    @Override
    public int addMember(MemberRuleTb newMember) {
        memberRuleTbMapper.insert(newMember);
        return 0;
    }

    @Override
    public int editMember(MemberRuleTb editMember) {
        memberRuleTbMapper.updateById(editMember);
        return 0;
    }

    @Override
    public int delMember(int memberid) {
        Map map = new HashMap();
        map.put("memberid", memberid);
        memberRuleTbMapper.deleteByMap(map);
        return 0;
    }

}
