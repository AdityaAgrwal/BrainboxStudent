package com.brainbox.student.dashboard;

import com.brainbox.student.dto.ClassroomListDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class ClassroomList {

    public static List<ClassroomListDTO> getClassroomList(){
        List<ClassroomListDTO> classroomListDTOs = new ArrayList<>();

        ClassroomListDTO classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(true);
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Home Work");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Test & Grades");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Attendence");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Course Progress");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Time Table");
        classroomListDTOs.add(classroomListDTO);


        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(true);
        classroomListDTOs.add(classroomListDTO);


        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Reports");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Certificates");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(true);
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Teacher Contacts");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Workshops");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Review");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(true);
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("Class group chat");
        classroomListDTOs.add(classroomListDTO);

        classroomListDTO = new ClassroomListDTO();
        classroomListDTO.setIsHeading(false);
        classroomListDTO.setText("PTA Slots");
        classroomListDTOs.add(classroomListDTO);

        return classroomListDTOs;
    }
}
