package com.mystory001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystory001.mapper.Sample1Mapper;
import com.mystory001.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxService implements SampleTxServiceInterface{

	@Setter(onMethod_ = {@Autowired})
	private Sample1Mapper mapper1;
	
	@Setter(onMethod_ = {@Autowired})
	private Sample2Mapper mapper2;
	
//	@Override
//	public void addData(String value) {
//		log.info("SampleTxService addDate()");
//		log.info("mapper1...............");
//		mapper1.insertCol1(value);
//		
//		log.info("mapper2...............");
//		mapper2.insertCol2(value);
//		
//	}
	
	@Transactional // 트랜잭션이 처리 될 수 있도록
	@Override
	public void addData(String value) {
		log.info("SampleTxService addDate()");
		log.info("mapper1...............");
		mapper1.insertCol1(value);
		
		log.info("mapper2...............");
		mapper2.insertCol2(value);
		
	}

}
