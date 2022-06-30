package com.projet.bibliotheque.controller;

import com.projet.bibliotheque.dao.AdherentRepository;
import com.projet.bibliotheque.model.Adherent;
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
public class AdherentController
{
    @Autowired
    private AdherentRepository adherentRepository;

    @PostMapping(path = "/adherent/register")
    public String register(Adherent adherent, RedirectAttributes ra)
    {
        Long countEmail = adherentRepository.countByEmail(adherent.getEmail());
        Long countCin = adherentRepository.countByCin(adherent.getCin());
        if (countEmail < 1 && countCin < 1)
        {
            adherentRepository.save(adherent);
            ra.addFlashAttribute("message", "L'adherent à été enregistrer avec succès ");
            return "redirect:/adherent/index";
        }
        else
        {
            ra.addFlashAttribute("message", "This account already exist");
            return "redirect:/adherent/create";
        }
    }

    @GetMapping(path ="/adherent/index")
    public String index(Model model)
    {
        List<Adherent> adherent = adherentRepository.findAll();
        model.addAttribute("adherents", adherent);
        return "Adherents/index";
    }

    @GetMapping(path ="/adherent/create")
    public String create(Model model)
    {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("TitrePage", "Ajouter un nouvel adherent");
        return "Adherents/formRegister";
    }

    @PostMapping(path = "/adherent/save")
    public String save(Adherent adherent, RedirectAttributes ra)
    {
        adherentRepository.save(adherent);
        ra.addFlashAttribute("message","L'adherent à été enregistrer avec succés");
        return "redirect:/adherent/index";
    }

    @GetMapping(path ="/adherent/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = adherentRepository.countById(id);
        if (count != null || count !=0)
        {
            Optional<Adherent> adherent = adherentRepository.findById(id);
            if (adherent.isPresent())
            {
                model.addAttribute("adherent", adherent);
                model.addAttribute("TitrePage", "Modifier informations de l'adherent " + adherent.get().getNom());
                return "Adherents/formEdit";
            }
        }
        ra.addFlashAttribute("message", "L'adherent n'a pas été détecter ");
        return "redirect:/adherent/index";
    }

    @GetMapping (path = "adherent/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = adherentRepository.countById(id);
        if (count != null || count !=0)
        {
            Adherent adherent = adherentRepository.getById(id);
            if (adherent != null)
            {
                model.addAttribute("adherent", adherent);
                model.addAttribute("TitrePage", "Les informations de l'adherent " + adherent.getNom());
                return "Adherents/detail";
            }
        }
        ra.addFlashAttribute("message", "L'adherent n'a pas été détecter ");
        return "redirect:/adherent/index";
    }

    @GetMapping (path = "adherent/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra)
    {
        Long count = adherentRepository.countById(id);
        if (count != null || count !=0)
        {
            adherentRepository.deleteById(id);
            ra.addFlashAttribute("message", "L'adherent à été supprimer "+ id);
        }
        else if(count == null || count ==0)
        {
            ra.addFlashAttribute("message", "L'adherent est introuvable "+ id);
        }
        return "redirect:/adherent/index";
    }
}
