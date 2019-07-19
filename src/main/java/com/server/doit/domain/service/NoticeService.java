package com.server.doit.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.doit.domain.entity.Notice;
import com.server.doit.domain.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {
	@Autowired
	NoticeRepository noticeRepository;
	
	public Notice inputNotice(String title,String content) {
		List<Notice> noticeCheck = noticeRepository.findAll();
		
		if( noticeCheck.size() == 0 ) {	
			Notice notice = Notice.builder()
					.title(title).content(content)
					.build();
			
			return noticeRepository.save(notice);
		}
		else {
			Notice newNotice = noticeCheck.get(0);
			newNotice.setContent(content);
			newNotice.setTitle(title);
			return noticeRepository.save(newNotice);
		}	
	}
	
	public Notice getNotice() {
		List<Notice> noticeCheck = noticeRepository.findAll();
		if( noticeCheck.size() == 0 ) {	
			return null;
		}
		return noticeCheck.get(0);
	}
	

}
