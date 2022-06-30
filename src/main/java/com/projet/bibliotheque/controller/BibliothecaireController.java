package com.projet.bibliotheque.controller;

import com.projet.bibliotheque.dao.BibliothecaireRepository;
import com.projet.bibliotheque.dao.RessourceRepository;
import com.projet.bibliotheque.model.Bibliothecaire;
import com.projet.bibliotheque.model.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BibliothecaireController
{
    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    @Autowired
    private RessourceRepository ressourceRepository;

    @GetMapping (path ="")
    public String accueil(Model model)
    {
        List<Ressource> ressource = ressourceRepository.findAll();
        model.addAttribute("ressources", ressource);
        return "design/accueil";
    }

    @GetMapping(path = "/bibliothecaire/logout")
    public String logout(HttpSession session){
        session.removeAttribute("NOM");
        session.removeAttribute("PRENOM");
        session.removeAttribute("ROLE");
        return "redirect:/bibliothecaire/login";
    }

    @GetMapping (path ="/bibliothecaire/login")
    public String login(Model model)
    {
        model.addAttribute("bibliothecaire", new Bibliothecaire());
        model.addAttribute("TitrePage", "Page de Connexion ");
        return "Bibliothecaires/login";
    }
    @PostMapping (path ="/bibliothecaire/verify")
    public String verify(Bibliothecaire bibliothecaire, RedirectAttributes ra, HttpSession session)
    {
        Bibliothecaire bibliothecaireFound = bibliothecaireRepository.findByEmailAndMotDePasse(bibliothecaire.getEmail(), bibliothecaire.getMotDePasse());

        if (bibliothecaireFound != null)
        {
            ra.addFlashAttribute("message", "Bienvenue "+bibliothecaireFound.getNom());
            session.setAttribute("NOM", bibliothecaireFound.getNom());
            session.setAttribute("PRENOM", bibliothecaireFound.getPrenom());
            session.setAttribute("ROLE", bibliothecaireFound.getType());
            return "redirect:/";
        }
        else
        {
            ra.addFlashAttribute("message", "Vérifier votre email et mot de passe");
            return "redirect:/bibliothecaire/login";
        }
    }

    @PostMapping("/bibliothecaire/register")
    public String register(Bibliothecaire bibliothecaire, RedirectAttributes ra)
    {
        Long countEmail = bibliothecaireRepository.countByEmail(bibliothecaire.getEmail());
        Long countCin = bibliothecaireRepository.countByCin(bibliothecaire.getCin());
        if (countEmail < 1 && countCin < 1)
        {
            bibliothecaireRepository.save(bibliothecaire);
            ra.addFlashAttribute("message", "Le bibliothecaire à été enregistrer avec succès"+ countCin+" " + countEmail);
            return "redirect:/bibliothecaire/index";
        }
        else
        {
            ra.addFlashAttribute("message", "Ce compte existe déjà"+ countCin+ " " + countEmail);
            return "redirect:/bibliothecaire/create";
        }
    }

    @GetMapping (path ="/bibliothecaire/index")
    public String index(Model model)
    {
        List<Bibliothecaire> bibliothecaire = bibliothecaireRepository.findAll();
        model.addAttribute("bibliothecaires", bibliothecaire);
        return "Bibliothecaires/index";
    }

    @GetMapping(path ="/bibliothecaire/create")
    public String create(Model model)
    {
        model.addAttribute("bibliothecaire", new Bibliothecaire());
        model.addAttribute("TitrePage", "Ajouter nouveau bibliothécaire");
        return "Bibliothecaires/formRegister";
    }

    @PostMapping(path = "/bibliothecaire/save")
    public String save(Bibliothecaire bibliothecaire, RedirectAttributes ra)
    {
        bibliothecaireRepository.save(bibliothecaire);
        ra.addFlashAttribute("message","Le bibliothécaire à été modifier avec succés");
        return "redirect:/bibliothecaire/index";
    }

    @GetMapping(path ="/bibliothecaire/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = bibliothecaireRepository.countById(id);
        if (count != null || count !=0)
        {
            Optional<Bibliothecaire> bibliothecaire = bibliothecaireRepository.findById(id);
            if (bibliothecaire.isPresent())
            {
                model.addAttribute("bibliothecaire", bibliothecaire);
                model.addAttribute("TitrePage", "Modifier informations du bibliothécaire " + bibliothecaire.get().getNom());
                return "Bibliothecaires/formEdit";
            }
        }
        ra.addFlashAttribute("message", "Le bibliothécaire n'a pas été détecter ");
        return "redirect:/bibliothecaire/index";
    }

    @GetMapping (path = "bibliothecaire/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        Long count = bibliothecaireRepository.countById(id);
        if (count != null || count !=0)
        {
            Bibliothecaire bibliothecaire = bibliothecaireRepository.getById(id);
            if (bibliothecaire != null)
            {
                model.addAttribute("bibliothecaire", bibliothecaire);
                model.addAttribute("TitrePage", "Les informations du bibliothécaire " + bibliothecaire.getNom());
                return "Bibliothecaires/detail";
            }
        }
        ra.addFlashAttribute("message", "Le bibliothécaire n'a pas été détecter ");
        return "redirect:/bibliothecaire/index";
    }

    @GetMapping (path = "bibliothecaire/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra)
    {
        Long count = bibliothecaireRepository.countById(id);
        if (count != null || count !=0)
        {
            bibliothecaireRepository.deleteById(id);
            ra.addFlashAttribute("message", "Le bibliothécaire à été supprimer "+ id);
        }
        else if(count == null || count ==0)
        {
            ra.addFlashAttribute("message", "Le bibliothécaire est introuvable "+ id);
        }
        return "redirect:/bibliothecaire/index";
    }

}
