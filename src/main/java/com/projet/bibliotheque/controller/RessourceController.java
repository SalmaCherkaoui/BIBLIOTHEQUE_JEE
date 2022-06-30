package com.projet.bibliotheque.controller;

import com.projet.bibliotheque.dao.EmplacementRepository;
import com.projet.bibliotheque.dao.RessourceRepository;
import com.projet.bibliotheque.model.Emplacement;
import com.projet.bibliotheque.model.Ressource;
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
public class RessourceController
{
    @Autowired
    private RessourceRepository ressourceRepository;
    @Autowired
    private EmplacementRepository emplacementRepository;

    @GetMapping(path ="/ressource/index")
    public String index(Model model)
    {
        List<Ressource> ressource = ressourceRepository.findAll();
        model.addAttribute("ressources", ressource);
        return "Ressources/index";
    }

    @GetMapping(path ="/ressource/create")
    public String create(Model model)
    {
        model.addAttribute("ressource", new Ressource());
        model.addAttribute("TitrePage", "Ajouter une nouvelle ressource");
        List<Emplacement> emplacements = emplacementRepository.findAll();
        model.addAttribute("emplacements", emplacements);
        return "Ressources/form";
    }

    @PostMapping(path = "/ressource/save")
    public String save(Ressource ressource, RedirectAttributes ra)
    {
        ressourceRepository.save(ressource);
        ra.addFlashAttribute("message","La ressource à été enregistrer avec succés");
        return "redirect:/ressource/index";
    }

    @GetMapping(path ="/ressource/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = ressourceRepository.countById(id);
        if (count != null || count !=0)
        {
            Optional<Ressource> ressource = ressourceRepository.findById(id);
            if (ressource.isPresent())
            {
                model.addAttribute("ressource", ressource);
                model.addAttribute("TitrePage", "Modifier la ressource ' " + ressource.get().getTitre() + " ' ");
                List<Emplacement> emplacements = emplacementRepository.findAll();
                model.addAttribute("emplacements", emplacements);
                return "Ressources/form";
            }
        }
        ra.addFlashAttribute("message", "La ressource n'a pas été détecter ");
        return "redirect:/ressource/index";
    }

    @GetMapping (path = "ressource/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = ressourceRepository.countById(id);
        if (count != null || count !=0)
        {
            Ressource ressource = ressourceRepository.getById(id);
            if (ressource != null)
            {
                model.addAttribute("ressource", ressource);
                model.addAttribute("TitrePage", "Les informations de la ressource " + ressource.getTitre());
                return "Ressources/detail";
            }
        }
        ra.addFlashAttribute("message", "La ressource n'a pas été détecter ");
        return "redirect:/ressource/index";
    }

    @GetMapping (path = "ressource/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra)
    {
        Long count = ressourceRepository.countById(id);
        if (count != null || count !=0)
        {
            ressourceRepository.deleteById(id);
            ra.addFlashAttribute("message", "La ressource à été supprimer "+ id);
        }
        else if(count == null || count ==0)
        {
            ra.addFlashAttribute("message", "La ressource est introuvable "+ id);
        }
        return "redirect:/ressource/index";
    }
}
