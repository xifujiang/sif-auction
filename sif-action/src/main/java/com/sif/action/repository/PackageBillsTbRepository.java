package com.sif.action.repository;

import com.sif.action.pojo.PackageBillsTb;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageBillsTbRepository extends JpaRepository<PackageBillsTb,String> {
    @Override
    <S extends PackageBillsTb> S save(S s);


    @Override
    <S extends PackageBillsTb> S saveAndFlush(S s);

    @Override
    List<PackageBillsTb> findAll(Sort sort);
}
