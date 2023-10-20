package com.java.neo4j;

import com.java.entity.CompanyInfo;
import com.java.entity.TraceInfo;
import com.java.repository.CompanyInfoRepository;
import com.java.repository.TraceInfoRepository;
import com.java.repository.companyNeoRepository;
import com.java.repository.salerNeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class neoService {
    @Autowired
    private companyNeoRepository companyNeoRepository;
    @Autowired
    private salerNeoRepository salerNeoRepository;
    @Autowired
    private TraceInfoRepository traceRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    public void create(String id){
        TraceInfo traceInfo = traceRepository.findByTraceabilityId(id);
        CompanyInfo start = companyInfoRepository.findByName(traceInfo.getManufactor());
        CompanyInfo end = companyInfoRepository.findByName(traceInfo.getCompany());
        companyNeo startNode = new companyNeo();
        companyNeo endnode = new companyNeo();
        startNode.setArea(start.getCompany_area());
        startNode.setName(start.getCompany_name());
        startNode.setPerson(start.getCompany_person());
        startNode.setTel(start.getCompany_tel());
        endnode.setArea(end.getCompany_area());
        endnode.setName(end.getCompany_name());
        endnode.setPerson(end.getCompany_person());
        endnode.setTel(end.getCompany_tel());

        salerNeo salerNeo = new salerNeo();
        salerNeo.setName("sale");
        salerNeo.setCompany(endnode);

        startNode.addSalers(salerNeo);

        companyNeoRepository.save(startNode);
        companyNeoRepository.save(endnode);

    }
}
