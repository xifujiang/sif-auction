package com.sif.back.service;

import com.sif.back.entity.MemberRuleTb;

import java.util.List;

public interface MemberService {
    List<MemberRuleTb> getListMember();

    int addMember(MemberRuleTb newMember);

    int editMember(MemberRuleTb editMember);

    int delMember(int memberid);
}
