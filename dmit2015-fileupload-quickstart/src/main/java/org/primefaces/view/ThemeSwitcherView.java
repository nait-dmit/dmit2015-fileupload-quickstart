package org.primefaces.view;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.domain.Theme;
import org.primefaces.service.ThemeService;

@Named
@SessionScoped
public class ThemeSwitcherView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Theme> themes;
   
    @Inject
    private ThemeService service;

    @PostConstruct
    public void init() {
        themes = service.getThemes();
    }
   
    public List<Theme> getThemes() {
        return themes;
    }

    public void setService(ThemeService service) {
        this.service = service;
    }
}
