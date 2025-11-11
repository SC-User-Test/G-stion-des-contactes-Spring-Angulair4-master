package org.keley.web;

import java.util.List;
import java.util.Optional;

import org.keley.dao.ContactRepository;
import org.keley.entites.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactRestService {
	
	@Autowired
	private ContactRepository cr;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public List<Contact> getContacts(){
		return cr.findAll();
	}
	
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
	public Optional<Contact> getContact(@PathVariable Long id){
		return cr.findById(id);
	}
	
	@RequestMapping(value = "/chercherContacts", method = RequestMethod.GET)
	public  Page<Contact>  chercher(
			@RequestParam(name = "mc", defaultValue = "") String mc, 
			@RequestParam(name = "page", defaultValue = "0") int page, 
			@RequestParam(name = "size", defaultValue = "5") int size){
		return cr.chercher("%"+mc+"%", new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public Contact save(@RequestBody Contact c){
		return cr.save(c);
	}

	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id){
		cr.deleteById(id);
		return true;
	}
	
	@RequestMapping(value = "/contacts{id}", method = RequestMethod.PUT)
	public Contact save(@PathVariable Long id, @RequestBody Contact c){
		c.setId(id);
		return cr.save(c);
	}

}
