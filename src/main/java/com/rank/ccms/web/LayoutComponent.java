package com.rank.ccms.web;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class LayoutComponent implements Serializable {

    public String toggle = "VISIBLE";
    public String close;

    public void handleToggle(ToggleEvent event) {

        toggle = event.getVisibility().name();

    }

    public boolean renderToggle() {
        boolean bool = toggle.equalsIgnoreCase("HIDDEN");
        return bool;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
