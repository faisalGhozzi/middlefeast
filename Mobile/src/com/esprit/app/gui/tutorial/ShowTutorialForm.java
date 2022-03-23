package com.esprit.app.gui.tutorial;


import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.Tutorial;
import com.esprit.app.services.TutorialService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class ShowTutorialForm extends Form{
    @SuppressWarnings("unused")
    private Resources theme;
    private TutorialService ts = new TutorialService();
    private Tutorial t = new Tutorial();
    
    public ShowTutorialForm(Form previous,Resources theme,int id){
        super("Tutorial Details",BoxLayout.y());
        t = new TutorialService().getTutorial(id);
        Button update = new Button("Update");
        
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(800,800), true);
        String url = t.getImage();
        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url, url));
        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);

        SpanLabel title = new SpanLabel("Title : "+t.getTitre()+"");
        
        SpanLabel description = new SpanLabel("Description : "+t.getDescription()+"");
        SpanLabel mode = new SpanLabel("Category : "+t.getCategory());
        SpanLabel price = new SpanLabel("Price : "+Double.toString(t.getPrix())+"");
        SpanLabel starting = new SpanLabel("Starting : "+t.getDateTuto());
        
        Style s = img.getAllStyles();
        s.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        s.setPadding(6, 6, 6, 6);
        
        this.addAll(img, title, description, mode, price, starting);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
        this.getToolbar().addCommandToRightBar("Delete", null , (evt) -> {
            ts.deleteTutorial(id);
            previous.showBack();
        });
    }
}
