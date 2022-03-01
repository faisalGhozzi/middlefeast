/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui.course;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.util.Resources;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;


import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.app.entity.Course;
import com.esprit.app.services.CourseService;

public class ShowCourseForm extends Form {
    @SuppressWarnings("unused")
    private Resources theme;
    private CourseService cs = new CourseService();
    private Course c = new Course();
    
    
    public ShowCourseForm(Form previous,Resources theme,int id){
        super("Course Details",BoxLayout.y());
        c = new CourseService().findCourse(id);
        Button update = new Button("Update");

        SpanLabel description = new SpanLabel("Description: "+c.getDescription()+"");
        SpanLabel mode = new SpanLabel("Mode: "+c.getMode());
        SpanLabel price = new SpanLabel("Price : "+Float.toString(c.getPrice()));
        SpanLabel starting = new SpanLabel("Starting : "+c.getDateDebut());
        SpanLabel fin = new SpanLabel("Ending : "+c.getDateFin());
        SpanLabel duree = new SpanLabel("Duration : "+c.getDuree());

        this.addAll(description, mode, price, starting, fin, duree);



        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
        this.getToolbar().addCommandToRightBar("Delete", null , (evt) -> {
            cs.deleteCourse(id);
            previous.showBack();
        });
    }
}
