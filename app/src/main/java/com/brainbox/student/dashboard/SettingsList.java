package com.brainbox.student.dashboard;

import com.brainbox.student.dto.SettingsListDTO;
import com.brainbox.student.dto.SettingsListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public class SettingsList
{
	public static List<SettingsListDTO> getSettingsList(){
		List<SettingsListDTO> settingsListDTOs = new ArrayList<>();

		SettingsListDTO settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(true);
		settingsListDTOs.add(settingsListDTO);

		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(false);
		settingsListDTO.setIsCheckBox(true);
		settingsListDTO.setText("Notifications");
		settingsListDTOs.add(settingsListDTO);

		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(false);
		settingsListDTO.setIsCheckBox(false);
		settingsListDTO.setText("Change Password");
		settingsListDTOs.add(settingsListDTO);

		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(true);
		settingsListDTOs.add(settingsListDTO);


		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(false);
		settingsListDTO.setIsCheckBox(false);
		settingsListDTO.setText("Open Source Libraries");
		settingsListDTOs.add(settingsListDTO);

		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(false);
		settingsListDTO.setIsCheckBox(false);
		settingsListDTO.setText("About Us");
		settingsListDTOs.add(settingsListDTO);


		settingsListDTO = new SettingsListDTO();
		settingsListDTO.setIsHeading(false);
		settingsListDTO.setIsCheckBox(false);
		settingsListDTO.setText("Version");
		settingsListDTOs.add(settingsListDTO);

		return settingsListDTOs;
	}
}
