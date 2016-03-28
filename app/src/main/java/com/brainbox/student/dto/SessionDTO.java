package com.brainbox.student.dto;

/**
 * Created by Admin-PC on 2/23/2016.
 */
public class SessionDTO
{
	private String gcmKey;
	private StudentDTO studentDTO;


	public String getGcmKey()
	{
		return gcmKey;
	}

	public void setGcmKey(String gcmKey)
	{
		this.gcmKey = gcmKey;
	}

	public StudentDTO getStudentDTO()
	{
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO)
	{
		this.studentDTO = studentDTO;
	}
}
