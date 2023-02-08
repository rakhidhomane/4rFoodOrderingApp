package com.cybage.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.ComplaintInfoDao;
import com.cybage.model.ComplaintInfo;
import com.google.common.base.Optional;

@Service
@Transactional
public class ComplaintInfoServiceImpl implements ComplaintInfoService {

	@Autowired
	ComplaintInfoDao complaintDao;

	@Override
	public List<ComplaintInfo> getAllComplaints() {
		return complaintDao.findAll();
	}

	@Override
	public ComplaintInfo save(ComplaintInfo newComplaint) {
		return complaintDao.save(newComplaint);
	}

	@Override
	public ComplaintInfo findComplaintById(int complaintId) {
		return complaintDao.findById(complaintId).orElse(null);
	}

	@Override
	public boolean sendReminder(ComplaintInfo complaint) {
		boolean flag = false;
		LocalTime sendReminderLimit = complaint.getComplaintDate().toLocalTime().plusHours(2);
		System.out.println(LocalTime.now().compareTo(sendReminderLimit));
		if (complaint.getComplaintDate().toLocalDate().compareTo(LocalDate.now()) == 0) {
			if (LocalTime.now().compareTo(sendReminderLimit) <= 0) {
				flag = true;
			}
		}
		return flag;
	}

}
