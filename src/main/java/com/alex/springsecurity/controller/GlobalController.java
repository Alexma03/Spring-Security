package com.alex.springsecurity.controller;

import com.alex.springsecurity.model.Tipo;
import com.alex.springsecurity.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private TipoService tipoService;

    @ModelAttribute("tipos")
    public List<Tipo> populateTipos() {
        return tipoService.findAll();
    }
}
