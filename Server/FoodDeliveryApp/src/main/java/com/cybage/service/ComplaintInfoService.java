package com.cybage.service;

import java.util.List;

import com.cybage.model.ComplaintInfo;
import com.google.common.base.Optional;

public interface ComplaintInfoService {

	public List<ComplaintInfo> getAllComplaints();

	public ComplaintInfo save(ComplaintInfo newComplaint);

	public ComplaintInfo findComplaintById(int complaintId);

    public boolean sendReminder(ComplaintInfo complaint); 
}
