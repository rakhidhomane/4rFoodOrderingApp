package com.cybage.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cybage.util.StorageService;

@CrossOrigin("http://localhost:3000")
@Controller
public class FileControllerImpl {
	@Autowired
	private StorageService storageService;

	@RequestMapping(value="/{fileName}", produces = "image/*")
	public void download(@PathVariable("fileName") String fileName, HttpServletResponse resp) {
		Resource resource = storageService.load(fileName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

