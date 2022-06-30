package com.projet.bibliotheque.controller;

import com.projet.bibliotheque.dao.EmplacementRepository;
import com.projet.bibliotheque.model.Emplacement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmplacementController
{
    @Autowired
    private EmplacementRepository emplacementRepository;

    @GetMapping(path ="/emplacement/index")
    public String index(Model model)
    {
        List<Emplacement> emplacement = emplacementRepository.findAll();
        model.addAttribute("emplacements", emplacement);
        return "Emplacements/index";
    }

    @GetMapping(path ="/emplacement/create")
    public String create(Model model)
    {
        model.addAttribute("emplacement", new Emplacement());
        model.addAttribute("TitrePage", "Ajouter un nouvel emplacement");
        return "Emplacements/form";
    }

    @PostMapping(path = "/emplacement/save")
    public String save(Emplacement emplacement, RedirectAttributes ra)
    {
        emplacementRepository.save(emplacement);
        ra.addFlashAttribute("message","L'emplacement à été enregistrer avec succés");
        return "redirect:/emplacement/index";
    }

    @GetMapping(path ="/emplacement/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = emplacementRepository.countById(id);
        if (count != null || count !=0)
        {
            Optional<Emplacement> emplacement = emplacementRepository.findById(id);
            if (emplacement.isPresent())
            {
                model.addAttribute("emplacement", emplacement);
                model.addAttribute("TitrePage", "Modifier informations de l'emplacement " + emplacement.get().getTheme());
                return "Emplacements/form";
            }
        }
        ra.addFlashAttribute("message", "L'emplacement n'a pas été détecter ");
        return "redirect:/emplacement/index";
    }

    @GetMapping (path = "emplacement/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = emplacementRepository.countById(id);
        if (count != null || count !=0)
        {
            Emplacement emplacement = emplacementRepository.getById(id);
            if (emplacement != null)
            {
                model.addAttribute("emplacement", emplacement);
                model.addAttribute("TitrePage", "Les informations de l'emplacement " + emplacement.getTheme());
                return "Emplacements/detail";
            }
        }
        ra.addFlashAttribute("message", "L'emplacement n'a pas été détecter ");
        return "redirect:/emplacement/index";
    }

    @GetMapping (path = "emplacement/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra)
    {
        Long count = emplacementRepository.countById(id);
        if (count != null || count !=0)
        {
            emplacementRepository.deleteById(id);
            ra.addFlashAttribute("message", "L'emplacement à été supprimer "+ id);
        }
        else if(count == null || count ==0)
        {
            ra.addFlashAttribute("message", "L'emplacement est introuvable "+ id);
        }
        return "redirect:/emplacement/index";
    }
}
