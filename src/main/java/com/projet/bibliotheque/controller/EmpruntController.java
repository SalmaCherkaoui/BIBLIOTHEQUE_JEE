package com.projet.bibliotheque.controller;

import com.projet.bibliotheque.dao.AdherentRepository;
import com.projet.bibliotheque.dao.EmpruntRepository;
import com.projet.bibliotheque.dao.ExemplaireRepository;
import com.projet.bibliotheque.model.Adherent;
import com.projet.bibliotheque.model.Exemplaire;
import com.projet.bibliotheque.model.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class EmpruntController
{
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @GetMapping(path ="/emprunt/index")
    public String index(Model model)
    {
        List<Emprunt> emprunt = empruntRepository.findAll();
        model.addAttribute("emprunts", emprunt);
        return "Emprunts/index";
    }

    @GetMapping(path ="/emprunt/create")
    public String create(Model model)
    {
        model.addAttribute("emprunt", new Emprunt());
        model.addAttribute("TitrePage", "Ajouter un nouveau emprunt");
        List<Adherent> adherents = adherentRepository.findAll();
        model.addAttribute("adherents", adherents);
        List<Exemplaire> exemplaires = exemplaireRepository.findAll();
        model.addAttribute("exemplaires", exemplaires);
        return "Emprunts/form";
    }

    @PostMapping(path = "/emprunt/save")
    public String save(Emprunt emprunt, RedirectAttributes ra)
    {
        emprunt.setDuree(7);
        emprunt.setNombreRappel(2);
        Calendar c = Calendar.getInstance();
        c.setTime(emprunt.getDateEmprunt());
        c.add(Calendar.DATE, 7);
        Date dt =new Date(c.getTimeInMillis());
        emprunt.setDateRetour(dt);
        empruntRepository.save(emprunt);
        ra.addFlashAttribute("message","L'emprunt à été enregistrer avec succés");
        return "redirect:/emprunt/index";
    }

    @GetMapping(path ="/emprunt/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = empruntRepository.countById(id);
        if (count != null || count !=0)
        {
            Optional<Emprunt> emprunt = empruntRepository.findById(id);
            if (emprunt.isPresent())
            {
                model.addAttribute("emprunt", emprunt);
                model.addAttribute("TitrePage", "Modifier informations de l'emprunt " + emprunt.get().getDateEmprunt());
                List<Adherent> adherents = adherentRepository.findAll();
                model.addAttribute("adherents", adherents);
                List<Exemplaire> exemplaires = exemplaireRepository.findAll();
                model.addAttribute("exemplaires", exemplaires);
                return "Emprunts/form";
            }
        }
        ra.addFlashAttribute("message", "L'emprunt n'a pas été détecter ");
        return "redirect:/emprunt/index";
    }

    @GetMapping (path = "emprunt/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = empruntRepository.countById(id);
        if (count != null || count !=0)
        {
            Emprunt emprunt = empruntRepository.getById(id);
            if (emprunt != null)
            {
                model.addAttribute("emprunt", emprunt);
                model.addAttribute("TitrePage", "Les informations de l'emprunt " + emprunt.getDateEmprunt());
                return "Emprunts/detail";
            }
        }
        ra.addFlashAttribute("message", "L'emprunt n'a pas été détecter ");
        return "redirect:/emprunt/index";
    }

    @GetMapping (path = "emprunt/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra)
    {
        Long count = empruntRepository.countById(id);
        if (count != null || count !=0)
        {
            empruntRepository.deleteById(id);
            ra.addFlashAttribute("message", "L'emprunt à été supprimer "+ id);
        }
        else if(count == null || count ==0)
        {
            ra.addFlashAttribute("message", "L'emprunt est introuvable "+ id);
        }
        return "redirect:/emprunt/index";
    }
}
